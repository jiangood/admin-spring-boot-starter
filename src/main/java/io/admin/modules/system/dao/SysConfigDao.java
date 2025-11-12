
package io.admin.modules.system.dao;


import io.admin.modules.system.entity.SysConfig;
import io.admin.framework.data.repository.BaseDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统参数配置
 */
@Slf4j
@Repository
public class SysConfigDao extends BaseDao<SysConfig> {


    @Transactional
    public void setDefaultValue( String id, String defaultValue) {
        SysConfig cfg = super.findOne(id);
        if (cfg == null){
            cfg = new SysConfig();
            cfg.setCode(id);
            cfg.setValue(defaultValue);
        }
        if(cfg.getValue() == null){
            cfg.setValue(defaultValue);
        }
        super.save(cfg);
    }


}
