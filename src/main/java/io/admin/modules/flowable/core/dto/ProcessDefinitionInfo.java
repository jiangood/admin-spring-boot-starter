package io.admin.modules.flowable.core.dto;

import io.admin.modules.flowable.core.definition.FormDefinition;
import io.admin.modules.flowable.core.definition.ProcessVariable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProcessDefinitionInfo {

    // 从数据库中加载
    private  String id;
    private String content; // 默认为空，需要时手动从数据库中加载

    // 从注解中加载
    private  String name;
    private  String code;


    private List<FormDefinition> formList = new ArrayList<>();
    private List<ProcessVariable> conditionVariableList = new ArrayList<>();
}
