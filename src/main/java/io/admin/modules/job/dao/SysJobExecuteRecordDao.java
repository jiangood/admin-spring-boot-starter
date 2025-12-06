package io.admin.modules.job.dao;

import io.admin.modules.job.entity.SysJob;
import io.admin.modules.job.entity.SysJobExecuteRecord;
import io.admin.framework.data.repository.BaseDao;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class SysJobExecuteRecordDao extends BaseDao<SysJobExecuteRecord> {


    @Transactional
    public void deleteByJobId(String jobId) {
        JpaQuery<SysJobExecuteRecord> q = new JpaQuery<>();
        q.eq(SysJobExecuteRecord.Fields.sysJob, new SysJob(jobId));
        List<SysJobExecuteRecord> list = findAll(q);
        this.deleteAll(list);
    }


}
