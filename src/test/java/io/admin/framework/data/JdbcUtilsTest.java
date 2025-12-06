package io.admin.framework.data;

import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

// ... (省略原有注解和 import)

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Import(JdbcUtils.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
class JdbcUtilsDataJpaTest {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        private Long id;
        private String userName;
        private Integer age;
        private String status;
    }

    @Autowired
    private JdbcUtils jdbcUtils;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 确保 Spring 可以找到并注入这些 Bean，虽然它们在测试中可能不会被直接使用
    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManagerFactory entityManagerFactory; // 确保构造函数依赖可以被满足

    private static final String TABLE_NAME = "TEST_USER";

    /**
     * 每个测试方法执行前，创建表并插入测试数据。
     */
    @BeforeEach
    void setUp() {
        // ... (创建表和插入数据的逻辑不变)
        String createSql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "id BIGINT PRIMARY KEY, " +
                "user_name VARCHAR(255), " +
                "age INT, " +
                "status VARCHAR(50)" +
                ");";
        jdbcTemplate.execute(createSql);

        jdbcTemplate.update("DELETE FROM " + TABLE_NAME);
        jdbcTemplate.update("INSERT INTO " + TABLE_NAME + " (id, user_name, age, status) VALUES (1, 'Alice', 30, 'ACTIVE')");
        jdbcTemplate.update("INSERT INTO " + TABLE_NAME + " (id, user_name, age, status) VALUES (2, 'Bob', 25, 'INACTIVE')");
        jdbcTemplate.update("INSERT INTO " + TABLE_NAME + " (id, user_name, age, status) VALUES (3, 'Charlie', 35, 'ACTIVE')");
        jdbcTemplate.update("INSERT INTO " + TABLE_NAME + " (id, user_name, age, status) VALUES (4, 'David', 40, 'ACTIVE')");
    }

    // --- 补充：通用 DML 和批量操作测试 ---

    @Test
    void testExecute_DML_NoParams() {
        // 执行不带参数的 SQL
        int rowsAffected = jdbcUtils.execute("UPDATE " + TABLE_NAME + " SET status = 'TEST' WHERE id = 1");
        assertThat(rowsAffected).isEqualTo(1);
        String status = jdbcUtils.findScalar("SELECT status FROM " + TABLE_NAME + " WHERE id = 1").toString();
        assertThat(status).isEqualTo("TEST");
    }

    @Test
    void testBatchUpdate() {
        String sql = "UPDATE " + TABLE_NAME + " SET age = ? WHERE user_name = ?";
        List<Object[]> batchArgs = Arrays.asList(
                new Object[]{100, "Alice"},
                new Object[]{101, "Bob"}
        );

        int[] rowsAffected = jdbcUtils.batchUpdate(sql, batchArgs);

        // 验证返回数组长度和内容
        assertThat(rowsAffected).hasSize(2);
        assertThat(rowsAffected).containsExactly(1, 1);

        // 验证数据库状态
        Long aliceAge = jdbcUtils.findLong("SELECT age FROM " + TABLE_NAME + " WHERE user_name = 'Alice'");
        assertThat(aliceAge).isEqualTo(100L);
    }

    // --- 补充：Find 家族测试 ---

    @Test
    void testFindAll_BeanList() {
        String sql = "SELECT id, user_name, age, status FROM " + TABLE_NAME + " WHERE age > 30 ORDER BY age ASC";
        List<User> users = jdbcUtils.findAll(User.class, sql);

        assertThat(users).hasSize(2);
        assertThat(users.get(0).getUserName()).isEqualTo("Charlie");
        assertThat(users.get(1).getAge()).isEqualTo(40);
    }

    @Test
    void testFindAll_MapList() {
        // 使用findAllMap (原有方法)
        String sql = "SELECT user_name, age FROM " + TABLE_NAME + " WHERE id < 3 ORDER BY id";
        List<Map<String, Object>> list = jdbcUtils.findAllMap(sql);

        assertThat(list).hasSize(2);
        // H2 默认列名大写
        assertThat(list.get(0)).containsEntry("USER_NAME", "Alice");
        assertThat(list.get(1)).containsEntry("AGE", 25);
    }

    @Test
    void testFindMapDict() {
        String sql = "SELECT id, user_name FROM " + TABLE_NAME + " ORDER BY id";
        Map<String, Object> dict = jdbcUtils.findMapDict(sql);

        assertThat(dict).hasSize(4);
        // 键是第一列 (id=1L)，值是第二列 (user_name="Alice")
        assertThat(dict).containsEntry("1", "Alice");
        assertThat(dict).containsEntry("4", "David");
    }

    @Test
    void testFindColumnList() {
        String sql = "SELECT user_name FROM " + TABLE_NAME + " WHERE status = 'ACTIVE' ORDER BY user_name";
        List<String> names = jdbcUtils.findColumnList(String.class, sql);

        assertThat(names).containsExactly("Alice", "Charlie", "David");
    }

    @Test
    void testExists() {
        boolean exists = jdbcUtils.exists("SELECT 1 FROM " + TABLE_NAME + " WHERE user_name = 'Bob'");
        assertThat(exists).isTrue();

        boolean notExists = jdbcUtils.exists("SELECT 1 FROM " + TABLE_NAME + " WHERE user_name = 'Zoe'");
        assertThat(notExists).isFalse();
    }

    // --- 补充：分页测试 ---

    @Test
    void testFindAll_Pageable_MapResult() {
        // 请求第 1 页（即第二页），每页 2 条，按 age 降序
        Pageable pageable = PageRequest.of(1, 2, Sort.by("age").descending());
        String sql = "SELECT id, user_name, age, status FROM " + TABLE_NAME;

        Page<Map> page = jdbcUtils.findAll(pageable, sql);

        assertThat(page.getTotalElements()).isEqualTo(4);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.getContent()).hasSize(2);

        // 验证分页和排序：第二页第一条应该是 Bob (25岁)
        // 排序顺序: David(40), Charlie(35) | Alice(30), Bob(25)
        assertThat(page.getContent().get(0).get("USER_NAME")).isEqualTo("Alice");
        assertThat(page.getContent().get(1).get("AGE")).isEqualTo(25);
    }

    // --- 补充：元数据和工具方法测试 ---

    @Test
    void testTableExists() {
        assertThat(jdbcUtils.tableExists(TABLE_NAME)).isTrue();
        assertThat(jdbcUtils.tableExists("non_existent_table")).isFalse();
    }

    @Test
    void testDropTable() {
        // 尝试删除一个存在的表
        int result = jdbcUtils.dropTable(TABLE_NAME);
        assertThat(result).isEqualTo(1); // 成功执行 DDL

        // 验证表已被删除
        assertThat(jdbcUtils.tableExists(TABLE_NAME)).isFalse();
    }
}