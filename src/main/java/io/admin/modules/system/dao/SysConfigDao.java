
package io.admin.modules.system.dao;


import io.admin.modules.system.entity.SysConfig;
import io.admin.framework.data.repository.BaseDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统参数配置
 */
@Slf4j
@Repository
public class SysConfigDao extends BaseDao<SysConfig> {


    @Transactional
    public SysConfig setDefaultValue(String id, String defaultValue) {
        SysConfig cfg = super.findOne(id);
        if (cfg == null){
            cfg = new SysConfig();
            cfg.setCode(id);
            cfg.setValue(defaultValue);
        }
        if(cfg.getValue() == null){
            cfg.setValue(defaultValue);
        }
      return  super.save(cfg);
    }


}
