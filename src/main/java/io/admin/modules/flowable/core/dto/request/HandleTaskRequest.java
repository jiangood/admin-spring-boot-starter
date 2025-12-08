package io.admin.modules.flowable.core.dto.request;

import io.admin.modules.flowable.core.dto.TaskHandleType;
import lombok.Data;

@Data
public class HandleTaskRequest {

    TaskHandleType result;
    String taskId;
    String comment;
}
