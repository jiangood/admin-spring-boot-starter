package io.admin.framework.config.data.sysconfig;


import io.admin.common.utils.field.ValueType;
import lombok.Data;

@Data
public class ConfigDefinition {
    String code;
    ValueType valueType;
    String description;
    String name;
}
