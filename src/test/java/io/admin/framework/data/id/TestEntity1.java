package io.admin.framework.data.id;

import io.admin.framework.data.domain.BaseNoIdEntity;
import io.admin.framework.data.id.ann.GeneratePrefixedSequence;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
public class TestEntity1  extends BaseNoIdEntity {



    private String id;

    @Id
    @GeneratePrefixedSequence(prefix = "BOOK")
    public String getId() {
        return id;
    }

    private String name;
}
