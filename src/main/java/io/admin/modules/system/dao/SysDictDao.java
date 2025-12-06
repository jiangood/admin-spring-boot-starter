
package io.admin.modules.system.dao;

import io.admin.common.utils.field.ValueType;
import io.admin.modules.system.entity.SysDict;
import io.admin.framework.data.repository.BaseDao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class SysDictDao extends BaseDao<SysDict> {



    @Transactional
    public SysDict add(String code,String text){
        SysDict  dict = new SysDict();
        dict.setCode(code);
        dict.setText(text);
        dict = this.save(dict);
        return dict;
    }

    public SysDict findByCode(String code) {
        JpaQuery<SysDict> q = new JpaQuery<>();
        q.eq(SysDict.Fields.code, code);
        return this.findOne(q);
    }
    public boolean existsByCode(String code) {
        JpaQuery<SysDict> q = new JpaQuery<>();
        q.eq("code",code);
        return this.exists(q);
    }

    public SysDict findByIdOrCode(String code) {
        JpaQuery<SysDict> q = new JpaQuery<>();
        q.addSubOr(qq->{
            qq.eq(SysDict.Fields.code, code);
            qq.eq("id", code);
        });

        return this.findOne(q);
    }


    @Transactional
    public SysDict saveOrUpdate(String code, String label, boolean isNumber) {
        SysDict dict = this.findByCode(code);
        if(dict == null){
            dict = new SysDict();
            dict.setCode(code);
        }
        dict.setText(label);
        dict.setIsNumber(isNumber);
      return  this.save(dict);
    }
}
