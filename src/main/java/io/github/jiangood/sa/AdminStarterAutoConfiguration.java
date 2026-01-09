package io.github.jiangood.sa;

import io.github.jiangood.sa.framework.config.security.SecurityConfig;
import io.github.jiangood.sa.modules.BasePackageModules;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan
@EntityScan(basePackageClasses = BasePackageModules.class)
@EnableCaching
@EnableScheduling
@EnableAsync

@AutoConfiguration
public class AdminStarterAutoConfiguration {


}
