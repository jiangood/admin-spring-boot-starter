package io.admin.modules.flowable.core.definition;

import lombok.Data;

import java.util.List;

@Data
public class ProcessDefinition {
    private String key;
    private String name;

    private Class<? extends ProcessListener> listener;
    // ... 其他字段

    private List<ProcessVariable> variables; // 变量名更改
    private List<FormDefinition> forms;       // 变量名更改
}
