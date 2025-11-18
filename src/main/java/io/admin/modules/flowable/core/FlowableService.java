package io.admin.modules.flowable.core;


import io.admin.common.utils.FriendlyUtils;
import io.admin.common.utils.SpringTool;
import io.admin.modules.flowable.core.assignment.AssignmentTypeProvider;
import io.admin.modules.flowable.core.dto.response.TaskResponse;
import io.admin.modules.system.service.SysUserService;
import lombok.AllArgsConstructor;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskInfo;
import org.flowable.task.api.TaskQuery;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FlowableService {

    private TaskService taskService;
    private RuntimeService runtimeService;
    private SysUserService sysUserService;
    private HistoryService historyService;
    public long findUserTaskCount(String userId) {
        TaskQuery taskQuery = buildUserQuery(userId);
        return taskQuery.count();
    }

    public Page<TaskResponse> findUserTaskList(Pageable pageable, String userId) {
        TaskQuery query = buildUserQuery(userId);
        long count = query.count();
        if (count == 0) {
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        }
        List<Task> taskList = query.listPage((int) pageable.getOffset(), pageable.getPageSize());


        // 填充流程信息
        Set<String> instanceIds = taskList.stream().map(TaskInfo::getProcessInstanceId).collect(Collectors.toSet());
        Map<String, ProcessInstance> instanceMap = runtimeService.createProcessInstanceQuery().processInstanceIds(instanceIds).list().stream().collect(Collectors.toMap(Execution::getId, t -> t));


        List<TaskResponse> infoList = taskList.stream().map(task -> {
            ProcessInstance instance = instanceMap.get(task.getProcessInstanceId());
            TaskResponse r = new TaskResponse();
            convert(r, task);
            r.setInstanceName(instance.getName());
            r.setInstanceStartTime(FriendlyUtils.getPastTime(instance.getStartTime()));
            r.setInstanceStarter(sysUserService.getNameById(instance.getStartUserId()));
            return r;
        }).collect(Collectors.toList());

        return new PageImpl<>(infoList, pageable, count);
    }

    public Page<TaskResponse> findUserTaskDoneList(Pageable pageable, String userId) {
        HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(userId)
                .finished()
                .includeProcessVariables()
                .orderByHistoricTaskInstanceEndTime().desc();


        List<HistoricTaskInstance> taskList = query.listPage((int) pageable.getOffset(), pageable.getPageSize());
        long count = query.count();
        if (count == 0) {
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        }
        
        Set<String> instanceIds = taskList.stream().map(TaskInfo::getProcessInstanceId).collect(Collectors.toSet());
        Map<String, HistoricProcessInstance> instanceMap = historyService.createHistoricProcessInstanceQuery().processInstanceIds(instanceIds).list()
                .stream().collect(Collectors.toMap(HistoricProcessInstance::getId, t -> t));


        List<TaskResponse> infoList = taskList.stream().map(task -> {
            HistoricProcessInstance instance = instanceMap.get(task.getProcessInstanceId());

            TaskResponse r = new TaskResponse();
            this.convert(r, task);
            r.setInstanceName(instance.getName());
            r.setInstanceStartTime(FriendlyUtils.getPastTime(instance.getStartTime()));
            r.setInstanceStarter(sysUserService.getNameById(instance.getStartUserId()));
            r.setDurationInfo(FriendlyUtils.getTimeDiff(task.getCreateTime(), task.getEndTime()));
            return r;
        }).toList();

        return new PageImpl<>(infoList, pageable, count);
    }

    private TaskQuery buildUserQuery(String userId) {
        TaskQuery query = taskService.createTaskQuery().active();

        query.or();
        query.taskAssignee(userId);
        query.taskCandidateUser(userId);

        // 人员及 分组
        Collection<AssignmentTypeProvider> providerList = SpringTool.getBeans(AssignmentTypeProvider.class);
        Set<String> groupIds = new HashSet<>();
        for (AssignmentTypeProvider provider : providerList) {
            List<String> groups = provider.findGroupsByUser(userId);
            if (groups != null) {
                groupIds.addAll(groups);
            }
        }
        if (!CollectionUtils.isEmpty(groupIds)) {
            query.taskCandidateGroupIn(groupIds);
        }
        query.endOr();

        query.orderByTaskCreateTime().desc();

        return query;
    }

    private void convert(TaskResponse r, TaskInfo task) {
        r.setId(task.getId());
        r.setTaskName(task.getName());
        r.setCreateTime(FriendlyUtils.getPastTime(task.getCreateTime()));
        r.setAssigneeInfo(sysUserService.getNameById(task.getAssignee()));
        r.setFormKey(task.getFormKey());
        r.setInstanceId(task.getProcessInstanceId());
    }
}
