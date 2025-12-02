package io.admin.modules.flowable.core.definition;

import lombok.Data;

@Data
public class ProcessVariable {
    private String name;
    private String label;
    private String type;
    private boolean required = false;
}
