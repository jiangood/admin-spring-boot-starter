package io.github.jiangood.openadmin.modules.system.dao;

import io.github.jiangood.openadmin.modules.system.entity.SysLog;
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
public class SysOpLogDaoTest {

    @Autowired
    private SysOpLogDao sysOpLogDao;

    private SysLog testLog;

    @BeforeEach
    public void setUp() {
        // 清理测试数据
        sysOpLogDao.deleteAll();

        // 创建测试日志
        testLog = new SysLog();
        testLog.setOperation("测试操作");
        testLog.setParams("{\"param1\": \"value1\", \"param2\": \"value2\"}");
        testLog.setResult("测试结果");
        testLog.setSuccess(true);
        testLog.setDuration(100);
        testLog.setUserId("test-user-id");
        testLog.setUsername("测试用户");
        testLog.setIp("127.0.0.1");
        testLog.setOperationTime(new Date());
        testLog = sysOpLogDao.save(testLog);
    }

    @Test
    public void testSave() {
        SysLog log = new SysLog();
        log.setOperation("新测试操作");
        log.setParams("{\"param1\": \"value1\"}");
        log.setResult("新测试结果");
        log.setSuccess(true);
        log.setDuration(200);
        log.setUserId("test-user-id");
        log.setUsername("测试用户");
        log.setIp("127.0.0.1");
        log.setOperationTime(new Date());

        SysLog savedLog = sysOpLogDao.save(log);
        assertNotNull(savedLog.getId());
        assertEquals("新测试操作", savedLog.getOperation());
        assertEquals("新测试结果", savedLog.getResult());
    }

    @Test
    public void testDelete() {
        String logId = testLog.getId();
        sysOpLogDao.delete(testLog);

        SysLog deletedLog = sysOpLogDao.findById(logId);
        assertNull(deletedLog);
    }

    @Test
    public void testFindById() {
        SysLog foundLog = sysOpLogDao.findById(testLog.getId());
        assertNotNull(foundLog);
        assertEquals("测试操作", foundLog.getOperation());

        // 测试不存在的ID
        SysLog nonExistentLog = sysOpLogDao.findById("non-existent-id");
        assertNull(nonExistentLog);
    }

    @Test
    public void testExistsById() {
        boolean exists = sysOpLogDao.existsById(testLog.getId());
        assertTrue(exists);

        // 测试不存在的ID
        boolean nonExists = sysOpLogDao.existsById("non-existent-id");
        assertFalse(nonExists);
    }

    @Test
    public void testCount() {
        long count = sysOpLogDao.count();
        assertEquals(1, count);

        // 添加一个日志后再次计数
        SysLog newLog = new SysLog();
        newLog.setOperation("计数测试操作");
        newLog.setParams("{\"param\": \"value\"}");
        newLog.setResult("计数测试结果");
        newLog.setSuccess(true);
        newLog.setDuration(150);
        newLog.setUserId("test-user-id");
        newLog.setUsername("测试用户");
        newLog.setIp("127.0.0.1");
        newLog.setOperationTime(new Date());
        sysOpLogDao.save(newLog);

        long newCount = sysOpLogDao.count();
        assertEquals(2, newCount);
    }

    @Test
    public void testFindAll() {
        List<SysLog> logs = sysOpLogDao.findAll();
        assertEquals(1, logs.size());
        assertEquals("测试操作", logs.get(0).getOperation());
    }

}
