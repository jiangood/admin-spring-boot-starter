
package io.admin.modules.system.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import io.admin.framework.log.Log;
import io.admin.common.dto.AjaxResult;
import io.admin.common.utils.tree.TreeTool;
import io.admin.common.utils.tree.drag.DragDropEvent;
import io.admin.common.utils.tree.drag.TreeDragTool;
import io.admin.modules.system.entity.OrgType;
import io.admin.modules.system.entity.SysOrg;
import io.admin.modules.system.service.SysOrgService;
import io.admin.framework.config.security.HasPermission;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 组织机构控制器
 */
@RestController
@RequestMapping("admin/sysOrg")
@Slf4j
public class SysOrgController {

    @Resource
    private SysOrgService sysOrgService;


    @Log("机构-保存")
    @HasPermission("sysOrg:save")
    @PostMapping("save")
    public AjaxResult saveOrUpdate(@RequestBody SysOrg sysOrg) {
        if(sysOrg.getLeader() != null){
            if(StrUtil.isEmpty(sysOrg.getLeader().getId())){
                sysOrg.setLeader(null);
            }
        }
        sysOrgService.saveOrUpdate(sysOrg);
        return AjaxResult.ok().msg("保存机构成功");
    }

    @Log("机构-删除")
    @HasPermission("sysOrg:delete")
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody SysOrg sysOrg, HttpSession session) {
        sysOrgService.deleteByRequest(sysOrg.getId());
        return AjaxResult.ok().msg("删除机构成功");
    }

    @HasPermission("sysOrg:view")
    @GetMapping("detail")
    public AjaxResult detail(String id) {
        SysOrg org = sysOrgService.findOneByRequest(id);
        return AjaxResult.ok().data(org);
    }

    @Log("机构-启用")
    @HasPermission("sysOrg:save")
    @GetMapping("enableAll")
    public AjaxResult enableAll(String id) {
        sysOrgService.toggleAllStatus(id, true);
        return AjaxResult.ok();
    }

    @Log("机构-禁用")
    @HasPermission("sysOrg:save")
    @GetMapping("disableAll")
    public AjaxResult disableAll(String id) {
        sysOrgService.toggleAllStatus(id, false);
        return AjaxResult.ok();
    }



    @Data
    public static class PageParam{
        boolean showDisabled;
        boolean showDept;
    }

    /**
     * 管理页面的树，包含禁用的
     *
     * @return
     */
    @HasPermission("sysOrg:view")
    @RequestMapping("pageTree")
    public AjaxResult pageTree(@RequestBody PageParam param, String searchText) {

        List<SysOrg> list = sysOrgService.findByLoginUser(param.showDept  , param.showDisabled);

        if(StrUtil.isNotEmpty(searchText)){
            list = list.stream().filter(t -> t.getName().contains(searchText)).collect(Collectors.toList());
        }



        return AjaxResult.ok().data(list2Tree(list));
    }


    private String getIconByType(int type) {
        switch (type) {
            case OrgType.UNIT -> {
                return "ApartmentOutlined";
            }
            case OrgType.DEPT -> {
                return "BorderOutlined";
            }

        }
        return "";
    }


    @PostMapping("sort")
    @HasPermission("sysOrg:save")
    public AjaxResult sort(@RequestBody DragDropEvent e) {
        List<SysOrg> nodes = sysOrgService.findAll();
        List<SysOrg> list = TreeDragTool.onDrop(e, nodes);
        for (int i = 0; i < list.size(); i++) {
            SysOrg sysOrg = list.get(i);
            sysOrg.setSeq(i);
        }

        return AjaxResult.ok().msg("排序成功");
    }


    @GetMapping("allTree")
    public AjaxResult allTree() throws Exception {
        List<SysOrg> list = this.sysOrgService.findByLoginUser(true, true);

        return AjaxResult.ok().data(list2Tree(list));
    }



    @GetMapping("unitTree")
    public AjaxResult unitTree() throws Exception {
        List<SysOrg> list = this.sysOrgService.findByLoginUser(false, false);

        list = list.stream().filter((o) -> !o.isDept()).collect(Collectors.toList());


        return AjaxResult.ok().data(list2Tree(list));
    }

    @GetMapping("deptTree")
    public AjaxResult deptTree() {
        List<SysOrg> list = this.sysOrgService.findByLoginUser(true, false);

        return AjaxResult.ok().data(list2Tree(list));
    }


    public List<Dict> list2Tree(List<SysOrg> list){
        List<Dict> treeList = list.stream().map(o -> {
            String title = o.getName();
            if (!o.getEnabled()) {
                title = title + " [禁用]";
            }
            String pid = o.getPid();

            Dict d = new Dict();
            d.set("title", title);
            d.set("key", o.getId());
            d.set("parentKey", pid);
            d.set("iconName", getIconByType(o.getType()));

            // 兼容选择框
            d.set("value", o.getId());
            d.set("label", o.getName());

            // 兼容treeUtil工具
            d.set("id", o.getId());
            d.set("pid", pid);

            return d;
        }).toList();


        return TreeTool.buildTree(treeList);
    }

}
