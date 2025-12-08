package io.admin.framework.config.data;

import io.admin.framework.config.data.sysconfig.ConfigGroupDefinition;
import io.admin.framework.config.data.sysmenu.MenuDefinition;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "data")
@Data
public class DataProperties {
    /**
     * 菜单定义和
     */
    private List<MenuDefinition> menus = new ArrayList<>();

    /**
     * 系统配置定义（可在系统设置中查看和修改）
     */
    private List<ConfigGroupDefinition> configs = new ArrayList<>();
}
