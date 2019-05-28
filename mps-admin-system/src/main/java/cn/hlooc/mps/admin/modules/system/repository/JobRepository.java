package cn.hlooc.mps.admin.modules.system.repository;

import cn.hlooc.mps.admin.modules.system.domain.Job;
import cn.hlooc.mps.admin.modules.system.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hlooc
* @date 2019-03-29
*/
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor {
}