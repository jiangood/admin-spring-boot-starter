package io.github.jiangood.openadmin.modules.system.dao;

import io.github.jiangood.openadmin.framework.config.datadefinition.MenuDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class SysMenuDaoTest {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Test
    public void testFindAll() {
        List<MenuDefinition> menus = sysMenuDao.findAll();
        assertNotNull(menus);
        // 确保至少有一个菜单
        assertFalse(menus.isEmpty());
    }

    @Test
    public void testFindAllEnabled() {
        List<MenuDefinition> enabledMenus = sysMenuDao.findAllEnabled();
        assertNotNull(enabledMenus);
        // 确保返回的菜单都是启用的
        for (MenuDefinition menu : enabledMenus) {
            assertNotEquals(Boolean.TRUE, menu.getDisabled());
        }
    }

    @Test
    public void testFindAllById() {
        // 先获取所有菜单，然后测试findAllById方法
        List<MenuDefinition> allMenus = sysMenuDao.findAll();
        assertNotNull(allMenus);
        assertFalse(allMenus.isEmpty());

        // 测试存在的菜单ID
        List<String> menuIds = allMenus.stream().limit(2).map(MenuDefinition::getId).toList();
        List<MenuDefinition> foundMenus = sysMenuDao.findAllById(menuIds);
        assertNotNull(foundMenus);
        assertEquals(menuIds.size(), foundMenus.size());

        // 测试不存在的菜单ID
        List<String> nonExistentIds = List.of("non-existent-id-1", "non-existent-id-2");
        List<MenuDefinition> nonExistentMenus = sysMenuDao.findAllById(nonExistentIds);
        assertNotNull(nonExistentMenus);
        assertTrue(nonExistentMenus.isEmpty());
    }

}
