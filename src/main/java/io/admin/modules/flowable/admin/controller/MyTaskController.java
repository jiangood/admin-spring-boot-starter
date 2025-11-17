package io.admin.modules.flowable.admin.controller;


import cn.hutool.core.lang.Dict;


import io.admin.common.dto.AjaxResult;
import io.admin.common.utils.BeanTool;
import io.admin.common.utils.DateFormatTool;
import io.admin.common.utils.ImgTool;
import io.admin.common.utils.SpringTool;
import io.admin.framework.config.security.LoginUser;
import io.admin.modules.common.LoginUtils;
import io.admin.modules.flowable.core.FlowableLoginUser;
import io.admin.modules.flowable.core.FlowableLoginUserProvider;
import io.admin.modules.flowable.core.FlowableManager;

import io.admin.modules.flowable.core.FlowableMasterDataProvider;
import io.admin.modules.flowable.admin.entity.ConditionVariable;
import io.admin.modules.flowable.admin.entity.SysFlowableModel;
import io.admin.modules.flowable.admin.service.MyTaskService;
import io.admin.modules.flowable.admin.service.SysFlowableModelService;
import io.admin.modules.flowable.core.assignment.AssignmentTypeProvider;
import io.admin.modules.flowable.core.dto.response.TaskResponse;
import io.admin.modules.flowable.core.dto.request.HandleTaskRequest;
import io.admin.modules.flowable.core.dto.response.CommentResult;


import io.admin.modules.system.service.SysUserService;
import jakarta.annotation.Resource;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.history.HistoricProcessInstanceQuery;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Comment;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskInfo;
import org.flowable.task.api.TaskQuery;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 用户侧功能，待办，处理，查看流程等
 * 每个人都可以看自己任务，故而没有权限注解
 */
@RestController
@RequestMapping("admin/flowable/userClient")
public class MyTaskController {


    @Resource
    MyTaskService myTaskService;

    @Resource
    TaskService taskService;


    @Resource
    HistoryService historyService;

    @Resource
    SysFlowableModelService myFlowModelService;

    @Resource
    FlowableLoginUserProvider flowableLoginUserProvider;

    @Resource
    FlowableMasterDataProvider masterDataProvider;

    @Resource
    FlowableManager fm;


    @Resource
    RuntimeService runtimeService;

    @Resource
    SysUserService sysUserService;
    @RequestMapping("todoTaskPage")
    public AjaxResult todo(Pageable pageable) {
        String userId = LoginUtils.getUserId();
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




        List<Task> taskList = query.listPage((int) pageable.getOffset(), pageable.getPageSize());
        long count = query.count();

        // 填充流程信息
        Set<String> instanceIds = taskList.stream().map(TaskInfo::getProcessInstanceId).collect(Collectors.toSet());
        Map<String, ProcessInstance> instanceMap = runtimeService.createProcessInstanceQuery().processInstanceIds(instanceIds).list().stream().collect(Collectors.toMap(Execution::getId, t -> t));




        List<TaskResponse> infoList = taskList.stream().map(task -> {
            ProcessInstance instance = instanceMap.get(task.getProcessInstanceId());
            TaskResponse r = new TaskResponse();
            r.setId(task.getId());
            r.setTaskName(task.getName());
            r.setCreateTime(task.getCreateTime());
            r.setAssigneeInfo(sysUserService.getNameById( task.getAssignee()));
            r.setFormKey(task.getFormKey());
            r.setInstanceId(task.getProcessInstanceId());
            r.setInstanceName(instance.getName());
            r.setInstanceStartTime(instance.getStartTime());
            r.setInstanceStarter(sysUserService.getNameById( instance.getStartUserId()));
            return r;
        }).collect(Collectors.toList());

        PageImpl<TaskResponse> page = new PageImpl<>(infoList, pageable, count);

        return AjaxResult.ok().data(page);
    }

    @RequestMapping("doneTaskPage")
    public AjaxResult doneTaskPage(Pageable pageable) {
        LoginUser user = LoginUtils.getUser();
        HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery()
                .finished()
                .includeProcessVariables()
                .orderByHistoricTaskInstanceEndTime().desc();


        List<HistoricTaskInstance> taskList = query.listPage((int) pageable.getOffset(), pageable.getPageSize());
        long count = query.count();

        Set<String> instanceIds = taskList.stream().map(TaskInfo::getProcessInstanceId).collect(Collectors.toSet());
        Map<String, HistoricProcessInstance> instanceMap = historyService.createHistoricProcessInstanceQuery().processInstanceIds(instanceIds).list()
                .stream().collect(Collectors.toMap(HistoricProcessInstance::getId, t -> t));


        List<TaskResponse> infoList = taskList.stream().map(task -> {
            HistoricProcessInstance instance = instanceMap.get(task.getProcessInstanceId());

            TaskResponse r = new TaskResponse();
            r.setId(task.getId());
            r.setTaskName(task.getName());
            r.setCreateTime(task.getCreateTime());
            r.setAssigneeInfo(sysUserService.getNameById( task.getAssignee()));
            r.setFormKey(task.getFormKey());
            r.setInstanceId(task.getProcessInstanceId());
            r.setInstanceName(instance.getName());
            r.setInstanceStartTime(instance.getStartTime());
            r.setInstanceStarter(sysUserService.getNameById( instance.getStartUserId()));
            return r;
        }).collect(Collectors.toList());

        return AjaxResult.ok().data( new PageImpl<>(infoList, pageable, count));
    }


