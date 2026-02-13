package io.github.jiangood.openadmin.modules.system.dao;

import io.github.jiangood.openadmin.modules.system.entity.SysUser;
import io.github.jiangood.openadmin.modules.system.entity.SysUserMessage;
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
public class SysUserMessageDaoTest {

    @Autowired
    private SysUserMessageDao sysUserMessageDao;

    @Autowired
    private SysUserDao sysUserDao;

    private SysUserMessage testMessage;
    private SysUser testUser;

    @BeforeEach
    public void setUp() {
        // 清理测试数据
        sysUserMessageDao.deleteAll();
        sysUserDao.deleteAll();

        // 创建测试用户
        testUser = new SysUser();
        testUser.setAccount("testuser");
        testUser.setPassword("123456");
        testUser.setName("测试用户");
        testUser.setEnabled(true);
        testUser = sysUserDao.save(testUser);

        // 创建测试消息
        testMessage = new SysUserMessage();
        testMessage.setTitle("测试消息");
        testMessage.setContent("这是一条测试消息");
        testMessage.setUser(testUser);
        testMessage.setIsRead(false);
        testMessage = sysUserMessageDao.save(testMessage);
    }

    @Test
    public void testSave() {
        SysUserMessage message = new SysUserMessage();
        message.setTitle("新测试消息");
        message.setContent("这是一条新测试消息");
        message.setUser(testUser);
        message.setIsRead(false);

        SysUserMessage savedMessage = sysUserMessageDao.save(message);
        assertNotNull(savedMessage.getId());
        assertEquals("新测试消息", savedMessage.getTitle());
        assertEquals("这是一条新测试消息", savedMessage.getContent());
    }

    @Test
    public void testDelete() {
        String messageId = testMessage.getId();
        sysUserMessageDao.delete(testMessage);

        SysUserMessage deletedMessage = sysUserMessageDao.findById(messageId);
        assertNull(deletedMessage);
    }

    @Test
    public void testFindById() {
        SysUserMessage foundMessage = sysUserMessageDao.findById(testMessage.getId());
        assertNotNull(foundMessage);
        assertEquals("测试消息", foundMessage.getTitle());

        // 测试不存在的ID
        SysUserMessage nonExistentMessage = sysUserMessageDao.findById("non-existent-id");
        assertNull(nonExistentMessage);
    }

    @Test
    public void testExistsById() {
        boolean exists = sysUserMessageDao.existsById(testMessage.getId());
        assertTrue(exists);

        // 测试不存在的ID
        boolean nonExists = sysUserMessageDao.existsById("non-existent-id");
        assertFalse(nonExists);
    }

    @Test
    public void testCount() {
        long count = sysUserMessageDao.count();
        assertEquals(1, count);

        // 添加一个消息后再次计数
        SysUserMessage newMessage = new SysUserMessage();
        newMessage.setTitle("计数测试消息");
        newMessage.setContent("这是一条计数测试消息");
        newMessage.setUser(testUser);
        newMessage.setIsRead(false);
        sysUserMessageDao.save(newMessage);

        long newCount = sysUserMessageDao.count();
        assertEquals(2, newCount);
    }

    @Test
    public void testFindAll() {
        List<SysUserMessage> messages = sysUserMessageDao.findAll();
        assertEquals(1, messages.size());
        assertEquals("测试消息", messages.get(0).getTitle());
    }

    @Test
    public void testUpdateField() {
        // 标记消息为已读
        testMessage.setIsRead(true);
        testMessage.setReadTime(new Date());
        sysUserMessageDao.updateField(testMessage, List.of("isRead", "readTime"));

        // 重新查询，验证更新是否成功
        SysUserMessage updatedMessage = sysUserMessageDao.findById(testMessage.getId());
        assertNotNull(updatedMessage);
        assertTrue(updatedMessage.getIsRead());
        assertNotNull(updatedMessage.getReadTime());
    }

}
