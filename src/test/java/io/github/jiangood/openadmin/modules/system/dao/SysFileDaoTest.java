package io.github.jiangood.openadmin.modules.system.dao;

import io.github.jiangood.openadmin.lang.enums.MaterialType;
import io.github.jiangood.openadmin.modules.system.entity.SysFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class SysFileDaoTest {

    @Autowired
    private SysFileDao sysFileDao;

    private SysFile testFile;

    @BeforeEach
    public void setUp() {
        // 清理测试数据
        sysFileDao.deleteAll();

        // 创建测试文件
        testFile = new SysFile();
        testFile.setTradeNo("test-trade-no");
        testFile.setOriginName("测试文件.txt");
        testFile.setObjectName("2024/test-file.txt");
        testFile.setSuffix("txt");
        testFile.setSize(1024L);
        testFile.setMimeType("text/plain");
        testFile.setType(MaterialType.OTHER);
        testFile.setTitle("测试文件");
        testFile.setDescription("这是一个测试文件");
        testFile = sysFileDao.save(testFile);
    }

    @Test
    public void testSave() {
        SysFile file = new SysFile();
        file.setTradeNo("new-test-trade-no");
        file.setOriginName("新测试文件.txt");
        file.setObjectName("2024/new-test-file.txt");
        file.setSuffix("txt");
        file.setSize(2048L);
        file.setMimeType("text/plain");
        file.setType(MaterialType.OTHER);
        file.setTitle("新测试文件");
        file.setDescription("这是一个新测试文件");

        SysFile savedFile = sysFileDao.save(file);
        assertNotNull(savedFile.getId());
        assertEquals("new-test-trade-no", savedFile.getTradeNo());
        assertEquals("新测试文件.txt", savedFile.getOriginName());
    }

    @Test
    public void testFindByTradeNo() {
        SysFile foundFile = sysFileDao.findByTradeNo("test-trade-no");
        assertNotNull(foundFile);
        assertEquals("测试文件.txt", foundFile.getOriginName());

        // 测试不存在的交易号
        SysFile nonExistentFile = sysFileDao.findByTradeNo("non-existent");
        assertNull(nonExistentFile);
    }

    @Test
    public void testDelete() {
        String fileId = testFile.getId();
        sysFileDao.delete(testFile);

        SysFile deletedFile = sysFileDao.findById(fileId);
        assertNull(deletedFile);
    }

    @Test
    public void testFindById() {
        SysFile foundFile = sysFileDao.findById(testFile.getId());
        assertNotNull(foundFile);
        assertEquals("测试文件.txt", foundFile.getOriginName());

        // 测试不存在的ID
        SysFile nonExistentFile = sysFileDao.findById("non-existent-id");
        assertNull(nonExistentFile);
    }

    @Test
    public void testExistsById() {
        boolean exists = sysFileDao.existsById(testFile.getId());
        assertTrue(exists);

        // 测试不存在的ID
        boolean nonExists = sysFileDao.existsById("non-existent-id");
        assertFalse(nonExists);
    }

    @Test
    public void testCount() {
        long count = sysFileDao.count();
        assertEquals(1, count);

        // 添加一个文件后再次计数
        SysFile newFile = new SysFile();
        newFile.setTradeNo("count-test-trade-no");
        newFile.setOriginName("计数测试文件.txt");
        newFile.setObjectName("2024/count-test-file.txt");
        newFile.setSuffix("txt");
        newFile.setSize(512L);
        newFile.setMimeType("text/plain");
        newFile.setType(MaterialType.OTHER);
        sysFileDao.save(newFile);

        long newCount = sysFileDao.count();
        assertEquals(2, newCount);
    }

    @Test
    public void testFindAll() {
        List<SysFile> files = sysFileDao.findAll();
        assertEquals(1, files.size());
        assertEquals("测试文件.txt", files.get(0).getOriginName());
    }

}
