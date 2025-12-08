package io.admin.modules.system.enums;

import io.admin.common.utils.annotation.Remark;
import io.admin.framework.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Remark("机构类型")
@AllArgsConstructor
@Getter
public enum OrgType implements CodeEnum {
    TYPE_UNIT(10, "单位"),
    TYPE_DEPT(20, "部门");

    private final int code;
    private final String msg;

    public static OrgType valueOf(int code) {
        for (OrgType type : OrgType.values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("No OrgType with code: " + code);
    }
}


