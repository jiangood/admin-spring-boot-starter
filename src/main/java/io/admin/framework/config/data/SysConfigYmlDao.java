package io.admin.framework.config.data;

import io.admin.common.tools.YmlTool;
import io.admin.framework.config.data.sysconfig.ConfigGroupDefinition;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Configuration
public class SysConfigYmlDao {
    private static final String MENU_CONFIG_PATTERN = "classpath*:config/application-data*.yml";
    private final List<ConfigGroupDefinition> configs = new ArrayList<>();



    public List<ConfigGroupDefinition> findAll() {
        return Collections.unmodifiableList(configs);
    }


    @PostConstruct
    public void init() {
        for (Resource configFile : this.getConfigFiles()) {
            log.info("处理数据文件 {}", configFile.getFilename());
            DataProperties cur = this.parseResource(configFile);
            configs.addAll(cur.getConfigs());
        }
    }




    @SneakyThrows
    private Resource[] getConfigFiles() {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 1. 查找所有匹配的文件
        Resource[] resources = resolver.getResources(MENU_CONFIG_PATTERN);

        // 2. 排序，将包含framework的文件放在前面
        Arrays.sort(resources, (o1, o2) -> {
            String f1 = o1.getFilename();
            String f2 = o2.getFilename();
            return f1.compareTo(f2);
        });
        log.info("找到 {} 个数据文件", resources.length);
        log.info("数据文件列表: {}", Arrays.toString(resources));

        return resources;
    }

    @SneakyThrows
    private DataProperties parseResource(Resource resource) {
        return YmlTool.parseYml(resource.getInputStream(), DataProperties.class, "data");
    }


}
