
package io.admin.modules.system.dao;


import io.admin.framework.data.repository.BaseDao;
import io.admin.modules.system.entity.SysDict;
import io.admin.modules.system.entity.SysDictItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统字典值
 */
@Slf4j
@Repository
public class SysDictItemDao extends BaseDao<SysDictItem> {
    @Transactional
    public SysDictItem add(SysDict dict, String code, String text){
        SysDictItem item = new SysDictItem();
        item.setSysDict(dict);
        item.setCode(code);
        item.setText(text);
        item.setBuiltin(false);

        return this.save(item);
    }

    @Override
    public void updateField(SysDictItem entity, List<String> fieldsToUpdate) {
        super.updateField(entity, fieldsToUpdate);
    }

    @Transactional
    public void deleteByPid(String typeId) {
        JpaQuery<SysDictItem> q = new JpaQuery<>();

        q.eq(SysDictItem.Fields.sysDict + ".id", typeId);

        List<SysDictItem> list = this.findAll(q);

        this.deleteAll(list);
    }

    public String findText(String typeCode, String itemCode) {
        JpaQuery<SysDictItem> q = new JpaQuery<>();
        q.eq(SysDictItem.Fields.sysDict + "." + SysDict.Fields.code, typeCode);
        q.eq(SysDictItem.Fields.code, itemCode);

        SysDictItem item = this.findOne(q);
        String rs = null;
        if (item != null) {
            rs = item.getText();
        }
        log.trace("获取数据字典 typeCode:{} itemCode:{} 结果:{}", typeCode, itemCode, rs);
        return rs;
    }




    public List<SysDictItem> findAllByDictCode(String code) {
        JpaQuery<SysDictItem> q = new JpaQuery<>();
        q.eq(SysDictItem.Fields.sysDict + "." + SysDict.Fields.code, code);
        return this.findAll(q);
    }

    public SysDictItem findByDictAndCode(SysDict dict ,String code) {
        JpaQuery<SysDictItem> q = new JpaQuery<>();
        q.eq(SysDictItem.Fields.code, code);
        q.eq(SysDictItem.Fields.sysDict , dict);
        return this.findOne(q);
    }


    @Transactional
    public void saveOrUpdate(SysDict sysDict, String code, String text, int seq, boolean buildin) {
        SysDictItem item = this.findByDictAndCode(sysDict, code);
        if(item == null){
            item = new SysDictItem();
        }
        item.setCode(code);
        item.setText(text);
        item.setSeq(seq);
        item.setSysDict(sysDict);
        item.setBuiltin(buildin);
        this.save(item);
    }
}
