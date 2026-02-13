package io.github.jiangood.openadmin.modules.system.dao;

import io.github.jiangood.openadmin.modules.system.entity.SysDictItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class SysDictItemDaoTest {

    @Autowired
    private SysDictItemDao sysDictItemDao;

    private SysDictItem testDictItem;

    @BeforeEach
    public void setUp() {
        // 清理测试数据
        sysDictItemDao.deleteAll();

        // 创建测试字典项
        testDictItem = new SysDictItem();
        testDictItem.setTypeCode("test_type");
        testDictItem.setCode("test_code");
        testDictItem.setName("测试字典项");
        testDictItem.setEnabled(true);
        testDictItem.setSeq(1);
        testDictItem.setColor("#FF0000");
        testDictItem = sysDictItemDao.save(testDictItem);
    }

    @Test
    public void testSave() {
        SysDictItem dictItem = new SysDictItem();
        dictItem.setTypeCode("test_type");
        dictItem.setCode("new_test_code");
        dictItem.setName("新测试字典项");
        dictItem.setEnabled(true);
        dictItem.setSeq(2);
        dictItem.setColor("#00FF00");

        SysDictItem savedDictItem = sysDictItemDao.save(dictItem);
        assertNotNull(savedDictItem.getId());
        assertEquals("new_test_code", savedDictItem.getCode());
        assertEquals("新测试字典项", savedDictItem.getName());
    }

    @Test
    public void testUpdateField() {
        // 修改字典项的名称和颜色
        testDictItem.setName("更新后的测试字典项");
        testDictItem.setColor("#0000FF");
        sysDictItemDao.updateField(testDictItem, List.of("name", "color"));

        // 重新查询，验证更新是否成功
        SysDictItem updatedDictItem = sysDictItemDao.findOne(testDictItem.getId());
        assertNotNull(updatedDictItem);
        assertEquals("更新后的测试字典项", updatedDictItem.getName());
        assertEquals("#0000FF", updatedDictItem.getColor());
    }

    @Test
    public void testDelete() {
        String dictItemId = testDictItem.getId();
        sysDictItemDao.delete(testDictItem);

        SysDictItem deletedDictItem = sysDictItemDao.findOne(dictItemId);
        assertNull(deletedDictItem);
    }

    @Test
    public void testFindById() {
        SysDictItem foundDictItem = sysDictItemDao.findOne(testDictItem.getId());
        assertNotNull(foundDictItem);
        assertEquals("测试字典项", foundDictItem.getName());

        // 测试不存在的ID
        SysDictItem nonExistentDictItem = sysDictItemDao.findOne("non-existent-id");
        assertNull(nonExistentDictItem);
    }

    @Test
    public void testExistsById() {
        boolean exists = sysDictItemDao.existsById(testDictItem.getId());
        assertTrue(exists);

        // 测试不存在的ID
        boolean nonExists = sysDictItemDao.existsById("non-existent-id");
        assertFalse(nonExists);
    }

    @Test
    public void testCount() {
        long count = sysDictItemDao.count();
        assertEquals(1, count);

        // 添加一个字典项后再次计数
        SysDictItem newDictItem = new SysDictItem();
        newDictItem.setTypeCode("test_type");
        newDictItem.setCode("count_test_code");
        newDictItem.setName("计数测试字典项");
        newDictItem.setEnabled(true);
        sysDictItemDao.save(newDictItem);

        long newCount = sysDictItemDao.count();
        assertEquals(2, newCount);
    }

    @Test
    public void testFindAll() {
        List<SysDictItem> dictItems = sysDictItemDao.findAll();
        assertEquals(1, dictItems.size());
        assertEquals("测试字典项", dictItems.get(0).getName());
    }

}
