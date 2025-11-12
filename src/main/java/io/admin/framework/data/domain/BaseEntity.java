package io.admin.framework.data.domain;

import com.fasterxml.jackson.annotation.*;
import io.admin.common.utils.ann.Remark;
import io.admin.framework.data.DBConstants;
import io.admin.framework.data.id.ann.GenerateUuid7;
import io.admin.framework.perm.SecurityUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
@EqualsAndHashCode(of = "id", callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity extends BaseNoIdEntity implements Serializable {



    @Id
    @GenerateUuid7
    @Column(length = DBConstants.LEN_ID)
    private String id;

















    @JsonIgnore
    @Transient
    public boolean isNew() {
        return null == getId();
    }





}
