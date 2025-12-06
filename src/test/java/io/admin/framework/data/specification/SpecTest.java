package io.admin.framework.data.specification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SpecTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;
    private Department hrDept;
    private Department itDept;

    @BeforeEach
    void setUp() {
        // 清理并准备数据
        userRepository.deleteAll();

        hrDept = new Department();
        hrDept.setName("Human Resources");
        entityManager.persist(hrDept);

        itDept = new Department();
        itDept.setName("Information Technology");
        entityManager.persist(itDept);

        user1 = new User();
        user1.setUsername("Alice");
        user1.setAge(30);
        user1.setStatus("Active");
        user1.setDept(hrDept);
        entityManager.persist(user1);

        user2 = new User();
        user2.setUsername("Bob");
        user2.setAge(25);
        user2.setStatus("Inactive");
        user2.setDept(itDept);
        entityManager.persist(user2);

        entityManager.flush(); // 确保数据写入数据库
    }

    // --- 基础条件测试 ---

    @Test
    void testEqualCondition() {
        // 查找 username = 'Alice' 的用户
        Specification<User> spec = new Spec<User>()
                .equal("username", "Alice");

        List<User> results = userRepository.findAll(spec);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getUsername()).isEqualTo("Alice");
    }

    @Test
    void testGreaterThanCondition() {
        // 查找 age > 25 的用户
        Specification<User> spec = new Spec<User>()
                .greaterThan("age", 25);

        List<User> results = userRepository.findAll(spec);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getUsername()).isEqualTo("Alice");
    }

    @Test
    void testAndComposition() {
        // 查找 status = 'Active' AND age >= 30 的用户
        Specification<User> spec = new Spec<User>()
                .equal("status", "Active")
                .greaterThanOrEqual("age", 30);

        List<User> results = userRepository.findAll(spec);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getUsername()).isEqualTo("Alice");
    }

    // --- 关联点操作测试 (dept.name) ---

    @Test
    void testDotNotationEqual() {
        // 查找部门名为 'Information Technology' 的用户
        Specification<User> spec = new Spec<User>()
                .equal("dept.name", "Information Technology");

        List<User> results = userRepository.findAll(spec);

        // 预期只找到 Bob
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getUsername()).isEqualTo("Bob");
    }

    @Test
    void testDotNotationLike() {
        // 查找部门名包含 'resources' 的用户 (注意 Like 操作在 ConditionSpec 中已被转为小写)
        Specification<User> spec = new Spec<User>()
                .like("dept.name", "resources");

        List<User> results = userRepository.findAll(spec);

        // 预期只找到 Alice
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getUsername()).isEqualTo("Alice");
    }

    // --- 逻辑 OR 条件测试 ---

    @Test
    void testOrLikeCondition() {
        // 查找 username 包含 'e' 或者 status 包含 'e' 的用户
        // 'e' 匹配 Alice (AlICE) 和 Bob (InactivE)
        Specification<User> spec = new Spec<User>()
                .orLike("e", "username", "status");

        List<User> results = userRepository.findAll(spec);

        // 预期：Alice (username=Alice) 和 Bob (status=Inactive) 都被找到
        assertThat(results).hasSize(2);
        assertThat(results).extracting(User::getUsername).containsExactlyInAnyOrder("Alice", "Bob");
    }

    // --- NOT 条件测试 ---

    @Test
    void testNotCondition() {
        // 1. 构建要取反的条件：username = 'Bob'
        Specification<User> bobSpec = new Spec<User>().equal("username", "Bob");

        // 2. 将其取反并执行
        Specification<User> notBobSpec = Specification.not(bobSpec);

        List<User> results = userRepository.findAll(notBobSpec);

        // 预期找到除了 Bob 之外的用户 (Alice)
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getUsername()).isEqualTo("Alice");
    }
}