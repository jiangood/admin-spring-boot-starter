package io.admin.framework.data.domain;

import com.fasterxml.jackson.annotation.*;
import io.admin.framework.data.DBConstants;
import io.admin.framework.data.id.ann.GenerateUuid7;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
@EqualsAndHashCode(of = "id", callSuper = false)
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
