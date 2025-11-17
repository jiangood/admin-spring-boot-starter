package io.admin.modules.flowable.admin.service;



import io.admin.modules.flowable.core.FlowableMasterDataProvider;
import io.admin.modules.flowable.core.FlowableProperties;
import io.admin.modules.flowable.core.dto.TaskHandleResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.UserTask;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.task.Comment;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 流程流转
 */
@Slf4j
@Component
public class MyTaskService {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private FlowableMasterDataProvider flowableMasterDataProvider;

    @Resource
    FlowableProperties flowableProperties;


    public void handle(String userId, TaskHandleResult result, String taskId, String comment) {
        Assert.notNull(userId, "用户Id不能为空");
        //校验任务是否存在
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        Assert.state(task != null, "任务已经处理过，请勿重复操作");

        //获取流程实例id
        String processInstanceId = task.getProcessInstanceId();
        comment = "【" +task.getName() + "】：" + result.getMessage() + "。" + comment;
        addComment(processInstanceId, taskId, userId, comment);

        String assignee = task.getAssignee();
        // if (StringUtils.hasText(assignee)) {
        //设置办理人为当前用户
        taskService.setAssignee(taskId, userId);
        //   }


        if (result == TaskHandleResult.APPROVE) {
            taskService.complete(taskId);
            return;
        }

        // 点击拒绝（不同意）
        if (result == TaskHandleResult.REJECT) {
            switch (flowableProperties.getRejectType()) {
                case DELETE:
                    closeAndDelete(comment, task);
                    break;
                case MOVE_BACK:
                    this.moveBack(task);

                    break;
            }

            return;
        }
    }

    private void closeAndDelete(String comment, Task task) {
        runtimeService.deleteProcessInstance(task.getProcessInstanceId(), comment);
    }

    // 回退上一个节点
    private void moveBack(Task task) {
        log.debug("开始回退任务 {}", task);
        List<UserTask> userTaskList = myBpmnModelService.findPreActivity(task);
        for (UserTask userTask : userTaskList) {
            log.debug("回退任务 {}", userTask);
        }

        List<String> ids = userTaskList.stream().map(t -> t.getId()).collect(Collectors.toList());

        if (ids.isEmpty()) {
            this.closeAndDelete("回退节点为空，终止流程", task);
            return;
        }



        runtimeService.createChangeActivityStateBuilder()
                .processInstanceId(task.getProcessInstanceId())
                .moveSingleExecutionToActivityIds(task.getExecutionId(), ids)
                .changeState();


    }


    public String getUserName(String userId) {
        if (userId == null) {
            return null;
        }
        return flowableMasterDataProvider.getUserNameById(userId);
    }


    private void addComment(String processInstanceId, String taskId, String taskAssignee, String comment) {
        Comment addComment = taskService.addComment(taskId, processInstanceId, comment);
        addComment.setUserId(taskAssignee);
        taskService.saveComment(addComment);
    }


    /**
     * @deprecated 替换MyBpmnModelService
     * @param instanceId
     * @return
     */
    public BufferedImage drawImage(String instanceId) {
      return myBpmnModelService.drawImage(instanceId);
    }


    @Resource
    MyBpmnModelService myBpmnModelService;
}
