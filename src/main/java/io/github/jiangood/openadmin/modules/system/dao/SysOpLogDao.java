package io.github.jiangood.openadmin.modules.system.dao;

import io.github.jiangood.openadmin.framework.data.BaseRepository;
import io.github.jiangood.openadmin.modules.system.entity.SysLog;
import org.springframework.stereotype.Repository;

/**
 * 系统访问日志mapper
 */
@Repository
public interface SysOpLogDao extends BaseRepository<SysLog, String> {


}
