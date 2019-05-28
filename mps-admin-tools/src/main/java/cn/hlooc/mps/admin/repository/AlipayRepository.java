package cn.hlooc.mps.admin.repository;

import cn.hlooc.mps.admin.domain.AlipayConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hlooc
 * @date 2018-12-31
 */
public interface AlipayRepository extends JpaRepository<AlipayConfig,Long> {
}
