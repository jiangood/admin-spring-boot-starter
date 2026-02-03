package io.github.jiangood.openadmin.modules.system.service;

import io.github.jiangood.openadmin.framework.data.BaseService;
import io.github.jiangood.openadmin.modules.system.dao.SysManualDao;
import io.github.jiangood.openadmin.modules.system.entity.SysManual;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysManualService extends BaseService<SysManual> {

    @Resource
    SysManualDao dao;

    @Override
    public SysManual save(SysManual input, List<String> requestKeys) throws Exception {
        if (input.isNew()) {
            int maxVersion = dao.findMaxVersion(input.getName());
            input.setVersion(maxVersion + 1);
        }

        return super.save(input, requestKeys);
    }
}

