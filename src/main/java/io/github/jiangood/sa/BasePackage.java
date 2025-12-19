package io.github.jiangood.sa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"io.github.jiangood.sa.framework", "io.github.jiangood.sa.modules"})
@EntityScan(basePackages = "io.github.jiangood.sa.modules")
public class BasePackage {
}
