package io.admin.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.admin.common.tools.annotation.Remark;
import io.admin.common.tools.tree.TreeNode;
import io.admin.framework.data.DBConstants;
import io.admin.framework.data.domain.BaseEntity;
import io.admin.modules.system.enums.OrgType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.List;

/**
 * 系统组织机构表
 */
@Remark("组织机构")
@Getter
@Setter
@Entity
@FieldNameConstants
public class SysOrg extends BaseEntity implements TreeNode<SysOrg> {


    @Transient
    List<SysOrg> children;
    /**
     * 父id, 如果是根节点，则为空
     */
    private String pid;
    /**
     * 名称
     */
    @NotNull
    private String name;
    /**
     * 排序
     */
    private Integer seq;
    @Column(nullable = false)
    private Boolean enabled;
    @NotNull
    private Integer type;
    // 部门领导
    @ManyToOne
    private SysUser leader;
    // 扩展字段1
    private String extra1;
    private String extra2;
    private String extra3;


    /**
     * 第三方系统的唯一标识符，常用于OAuth集成，统一认证等
     */
    @JsonIgnore
    @Column(length = DBConstants.LEN_ID)
    private String thirdId;

    @JsonIgnore
    @Column(length = DBConstants.LEN_ID)
    private String thirdPid;

    @JsonIgnore
    @Column(length = DBConstants.LEN_NAME)
    private String thirdAccount;

    @Transient
    public boolean isDept() {
        if (type == null) {
            return false;
        }
        return type == OrgType.TYPE_DEPT.getCode();
    }


    @Override
    public String toString() {
        return name;
    }


    @PrePersist
    public void prePersist() {
        if (enabled == null) {
            enabled = true;
        }
    }
}
