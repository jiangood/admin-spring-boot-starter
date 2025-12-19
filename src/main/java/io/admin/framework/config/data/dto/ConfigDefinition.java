package io.admin.framework.config.data.dto;


import io.admin.common.tools.field.ValueType;
import lombok.Data;

@Data
public class ConfigDefinition {
    String code;
    ValueType valueType;
    String description;
    String name;
}
