package io.admin.modules.common.dto.response;

import io.admin.modules.system.dto.response.SysDictTreeResponse;
import io.admin.modules.system.entity.SysDictItem;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class LoginDataResponse {

    private boolean login;
    private boolean needUpdatePwd;


    private Map<String, List<SysDictItem>> dictMap;

    private LoginInfoResponse loginInfo;
}
