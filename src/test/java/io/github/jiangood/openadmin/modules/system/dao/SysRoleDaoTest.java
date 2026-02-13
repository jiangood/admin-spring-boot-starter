package io.github.jiangood.openadmin.modules.system.dao;

import io.github.jiangood.openadmin.modules.system.entity.SysRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class SysRoleDaoTest {

    @Autowired
    private SysRoleDao sysRoleDao;

    private SysRole testRole;

    @BeforeEach
    public void setUp() {
        // 清理测试数据
        sysRoleDao.deleteAll();

        // 创建测试角色
        testRole = new SysRole();
        testRole.setName("测试角色");
        testRole.setCode("test_role");
        testRole.setSeq(1);
        testRole.setRemark("测试用角色");
        testRole.setEnabled(true);
        testRole.setBuiltin(false);
        testRole = sysRoleDao.save(testRole);
    }

    @Test
    public void testSave() {
        SysRole role = new SysRole();
        role.setName("新测试角色");
        role.setCode("new_test_role");
        role.setSeq(2);
        role.setRemark("新测试用角色");
        role.setEnabled(true);
        role.setBuiltin(false);

        SysRole savedRole = sysRoleDao.save(role);
        assertNotNull(savedRole.getId());
        assertEquals("new_test_role", savedRole.getCode());
        assertEquals("新测试角色", savedRole.getName());
    }

    @Test
    public void testFindByCode() {
        SysRole foundRole = sysRoleDao.findByCode("test_role");
        assertNotNull(foundRole);
        assertEquals("测试角色", foundRole.getName());

        // 测试不存在的编码
        SysRole nonExistentRole = sysRoleDao.findByCode("non-existent");
        assertNull(nonExistentRole);
    }

    @Test
    public void testCountByCode() {
        long count = sysRoleDao.countByCode("test_role");
        assertEquals(1, count);

        // 测试不存在的编码
        long nonExistentCount = sysRoleDao.countByCode("non-existent");
        assertEquals(0, nonExistentCount);
    }

    @Test
    public void testDelete() {
        String roleId = testRole.getId();
        sysRoleDao.delete(testRole);

        SysRole deletedRole = sysRoleDao.findById(roleId);
        assertNull(deletedRole);
    }

    @Test
    public void testFindById() {
        SysRole foundRole = sysRoleDao.findById(testRole.getId());
        assertNotNull(foundRole);
        assertEquals("测试角色", foundRole.getName());

        // 测试不存在的ID
        SysRole nonExistentRole = sysRoleDao.findById("non-existent-id");
        assertNull(nonExistentRole);
    }

    @Test
    public void testExistsById() {
        boolean exists = sysRoleDao.existsById(testRole.getId());
        assertTrue(exists);

        // 测试不存在的ID
        boolean nonExists = sysRoleDao.existsById("non-existent-id");
        assertFalse(nonExists);
    }

    @Test
    public void testCount() {
        long count = sysRoleDao.count();
        assertEquals(1, count);

        // 添加一个角色后再次计数
        SysRole newRole = new SysRole();
        newRole.setName("计数测试角色");
        newRole.setCode("count_test_role");
        newRole.setEnabled(true);
        newRole.setBuiltin(false);
        sysRoleDao.save(newRole);

        long newCount = sysRoleDao.count();
        assertEquals(2, newCount);
    }

    @Test
    public void testFindAll() {
        List<SysRole> roles = sysRoleDao.findAll();
        assertEquals(1, roles.size());
        assertEquals("测试角色", roles.get(0).getName());
    }

}
