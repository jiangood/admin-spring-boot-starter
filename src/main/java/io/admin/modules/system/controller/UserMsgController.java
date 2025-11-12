package io.admin.modules.system.controller;


import io.admin.modules.system.service.SysUserMessageService;
import io.admin.common.dto.AjaxResult;
import io.admin.modules.common.LoginTool;
import io.admin.modules.system.entity.SysUserMessage;
import io.admin.framework.perm.SecurityUtils;
import io.admin.framework.data.domain.BaseEntity;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("admin/user/msg")
public class UserMsgController {


    @Resource
    SysUserMessageService sysUserMsgService;


    @RequestMapping("page")
    public AjaxResult page(Boolean read, @PageableDefault(sort = "createTime", direction = Sort.Direction.DESC) Pageable pageable) throws SQLException {
        String id = SecurityUtils.getSubject().getId();
        Page<SysUserMessage> page = sysUserMsgService.findByUser(id, read, pageable);
        return AjaxResult.ok().data(page);
    }

    @PostMapping("read")
    public AjaxResult read(@RequestBody SysUserMessage msg) {
        sysUserMsgService.read(msg.getId());

        return AjaxResult.ok();
    }

    @GetMapping("getMessageCount")
    public AjaxResult getMessageCount() {
        String id = LoginTool.getLoginUser().getId();
        long sum = sysUserMsgService.countUnReadByUser(id);

        return AjaxResult.ok().data(sum);
    }

}
