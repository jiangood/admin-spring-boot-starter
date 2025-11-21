package io.admin.modules.api.controller;

import io.admin.modules.api.service.ApiAccountResourceService;
import io.admin.framework.log.Log;
import io.admin.framework.config.argument.RequestBodyKeys;
import io.admin.framework.data.query.JpaQuery;
import io.admin.common.dto.AjaxResult;
import io.admin.modules.api.entity.ApiAccountResource;


import io.admin.framework.config.security.HasPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


import jakarta.annotation.Resource;

@RestController
@RequestMapping("admin/apiAccountResource")
public class ApiAccountResourceController {

    @Resource
    ApiAccountResourceService service;

    @HasPermission("apiAccountResource:view")
    @RequestMapping("page")
    public AjaxResult page(String searchText, String accountId, @PageableDefault(sort = "resource.action") Pageable pageable) throws Exception {
        JpaQuery<ApiAccountResource> q = new JpaQuery<>();
        q.eq(ApiAccountResource.Fields.account + ".id", accountId);
        q.like(ApiAccountResource.Fields.account + ".name", searchText);

        Page<ApiAccountResource> page = service.pageByRequest(q, pageable);

        return AjaxResult.ok().data(page);
    }


    @Log("接口账号-保存")
    @HasPermission("apiAccountResource:save")
    @PostMapping("save")
    public AjaxResult save(@RequestBody ApiAccountResource input, RequestBodyKeys updateFields) throws Exception {
        service.saveOrUpdateByRequest(input, updateFields);
        return AjaxResult.ok().msg("保存成功");
    }


    @Log("接口账号-删除")
    @HasPermission("apiAccountResource:delete")
    @RequestMapping("delete")
    public AjaxResult delete(String id) {
        service.deleteByRequest(id);
        return AjaxResult.ok().msg("删除成功");
    }


}