    // 我发起的
    @GetMapping("myInstance")
    public AjaxResult myInstance(Pageable pageable) {
        FlowableLoginUser loginUser = flowableLoginUserProvider.currentLoginUser();


        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
        if (!loginUser.isSuperAdmin()) {
            query.startedBy(loginUser.getId());
        }


        query.orderByProcessInstanceStartTime().desc();
        query.includeProcessVariables();

        long count = query.count();

        List<HistoricProcessInstance> list = query.listPage((int) pageable.getOffset(), pageable.getPageSize());

        List<Map<String, Object>> mapList = BeanTool.copyToListMap(list, HistoricProcessInstance.class);


        for (Map<String, Object> map : mapList) {
            String startUserId = (String) map.get("startUserId");
            if (startUserId != null) {
                map.put("startUserName", masterDataProvider.getUserNameById(startUserId));
            }
        }


        return AjaxResult.ok().data(new PageImpl<>(mapList, pageable, count));
    }


    @PostMapping("handleTask")
    public AjaxResult handle(@RequestBody HandleTaskRequest param) {
        FlowableLoginUser subject = flowableLoginUserProvider.currentLoginUser();
        myTaskService.handle(subject.getId(), param.getResult(), param.getTaskId(), param.getComment());
        return AjaxResult.ok().msg("处理成功");
    }

    /**
     * 任务信息
     *
     * @param id
     * @return
     */
    @GetMapping("taskInfo")
    @Transactional
    public AjaxResult taskInfo(String id) {
        Assert.hasText(id, "任务id不能为空");
        Map<String, Object> variables = taskService.getVariables(id);
        Task task = taskService.createTaskQuery()
                .taskId(id)
                .singleResult();

        Dict data = Dict.of("id", task.getId(),
                "formKey", task.getFormKey(),
                "variables", variables
        );

        return AjaxResult.ok().data(data);
    }


    /**
     * 流程处理信息
     *
     * @return 处理流程及流程图
     */
    @GetMapping("getInstanceInfo")
    public AjaxResult instanceByBusinessKey(String businessKey, String id) throws IOException {
        Assert.state(businessKey != null || id != null, "id或businessKey不能同时为空");
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
        if (businessKey != null) {
            query.processInstanceBusinessKey(businessKey);
        }
        if (id != null) {
            query.processInstanceId(id);
        }


        query.notDeleted();
        query.includeProcessVariables()
                .orderByProcessInstanceStartTime()
                .desc();

        List<HistoricProcessInstance> list = query
                .listPage(0, 1);
        Assert.state(!list.isEmpty(), "暂无流程信息");
        HistoricProcessInstance instance = list.get(0);


        Map<String, Object> data = BeanTool.copyToMap(HistoricProcessInstance.class, instance);

        // 处理意见
        {
            List<Comment> processInstanceComments = taskService.getProcessInstanceComments(instance.getId());
            List<CommentResult> commentResults = processInstanceComments.stream().sorted(Comparator.comparing(Comment::getTime)).map(c -> new CommentResult(c)).collect(Collectors.toList());
            data.put("commentList", commentResults);
        }


        // 图片
        {
            BufferedImage image = myTaskService.drawImage(instance.getId());

            String base64 = ImgTool.toBase64DataUri(image);

            data.put("img", base64);
        }


        {
            String instanceName = instance.getName();
            if (instanceName == null) {
                instanceName = instance.getProcessDefinitionName();
            }
            data.put("startTime", DateFormatTool.format(instance.getStartTime()));
            data.put("starter", myTaskService.getUserName(instance.getStartUserId()));
            data.put("name", instanceName);
            data.put("id", instance.getId());


            List<Comment> processInstanceComments = taskService.getProcessInstanceComments(id);
            List<CommentResult> commentResults = processInstanceComments.stream().sorted(Comparator.comparing(Comment::getTime)).map(c -> new CommentResult(c)).collect(Collectors.toList());


            data.put("instanceCommentList", commentResults);

            SysFlowableModel model = myFlowModelService.findByCode(instance.getProcessDefinitionKey());


            List<ConditionVariable> conditionVariableList = model.getConditionVariableList();
            if (conditionVariableList != null) {
                Map<String, Object> pv = instance.getProcessVariables();
                Map<String, Object> variables = new HashMap<>();
                for (ConditionVariable con : conditionVariableList) {
                    String name = con.getName();
                    String label = con.getLabel();
                    variables.put(label, pv.get(name));
                }
                data.put("variables", variables);
            }
        }


        return AjaxResult.ok().data(data);
    }


}
