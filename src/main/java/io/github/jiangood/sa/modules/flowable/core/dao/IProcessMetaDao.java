package io.github.jiangood.sa.modules.flowable.core.dao;

import io.github.jiangood.sa.modules.flowable.core.config.meta.ProcessMeta;

import java.util.List;

public interface IProcessMetaDao {

    List<ProcessMeta> findProcessMetaList();



}
