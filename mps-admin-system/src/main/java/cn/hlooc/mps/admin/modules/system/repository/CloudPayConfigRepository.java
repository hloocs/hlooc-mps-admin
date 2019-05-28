package cn.hlooc.mps.admin.modules.system.repository;

import cn.hlooc.mps.admin.modules.system.domain.CloudPayConfig;
import cn.hlooc.mps.admin.modules.system.domain.CloudPayConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hlooc
* @date 2019-05-21
*/
public interface CloudPayConfigRepository extends JpaRepository<CloudPayConfig, Long>, JpaSpecificationExecutor {
}