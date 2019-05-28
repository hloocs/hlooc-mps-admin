package cn.hlooc.mps.admin.service;

import cn.hlooc.mps.admin.domain.Log;
import cn.hlooc.mps.admin.domain.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

/**
 * @author hlooc
 * @date 2018-11-24
 */
public interface LogService {

    /**
     * 新增日志
     * @param joinPoint
     * @param log
     */
    @Async
    void save(ProceedingJoinPoint joinPoint, Log log);
}
