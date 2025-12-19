package io.admin.modules.flowable.core.config.meta;

import io.admin.common.tools.field.ValueType;
import lombok.Data;

@Data
public class ProcessVariable {
    private String name;
    private String label;
    private ValueType valueType;
    private boolean required = false;
}
