
package io.admin.modules.system.entity;

import io.admin.common.utils.ann.Remark;
import io.admin.framework.data.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;


@Remark("数据字典")
@Getter
@Setter
@Entity
@FieldNameConstants
public class SysDict extends BaseEntity {


    @Column(length = 80)
    private String text;

    @Remark("编码")
    @Column(nullable = false, unique = true)
    private String code;


    private Boolean isNumber;


}
