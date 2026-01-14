package io.github.jiangood.as.modules.flowable.core.config.meta;

import io.github.jiangood.as.common.tools.field.ValueType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProcessVariable {
    private String name;
    private String label;
    private ValueType valueType;
    private boolean required;

    public ProcessVariable(String name, String label) {
        this(name, label, ValueType.STRING, false);
    }

    public ProcessVariable(String name, String label, ValueType valueType, boolean required) {
        this.name = name;
        this.label = label;
        this.valueType = valueType;
        this.required = required;
    }
}
