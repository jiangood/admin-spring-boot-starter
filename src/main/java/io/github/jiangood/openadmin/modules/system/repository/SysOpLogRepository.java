package io.github.jiangood.openadmin.modules.system.repository;

import io.github.jiangood.openadmin.framework.data.BaseRepository;
import io.github.jiangood.openadmin.modules.system.entity.SysLog;
import org.springframework.stereotype.Repository;

/**
 * 系统访问日志mapper
 */
@Repository
public interface SysOpLogRepository extends BaseRepository<SysOpLog, String> {


}
