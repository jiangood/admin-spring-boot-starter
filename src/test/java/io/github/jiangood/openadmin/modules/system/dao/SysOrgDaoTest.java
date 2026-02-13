package io.github.jiangood.openadmin.modules.system.dao;

import io.github.jiangood.openadmin.lang.tree.TreeManager;
import io.github.jiangood.openadmin.modules.system.entity.SysOrg;
import io.github.jiangood.openadmin.modules.system.enums.OrgType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class SysOrgDaoTest {

    @Autowired
    private SysOrgDao sysOrgDao;

    private SysOrg testOrg;
    private SysOrg testDept;

    @BeforeEach
    public void setUp() {
        // 清理测试数据
        sysOrgDao.deleteAll();

        // 创建测试机构
        testOrg = new SysOrg();
        testOrg.setName("测试机构");
        testOrg.setType(OrgType.TYPE_UNIT);
        testOrg.setEnabled(true);
        testOrg.setSeq(1);
        testOrg = sysOrgDao.save(testOrg);

        // 创建测试部门
        testDept = new SysOrg();
        testDept.setName("测试部门");
        testDept.setType(OrgType.TYPE_DEPT);
        testDept.setPid(testOrg.getId());
        testDept.setEnabled(true);
        testDept.setSeq(1);
        testDept = sysOrgDao.save(testDept);
    }

    @Test
    public void testSave() {
        SysOrg org = new SysOrg();
        org.setName("新测试机构");
        org.setType(OrgType.TYPE_UNIT);
        org.setEnabled(true);
        org.setSeq(2);

        SysOrg savedOrg = sysOrgDao.save(org);
        assertNotNull(savedOrg.getId());
        assertEquals("新测试机构", savedOrg.getName());
    }

    @Test
    public void testFindByThirdId() {
        // 设置第三方ID
        testOrg.setThirdId("third-test-id");
        sysOrgDao.save(testOrg);

        SysOrg foundOrg = sysOrgDao.findByThirdId("third-test-id");
        assertNotNull(foundOrg);
        assertEquals("测试机构", foundOrg.getName());

        // 测试不存在的第三方ID
        SysOrg nonExistentOrg = sysOrgDao.findByThirdId("non-existent");
        assertNull(nonExistentOrg);
    }

    @Test
    public void testGetTreeManager() {
        TreeManager<SysOrg> treeManager = sysOrgDao.getTreeManager();
        assertNotNull(treeManager);
        // 测试树管理器是否包含我们创建的机构
        assertTrue(treeManager.getMap().containsKey(testOrg.getId()));
        assertTrue(treeManager.getMap().containsKey(testDept.getId()));
    }

    @Test
    public void testCheckIsLeaf() {
        // 测试机构是否是叶子节点（应该是，因为它只有一个部门子节点）
        boolean isLeaf = sysOrgDao.checkIsLeaf(testOrg.getId());
        assertFalse(isLeaf);

        // 测试部门是否是叶子节点（应该是，因为它没有子节点）
        boolean isDeptLeaf = sysOrgDao.checkIsLeaf(testDept.getId());
        assertTrue(isDeptLeaf);
    }

    @Test
    public void testFindDirectChildUnit() {
        // 创建一个子机构
        SysOrg childUnit = new SysOrg();
        childUnit.setName("子测试机构");
        childUnit.setType(OrgType.TYPE_UNIT);
        childUnit.setPid(testOrg.getId());
        childUnit.setEnabled(true);
        childUnit.setSeq(1);
        sysOrgDao.save(childUnit);

        // 测试查询直接下级公司
        List<SysOrg> childUnits = sysOrgDao.findDirectChildUnit(testOrg.getId(), true);
        assertEquals(1, childUnits.size());
        assertEquals("子测试机构", childUnits.get(0).getName());
    }

    @Test
    public void testFindDirectChildUnitId() {
        // 创建一个子机构
        SysOrg childUnit = new SysOrg();
        childUnit.setName("子测试机构");
        childUnit.setType(OrgType.TYPE_UNIT);
        childUnit.setPid(testOrg.getId());
        childUnit.setEnabled(true);
        childUnit.setSeq(1);
        childUnit = sysOrgDao.save(childUnit);

        // 测试查询直接下级公司ID
        List<String> childUnitIds = sysOrgDao.findDirectChildUnitId(testOrg.getId());
        assertEquals(1, childUnitIds.size());
        assertEquals(childUnit.getId(), childUnitIds.get(0));
    }

    @Test
    public void testFindLevelById() {
        int orgLevel = sysOrgDao.findLevelById(testOrg.getId());
        int deptLevel = sysOrgDao.findLevelById(testDept.getId());
        assertTrue(orgLevel < deptLevel);
    }

    @Test
    public void testFindParentUnit() {
        // 测试查找部门的上级单位
        SysOrg parentUnit = sysOrgDao.findParentUnit(testDept);
        assertNotNull(parentUnit);
        assertEquals("测试机构", parentUnit.getName());

        // 测试查找机构的上级单位（应该是null）
        SysOrg orgParentUnit = sysOrgDao.findParentUnit(testOrg);
        assertNull(orgParentUnit);
    }

    @Test
    public void testFindParentUnitId() {
        // 测试查找部门的上级单位ID
        String parentUnitId = sysOrgDao.findParentUnitId(testDept, 1);
        assertNotNull(parentUnitId);
        assertEquals(testOrg.getId(), parentUnitId);
    }

    @Test
    public void testFindUnit() {
        // 测试查找部门对应的单位
        SysOrg unit = sysOrgDao.findUnit(testDept);
        assertNotNull(unit);
        assertEquals("测试机构", unit.getName());

        // 测试查找单位对应的单位（应该是自身）
        SysOrg orgUnit = sysOrgDao.findUnit(testOrg);
        assertNotNull(orgUnit);
        assertEquals(testOrg.getId(), orgUnit.getId());
    }

    @Test
    public void testGetParentIdListById() {
        List<String> parentIds = sysOrgDao.getParentIdListById(testDept.getId());
        assertTrue(parentIds.contains(testOrg.getId()));
    }

    @Test
    public void testDict() {
        Map<String, SysOrg> dict = sysOrgDao.dict();
        assertNotNull(dict);
        assertTrue(dict.containsKey(testOrg.getId()));
        assertTrue(dict.containsKey(testDept.getId()));
    }

    @Test
    public void testGetNameById() {
        String orgName = sysOrgDao.getNameById(testOrg.getId());
        assertEquals("测试机构", orgName);

        String deptName = sysOrgDao.getNameById(testDept.getId());
        assertEquals("测试部门", deptName);
    }

    @Test
    public void testFindAllValid() {
        List<SysOrg> validOrgs = sysOrgDao.findAllValid();
        assertEquals(2, validOrgs.size());
    }

    @Test
    public void testFindChildIdListWithSelfById() {
        List<String> childIdsWithSelf = sysOrgDao.findChildIdListWithSelfById(testOrg.getId());
        assertTrue(childIdsWithSelf.contains(testOrg.getId()));
        assertTrue(childIdsWithSelf.contains(testDept.getId()));
    }

    @Test
    public void testFindChildIdListById() {
        List<String> childIds = sysOrgDao.findChildIdListById(testOrg.getId());
        assertTrue(childIds.contains(testDept.getId()));
        assertFalse(childIds.contains(testOrg.getId()));
    }

    @Test
    public void testFindByPid() {
        List<SysOrg> depts = sysOrgDao.findByPid(testOrg.getId());
        assertEquals(1, depts.size());
        assertEquals("测试部门", depts.get(0).getName());

        // 测试查找顶级机构
        List<SysOrg> topOrgs = sysOrgDao.findByPid(null);
        assertEquals(1, topOrgs.size());
        assertEquals("测试机构", topOrgs.get(0).getName());
    }

    @Test
    public void testCleanCache() {
        // 测试清理缓存方法是否正常执行
        sysOrgDao.cleanCache();
        // 没有异常抛出即表示测试通过
    }

}

