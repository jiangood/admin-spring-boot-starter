package io.github.jiangood.openadmin.modules.api.dao;

import io.github.jiangood.openadmin.modules.api.entity.ApiAccessLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ApiAccessLogDaoTest {

    @Autowired
    private ApiAccessLogDao apiAccessLogDao;

    private ApiAccessLog testLog;

    @BeforeEach
    public void setUp() {
        // 清理测试数据
        apiAccessLogDao.deleteAll();

        // 创建测试API访问日志
        testLog = new ApiAccessLog();
        testLog.setTimestamp(System.currentTimeMillis());
        testLog.setName("测试接口");
        testLog.setAction("testAction");
        testLog.setRequestData("{\"param1\": \"value1\", \"param2\": \"value2\"}");
        testLog.setResponseData("{\"code\": 200, \"message\": \"success\"}");
        testLog.setIp("127.0.0.1");
        testLog.setIpLocation("本地");
        testLog.setExecutionTime(100L);
        testLog.setAccountName("测试账户");
        testLog = apiAccessLogDao.save(testLog);
    }

    @Test
    public void testSave() {
        ApiAccessLog log = new ApiAccessLog();
        log.setTimestamp(System.currentTimeMillis());
        log.setName("新测试接口");
        log.setAction("newTestAction");
        log.setRequestData("{\"param1\": \"value1\"}");
        log.setResponseData("{\"code\": 200, \"message\": \"success\"}");
        log.setIp("192.168.1.1");
        log.setIpLocation("测试位置");
        log.setExecutionTime(200L);
        log.setAccountName("新测试账户");

        ApiAccessLog savedLog = apiAccessLogDao.save(log);
        assertNotNull(savedLog.getId());
        assertEquals("新测试接口", savedLog.getName());
        assertEquals("newTestAction", savedLog.getAction());
    }

    @Test
    public void testDelete() {
        String logId = testLog.getId();
        apiAccessLogDao.delete(testLog);

        ApiAccessLog deletedLog = apiAccessLogDao.findById(logId);
        assertNull(deletedLog);
    }

    @Test
    public void testFindById() {
        ApiAccessLog foundLog = apiAccessLogDao.findById(testLog.getId());
        assertNotNull(foundLog);
        assertEquals("测试接口", foundLog.getName());

        // 测试不存在的ID
        ApiAccessLog nonExistentLog = apiAccessLogDao.findById("non-existent-id");
        assertNull(nonExistentLog);
    }

    @Test
    public void testExistsById() {
        boolean exists = apiAccessLogDao.existsById(testLog.getId());
        assertTrue(exists);

        // 测试不存在的ID
        boolean nonExists = apiAccessLogDao.existsById("non-existent-id");
        assertFalse(nonExists);
    }

    @Test
    public void testCount() {
        long count = apiAccessLogDao.count();
        assertEquals(1, count);

        // 添加一个日志后再次计数
        ApiAccessLog newLog = new ApiAccessLog();
        newLog.setTimestamp(System.currentTimeMillis());
        newLog.setName("计数测试接口");
        newLog.setAction("countTestAction");
        newLog.setRequestData("{\"param\": \"value\"}");
        newLog.setResponseData("{\"code\": 200, \"message\": \"success\"}");
        newLog.setIp("192.168.1.2");
        newLog.setIpLocation("计数测试位置");
        newLog.setExecutionTime(150L);
        newLog.setAccountName("计数测试账户");
        apiAccessLogDao.save(newLog);

        long newCount = apiAccessLogDao.count();
        assertEquals(2, newCount);
    }

    @Test
    public void testFindAll() {
        List<ApiAccessLog> logs = apiAccessLogDao.findAll();
        assertEquals(1, logs.size());
        assertEquals("测试接口", logs.get(0).getName());
    }

}
