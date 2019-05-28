package cn.hlooc.mps.admin.modules.system.repository;

import cn.hlooc.mps.admin.modules.system.domain.DictDetail;
import cn.hlooc.mps.admin.modules.system.domain.DictDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hlooc
* @date 2019-04-10
*/
public interface DictDetailRepository extends JpaRepository<DictDetail, Long>, JpaSpecificationExecutor {
}