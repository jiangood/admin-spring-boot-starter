package io.admin.modules.flowable.core;

import io.admin.modules.flowable.core.dto.response.TaskResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface FlowableManager {
    void start(String processDefinitionKey, String bizKey, Map<String, Object> variables);

    void start(String processDefinitionKey, String bizKey, String title, Map<String, Object> variables);

    void validate(String processDefinitionKey, String bizKey, Map<String, Object> variables);


}
