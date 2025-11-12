package io.admin.framework.data.audit;

import cn.hutool.core.util.IdUtil;
import io.admin.common.utils.JsonUtils;
import io.admin.modules.system.dao.SysConfigDao;
import io.admin.modules.system.entity.SysConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;


import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class CreatedByAuditingTest {

    @Autowired
    private SysConfigDao repository;

    // 定义一个常量用于模拟用户名
    private static final String MOCK_USERNAME = "test_creator_001";

    /**
     * 测试 @CreatedBy 字段的自动填充功能。
     * 关键点在于使用 @WithMockUser 来模拟已登录的用户。
     */
    @Test
    @WithMockUser(username = MOCK_USERNAME, roles = {"USER"}) // 模拟一个已认证用户
    void createdBy_ShouldBeSetToMockUserOnSave() {
        SysConfig cfg = repository.setDefaultValue(IdUtil.fastUUID(), IdUtil.fastSimpleUUID());

        System.out.println(JsonUtils.toJsonQuietly(cfg));

    }
}