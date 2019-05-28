package cn.hlooc.mps.admin.service.impl;

import cn.hlooc.mps.admin.repository.LogRepository;
import cn.hlooc.mps.admin.utils.RequestHolder;
import cn.hlooc.mps.admin.utils.SecurityUtils;
import cn.hlooc.mps.admin.utils.StringUtils;
import cn.hutool.json.JSONObject;
import cn.hlooc.mps.admin.repository.LogRepository;
import cn.hlooc.mps.admin.utils.RequestHolder;
import cn.hlooc.mps.admin.utils.SecurityUtils;
import cn.hlooc.mps.admin.utils.StringUtils;
import cn.hlooc.mps.admin.domain.Log;
import cn.hlooc.mps.admin.repository.LogRepository;
import cn.hlooc.mps.admin.service.LogService;
import cn.hlooc.mps.admin.utils.RequestHolder;
import cn.hlooc.mps.admin.utils.SecurityUtils;
import cn.hlooc.mps.admin.utils.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author hlooc
 * @date 2018-11-24
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    private final String LOGINPATH = "login";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProceedingJoinPoint joinPoint, Log log){

        // 获取request
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        cn.hlooc.mps.admin.aop.log.Log aopLog = method.getAnnotation(cn.hlooc.mps.admin.aop.log.Log.class);

        // 描述
        if (log != null) {
            log.setDescription(aopLog.value());
        }

        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName()+"."+signature.getName()+"()";

        String params = "{";
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        // 用户名
        String username = "";

        if(argValues != null){
            for (int i = 0; i < argValues.length; i++) {
                params += " " + argNames[i] + ": " + argValues[i];
            }
        }

        // 获取IP地址
        log.setRequestIp(StringUtils.getIP(request));

        if(!LOGINPATH.equals(signature.getName())){
            username = SecurityUtils.getUsername();
        } else {
            try {
                JSONObject jsonObject = new JSONObject(argValues[0]);
                username = jsonObject.get("username").toString();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(params + " }");
        logRepository.save(log);
    }
}
