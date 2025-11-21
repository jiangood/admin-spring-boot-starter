package io.admin.modules.job.controller;

import io.admin.modules.job.entity.SysJob;
import io.admin.modules.job.entity.SysJobLog;
import io.admin.modules.job.service.SysJobLogService;
import io.admin.modules.job.service.SysJobService;
import io.admin.framework.log.Log;
import io.admin.framework.data.query.JpaQuery;
import io.admin.common.dto.AjaxResult;
import io.admin.framework.config.security.HasPermission;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.util.List;

@RestController
@RequestMapping("admin/job")
public class SysJobLogController {

    @Resource
    private SysJobLogService service;

    @Resource
    private SysJobService sysJobService;

    @RequestMapping("jobLog")
    public AjaxResult page(String searchText, String jobId, @PageableDefault(direction = Sort.Direction.DESC, sort = "updateTime") Pageable pageable) {
        JpaQuery<SysJobLog> q = new JpaQuery<>();
        q.searchText(searchText, SysJobLog.Fields.sysJob + "." + SysJob.Fields.name);
        q.eq(SysJobLog.Fields.sysJob + ".id", jobId);

        Page<SysJobLog> page = service.pageByRequest(q, pageable);
        return AjaxResult.ok().data(page);
    }

    @Data
    public static class CleanParam {
        @NotEmpty
        List<String> ids;
    }

    @Log("作业-清理日志")
    @HasPermission("job:jobLogClean")
    @PostMapping("jobLogClean")
    public AjaxResult clean(@RequestBody CleanParam param) {
        List<String> ids = param.getIds();
        sysJobService.clean(ids);
        return AjaxResult.ok().msg("删除成功");
    }


}
