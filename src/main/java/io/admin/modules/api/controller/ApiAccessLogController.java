package io.admin.modules.api.controller;

import io.admin.common.dto.AjaxResult;
import io.admin.framework.data.query.JpaQuery;
import io.admin.modules.api.entity.ApiAccessLog;
import io.admin.modules.api.service.ApiAccessLogService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/apiAccessLog")
public class ApiAccessLogController  {

    @Resource
    ApiAccessLogService service;



    @Deprecated
    @RequestMapping("page")
    public AjaxResult page(@PageableDefault(direction = Sort.Direction.DESC, sort = "updateTime") Pageable pageable) throws Exception {
        JpaQuery<ApiAccessLog> q = new JpaQuery<>();


        Page<ApiAccessLog> page = service.pageByRequest(q, pageable);



        return AjaxResult.ok().data(page);
    }






}

