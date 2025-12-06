package io.admin.modules.system.dao;

import io.admin.modules.system.entity.SysJwt;
import io.admin.framework.data.repository.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class SysJwtDao extends BaseDao<SysJwt> {

    public SysJwt findByTokenMd5(String tokenMd5){
        JpaQuery<SysJwt> q = new JpaQuery<>();
        q.eq(SysJwt.Fields.tokenMd5, tokenMd5);
        return findOne(q);
    }
}
