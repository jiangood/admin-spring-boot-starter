package io.admin.framework.data.domain;

import io.admin.common.utils.annotation.Remark;
import lombok.Data;

import java.util.Date;

@Data
public class BaseDto {

    private String id;


    @Remark("创建时间")
    private Date createTime;


    @Remark("创建人ID")
    private String createUser;


    private Date updateTime;


    @Remark("更新人ID")
    private String updateUser;


}
