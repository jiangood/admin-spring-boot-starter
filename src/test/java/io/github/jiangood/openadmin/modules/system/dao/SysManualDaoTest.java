package io.github.jiangood.openadmin.modules.system.dao;

import io.github.jiangood.openadmin.modules.system.entity.SysManual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class SysManualDaoTest {

    @Autowired
    private SysManualDao sysManualDao;

    private SysManual testManual;

    @BeforeEach
    public void setUp() {
        // 清理测试数据
        sysManualDao.deleteAll();

        // 创建测试操作手册
        testManual = new SysManual();
        testManual.setName("测试操作手册");
        testManual.setVersion(1);
        testManual.setFileId("test-file-id");
        testManual = sysManualDao.save(testManual);
    }

    @Test
    public void testSave() {
        SysManual manual = new SysManual();
        manual.setName("新测试操作手册");
        manual.setVersion(1);
        manual.setFileId("new-test-file-id");

        SysManual savedManual = sysManualDao.save(manual);
        assertNotNull(savedManual.getId());
        assertEquals("新测试操作手册", savedManual.getName());
        assertEquals(1, savedManual.getVersion());
    }

    @Test
    public void testFindMaxVersion() {
        // 测试存在的操作手册名称
        int maxVersion = sysManualDao.findMaxVersion("测试操作手册");
        assertEquals(1, maxVersion);

        // 添加一个更高版本的操作手册
        SysManual higherVersionManual = new SysManual();
        higherVersionManual.setName("测试操作手册");
        higherVersionManual.setVersion(2);
        higherVersionManual.setFileId("test-file-id-2");
        sysManualDao.save(higherVersionManual);

        // 再次测试最大版本号
        int newMaxVersion = sysManualDao.findMaxVersion("测试操作手册");
        assertEquals(2, newMaxVersion);

        // 测试不存在的操作手册名称
        int nonExistentMaxVersion = sysManualDao.findMaxVersion("不存在的操作手册");
        assertEquals(0, nonExistentMaxVersion);
    }

    @Test
    public void testDelete() {
        String manualId = testManual.getId();
        sysManualDao.delete(testManual);

        SysManual deletedManual = sysManualDao.findById(manualId);
        assertNull(deletedManual);
    }

    @Test
    public void testFindById() {
        SysManual foundManual = sysManualDao.findById(testManual.getId());
        assertNotNull(foundManual);
        assertEquals("测试操作手册", foundManual.getName());

        // 测试不存在的ID
        SysManual nonExistentManual = sysManualDao.findById("non-existent-id");
        assertNull(nonExistentManual);
    }

    @Test
    public void testExistsById() {
        boolean exists = sysManualDao.existsById(testManual.getId());
        assertTrue(exists);

        // 测试不存在的ID
        boolean nonExists = sysManualDao.existsById("non-existent-id");
        assertFalse(nonExists);
    }

    @Test
    public void testCount() {
        long count = sysManualDao.count();
        assertEquals(1, count);

        // 添加一个操作手册后再次计数
        SysManual newManual = new SysManual();
        newManual.setName("计数测试操作手册");
        newManual.setVersion(1);
        newManual.setFileId("count-test-file-id");
        sysManualDao.save(newManual);

        long newCount = sysManualDao.count();
        assertEquals(2, newCount);
    }

    @Test
    public void testFindAll() {
        List<SysManual> manuals = sysManualDao.findAll();
        assertEquals(1, manuals.size());
        assertEquals("测试操作手册", manuals.get(0).getName());
    }

}
