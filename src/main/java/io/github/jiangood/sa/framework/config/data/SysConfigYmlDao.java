package io.github.jiangood.sa.framework.config.data;

import io.github.jiangood.sa.common.tools.ResourceTool;
import io.github.jiangood.sa.common.tools.YmlTool;
import io.github.jiangood.sa.framework.config.data.dto.ConfigGroupDefinition;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Configuration
public class SysConfigYmlDao {
    private static final String MENU_CONFIG_PATTERN = "config/application-data*.yml";
    private final List<ConfigGroupDefinition> configs = new ArrayList<>();



    public List<ConfigGroupDefinition> findAll() {
        return Collections.unmodifiableList(configs);
    }


    @PostConstruct
    public void init() throws IOException {
        Resource[] resources = ResourceTool.findAll(MENU_CONFIG_PATTERN);
        ResourceTool.sort(resources);

        log.info("找到 {} 个数据文件", resources.length);
        log.info("数据文件列表: {}", Arrays.toString(resources));

        for (Resource configFile : resources) {
            log.info("处理数据文件 {}", configFile.getFilename());
            DataProperties cur = this.parseResource(configFile);
            configs.addAll(cur.getConfigs());
        }
    }


    @SneakyThrows
    private DataProperties parseResource(Resource resource) {
        return YmlTool.parseYml(resource.getInputStream(), DataProperties.class, "data");
    }


}
