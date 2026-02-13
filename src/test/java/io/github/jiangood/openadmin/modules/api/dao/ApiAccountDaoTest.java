package io.github.jiangood.openadmin.modules.api.dao;

import io.github.jiangood.openadmin.modules.api.entity.ApiAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ApiAccountDaoTest {

    @Autowired
    private ApiAccountDao apiAccountDao;

    private ApiAccount testAccount;

    @BeforeEach
    public void setUp() {
        // 清理测试数据
        apiAccountDao.deleteAll();

        // 创建测试API账户
        testAccount = new ApiAccount();
        testAccount.setName("测试API账户");
        testAccount.setAccessIp("127.0.0.1");
        testAccount.setAppId("test-app-id");
        testAccount.setAppSecret("test-app-secret");
        testAccount.setEnable(true);
        testAccount.setEndTime(new Date(System.currentTimeMillis() + 365 * 24 * 60 * 60 * 1000)); // 一年后过期
        testAccount.setPerms(List.of("api:read", "api:write"));
        testAccount = apiAccountDao.save(testAccount);
    }

    @Test
    public void testSave() {
        ApiAccount account = new ApiAccount();
        account.setName("新测试API账户");
        account.setAccessIp("192.168.1.1");
        account.setAppId("new-test-app-id");
        account.setAppSecret("new-test-app-secret");
        account.setEnable(true);
        account.setEndTime(new Date(System.currentTimeMillis() + 365 * 24 * 60 * 60 * 1000)); // 一年后过期
        account.setPerms(List.of("api:read"));

        ApiAccount savedAccount = apiAccountDao.save(account);
        assertNotNull(savedAccount.getId());
        assertEquals("新测试API账户", savedAccount.getName());
        assertEquals("new-test-app-id", savedAccount.getAppId());
    }

    @Test
    public void testDelete() {
        String accountId = testAccount.getId();
        apiAccountDao.delete(testAccount);

        ApiAccount deletedAccount = apiAccountDao.findById(accountId);
        assertNull(deletedAccount);
    }

    @Test
    public void testFindById() {
        ApiAccount foundAccount = apiAccountDao.findById(testAccount.getId());
        assertNotNull(foundAccount);
        assertEquals("测试API账户", foundAccount.getName());

        // 测试不存在的ID
        ApiAccount nonExistentAccount = apiAccountDao.findById("non-existent-id");
        assertNull(nonExistentAccount);
    }

    @Test
    public void testExistsById() {
        boolean exists = apiAccountDao.existsById(testAccount.getId());
        assertTrue(exists);

        // 测试不存在的ID
        boolean nonExists = apiAccountDao.existsById("non-existent-id");
        assertFalse(nonExists);
    }

    @Test
    public void testCount() {
        long count = apiAccountDao.count();
        assertEquals(1, count);

        // 添加一个账户后再次计数
        ApiAccount newAccount = new ApiAccount();
        newAccount.setName("计数测试API账户");
        newAccount.setAccessIp("192.168.1.2");
        newAccount.setAppId("count-test-app-id");
        newAccount.setAppSecret("count-test-app-secret");
        newAccount.setEnable(true);
        newAccount.setEndTime(new Date(System.currentTimeMillis() + 365 * 24 * 60 * 60 * 1000)); // 一年后过期
        apiAccountDao.save(newAccount);

        long newCount = apiAccountDao.count();
        assertEquals(2, newCount);
    }

    @Test
    public void testFindAll() {
        List<ApiAccount> accounts = apiAccountDao.findAll();
        assertEquals(1, accounts.size());
        assertEquals("测试API账户", accounts.get(0).getName());
    }

}
