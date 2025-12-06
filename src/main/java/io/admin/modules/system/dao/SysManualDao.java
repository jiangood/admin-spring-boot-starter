package io.admin.modules.system.dao;

import io.admin.modules.system.entity.SysManual;
import io.admin.framework.data.repository.BaseDao;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class SysManualDao extends BaseDao<SysManual> {


    public int findMaxVersion(String name){
        JpaQuery<SysManual> q = new JpaQuery<>();
        q.eq(SysManual.Fields.name,name);

        SysManual e = this.findTop1(q, Sort.by(Sort.Direction.DESC, SysManual.Fields.version));

        return e == null ? 0 : e.getVersion();
    }

}

