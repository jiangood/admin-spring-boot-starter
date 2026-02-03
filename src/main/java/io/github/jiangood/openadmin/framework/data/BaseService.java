package io.github.jiangood.openadmin.framework.data;

import io.github.jiangood.openadmin.framework.data.specification.Spec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 基础服务类
 */
@Slf4j
public abstract class BaseService<T extends Persistable<String>> {



    @Autowired
    protected BaseDao<T> baseDao;

    /**
     * 保存
     * controller调用
     * 更新时，指定字段更新
     * 防止了全字段更新，以免有些字段非前端输入的情况
     */
    @Transactional
    public T save(T input, List<String> requestKeys) throws Exception {
        if (input.isNew()) {
            return baseDao.save(input);
        }

        baseDao.updateField(input, requestKeys);
        return baseDao.findById(input.getId());
    }

    /**
     * 删除
     * controller调用
     */
    @Transactional
    public void delete(String id) {
        baseDao.deleteById(id);
    }

    public Page<T> getPage(Specification<T> spec, Pageable pageable) {
        return baseDao.findAll(spec, pageable);
    }

    /**
     * 详情
     * controller调用
     *
     */
    public T detail(String id) {
        return baseDao.findById(id);
    }


    // ============== 一下为dao层直接调用===================
    public T get(String id) {
        return baseDao.findById(id);
    }

    public List<T> getAll() {
        return baseDao.findAll();
    }

    public List<T> getAll(Sort sort) {
        return baseDao.findAll(sort);
    }
    public List<T> getAll(Specification<T> s, Sort sort) {
        return baseDao.findAll(s,sort);
    }
    public  Spec<T> spec(){
        return Spec.of();
    }

    public T save(T t){
        return baseDao.save(t);
    }


}
