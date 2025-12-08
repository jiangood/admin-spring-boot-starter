package io.admin.modules.system.dto.request;

import io.admin.modules.system.entity.DataPermType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GrantUserPermRequest {

    @NotNull
    String id;

    @NotNull
    DataPermType dataPermType;

    List<String> orgIds;

    List<String> roleIds;

}
