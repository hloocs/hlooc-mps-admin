package cn.hlooc.mps.admin.repository;

import cn.hlooc.mps.admin.domain.EmailConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hlooc
 * @date 2018-12-26
 */
public interface EmailRepository extends JpaRepository<EmailConfig,Long> {
}
