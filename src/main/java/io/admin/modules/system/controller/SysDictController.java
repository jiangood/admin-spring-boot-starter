
package io.admin.modules.system.controller;

import io.admin.framework.data.query.JpaQuery;
import io.admin.framework.config.security.HasPermission;
import io.admin.framework.config.argument.RequestBodyKeys;
import io.admin.common.dto.AjaxResult;
import io.admin.modules.system.entity.SysDict;
import io.admin.modules.system.service.SysDictService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/sysDict")
public class SysDictController  {

    @Resource
    private SysDictService service;

    @HasPermission("sysDict:view")
    @RequestMapping("page")
    public AjaxResult page(String searchText, @PageableDefault(direction = Sort.Direction.DESC, sort = "updateTime") Pageable pageable) throws Exception {
        JpaQuery<SysDict> q = new JpaQuery<>();
        q.searchText(searchText, "text","code");

        Page<SysDict> page = service.findPageByRequest(q, pageable);

        return AjaxResult.ok().data(page);
    }

    @HasPermission("sysDict:save")
    @PostMapping("save")
    public AjaxResult save(@RequestBody SysDict input, RequestBodyKeys updateFields) throws Exception {
        service.saveOrUpdateByRequest(input, updateFields);
        return AjaxResult.ok().msg("保存成功");
    }

    @HasPermission("sysDict:delete")
    @RequestMapping("delete")
    public AjaxResult delete(String id) {
        service.deleteByRequest(id);
        return AjaxResult.ok().msg("删除成功");
    }





}
