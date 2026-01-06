package io.github.jiangood.sa.modules.flowable.core.config.meta;

import io.github.jiangood.sa.common.tools.field.ValueType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProcessVariable {
    private String name;
    private String label;
    private ValueType valueType;
    private boolean required = false;

    public ProcessVariable(String name, String label) {
        this.name = name;
        this.label = label;
    }
}
