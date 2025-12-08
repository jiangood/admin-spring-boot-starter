package io.admin.modules.api.entity;

import io.admin.common.utils.annotation.Remark;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResourceArgument {

    String name;

    String type;
    Integer len;


    String desc;


    @Remark("示例")
    String demo;


    Boolean required;


    Integer index;
}
