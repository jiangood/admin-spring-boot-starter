package io.github.jiangood.sa.modules.system.dao;


import io.github.jiangood.sa.framework.config.data.SysMenuYmlDao;
import io.github.jiangood.sa.framework.config.data.dto.MenuDefinition;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SysMenuDao {


    List<MenuDefinition> findAll();

    List<MenuDefinition> findAllEnabled();

    List<MenuDefinition> findAllById(List<String> ids);
}
