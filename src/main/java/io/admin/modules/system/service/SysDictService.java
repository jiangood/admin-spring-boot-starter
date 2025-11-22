
package io.admin.modules.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import io.admin.common.utils.tree.TreeTool;
import io.admin.common.dto.antd.Option;
import io.admin.modules.system.dto.response.SysDictTreeResponse;
import io.admin.modules.system.dao.SysDictDao;
import io.admin.modules.system.dao.SysDictItemDao;
import io.admin.modules.system.entity.SysDict;
import io.admin.modules.system.entity.SysDictItem;
import io.admin.framework.data.domain.BaseEntity;
import io.admin.framework.data.service.BaseService;
import io.admin.framework.data.query.JpaQuery;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SysDictService extends BaseService<SysDict> {

    @Resource
    private SysDictItemDao sysDictItemDao;

    @Resource
    private SysDictDao sysDictDao;

    /**
     * 初始一个数据字典
     * 先判断是否存在
     *
     * @param code
     * @param text
     * @return
     */
    @Transactional
    public SysDict init(String code, String text, String... itemCodeTextArr) {
        SysDict old = sysDictDao.findByCode(code);
        if (old != null) {
            log.info("字典已存在，忽略初始化。 {}={}", code, text);
            return old;
        }
        SysDict dict = new SysDict();
        dict.setCode(code);
        dict.setText(text);
        dict = sysDictDao.save(dict);

        for (int i = 0; i < itemCodeTextArr.length; i = i + 2) {
            String itemCode = itemCodeTextArr[i];
            String itemValue = itemCodeTextArr[i + 1];
            sysDictItemDao.add(dict, itemCode, itemValue);
        }

        return dict;
    }

    public String findTextByDictCodeAndKey(String code, String key) {
        return sysDictItemDao.findText(code, key);
    }

    public List<Option> findOptions(String code) {
        List<SysDictItem> list = sysDictItemDao.findAllByDictCode(code);

        return Option.convertList(list, BaseEntity::getId, SysDictItem::getText);
    }




    public Map<String, List<SysDictItem>> mapList() {
        List<SysDictItem> dictData = sysDictItemDao.findAll(Sort.by(SysDictItem.Fields.seq));
        Map<String, List<SysDictItem>> map = dictData.stream().collect(Collectors.groupingBy(t->t.getSysDict().getCode().toUpperCase()));
        return map;
    }

    public Object getFinalKey(SysDictItem item) {
        String code = item.getCode();
        return code;
    }

}
