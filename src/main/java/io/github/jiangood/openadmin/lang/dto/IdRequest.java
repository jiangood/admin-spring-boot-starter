package io.github.jiangood.openadmin.lang.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * 主要是id的请求
 *
 */
@Data
public class IdRequest {

    @NotNull(message = "id不能为空")
    private String id;

}
