package io.github.jiangood.openadmin.modules.system.dao;

import io.github.jiangood.openadmin.modules.system.entity.SysUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class SysUserDaoTest {

    @Autowired
    private SysUserDao sysUserDao;

    private SysUser testUser;

    @BeforeEach
    public void setUp() {
        // 清理测试数据
        sysUserDao.deleteAll();

        // 创建测试用户
        testUser = new SysUser();
        testUser.setAccount("testuser");
        testUser.setPassword("123456");
        testUser.setName("测试用户");
        testUser.setPhone("13800138000");
        testUser.setEmail("test@example.com");
        testUser.setEnabled(true);
        testUser = sysUserDao.save(testUser);
    }

    @Test
    public void testSave() {
        SysUser user = new SysUser();
        user.setAccount("newuser");
        user.setPassword("123456");
        user.setName("新测试用户");
        user.setPhone("13800138001");
        user.setEmail("newtest@example.com");
        user.setEnabled(true);

        SysUser savedUser = sysUserDao.save(user);
        assertNotNull(savedUser.getId());
        assertEquals("newuser", savedUser.getAccount());
        assertEquals("新测试用户", savedUser.getName());
    }

    @Test
    public void testFindByAccount() {
        SysUser foundUser = sysUserDao.findByAccount("testuser");
        assertNotNull(foundUser);
        assertEquals("测试用户", foundUser.getName());

        // 测试不存在的账号
        SysUser nonExistentUser = sysUserDao.findByAccount("non-existent");
        assertNull(nonExistentUser);
    }

    @Test
    public void testFindValid() {
        // 创建一个禁用的用户
        SysUser disabledUser = new SysUser();
        disabledUser.setAccount("disableduser");
        disabledUser.setPassword("123456");
        disabledUser.setName("禁用用户");
        disabledUser.setEnabled(false);
        sysUserDao.save(disabledUser);

        // 测试查询所有有效用户
        List<SysUser> validUsers = sysUserDao.findValid();
        assertEquals(1, validUsers.size());
        assertEquals("testuser", validUsers.get(0).getAccount());

        // 测试按ID集合查询有效用户
        List<SysUser> validUsersByIds = sysUserDao.findValid(Set.of(testUser.getId(), disabledUser.getId()));
        assertEquals(1, validUsersByIds.size());
        assertEquals("testuser", validUsersByIds.get(0).getAccount());
    }

    @Test
    public void testGetNameById() {
        // 测试存在的用户ID
        String userName = sysUserDao.getNameById(testUser.getId());
        assertEquals("测试用户", userName);

        // 测试不存在的用户ID
        String nonExistentUserName = sysUserDao.getNameById("non-existent-id");
        assertNull(nonExistentUserName);

        // 测试缓存
        String cachedUserName = sysUserDao.getNameById(testUser.getId());
        assertEquals("测试用户", cachedUserName);
    }

    @Test
    public void testDelete() {
        String userId = testUser.getId();
        sysUserDao.delete(testUser);

        SysUser deletedUser = sysUserDao.findById(userId);
        assertNull(deletedUser);
    }

    @Test
    public void testFindById() {
        SysUser foundUser = sysUserDao.findById(testUser.getId());
        assertNotNull(foundUser);
        assertEquals("测试用户", foundUser.getName());

        // 测试不存在的ID
        SysUser nonExistentUser = sysUserDao.findById("non-existent-id");
        assertNull(nonExistentUser);
    }

    @Test
    public void testExistsById() {
        boolean exists = sysUserDao.existsById(testUser.getId());
        assertTrue(exists);

        // 测试不存在的ID
        boolean nonExists = sysUserDao.existsById("non-existent-id");
        assertFalse(nonExists);
    }

    @Test
    public void testCount() {
        long count = sysUserDao.count();
        assertEquals(1, count);

        // 添加一个用户后再次计数
        SysUser newUser = new SysUser();
        newUser.setAccount("counttest");
        newUser.setPassword("123456");
        newUser.setName("计数测试用户");
        newUser.setEnabled(true);
        sysUserDao.save(newUser);

        long newCount = sysUserDao.count();
        assertEquals(2, newCount);
    }

}
