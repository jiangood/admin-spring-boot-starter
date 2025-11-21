package io.admin.modules.job.service;

import io.admin.common.utils.field.FieldDescription;
import io.admin.modules.job.BaseJob;
import io.admin.modules.job.JobDescription;
import org.quartz.JobDataMap;
import org.slf4j.Logger;

@JobDescription(label = "你好世界", group = "测试分组", params = {@FieldDescription(name = "name", label = "姓名")})
public class HelloWorldJob extends BaseJob {
    @Override
    public String execute(JobDataMap data, Logger logger) throws Exception {
        System.out.println("你好世界, 欢迎您" + data.get("name"));
        return "OK";
    }
}
