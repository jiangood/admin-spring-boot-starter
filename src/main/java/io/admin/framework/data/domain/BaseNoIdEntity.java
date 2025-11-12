package io.admin.framework.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.admin.common.utils.ann.Remark;
import io.admin.framework.data.DBConstants;
import io.admin.framework.data.id.ann.GeneratePrefixedSequence;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseNoIdEntity implements Persistable<String> {

    @CreatedDate
    @Column(updatable = false)
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

    @CreatedBy
    @Column(length = DBConstants.LEN_ID, updatable = false)
    private String createUser;




    @LastModifiedBy
    @Column(length = DBConstants.LEN_ID)
    private String updateUser;



    /// ===== 乐观锁字段 =====
/*    @Version
    @Column(columnDefinition = "bigint default 0") // 建议：仅用于提示数据库建表时设置默认值
    private Integer lockVersion;*/
}
