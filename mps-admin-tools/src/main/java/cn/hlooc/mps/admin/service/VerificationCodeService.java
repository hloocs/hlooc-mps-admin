package cn.hlooc.mps.admin.service;

import cn.hlooc.mps.admin.domain.VerificationCode;
import cn.hlooc.mps.admin.domain.VerificationCode;
import cn.hlooc.mps.admin.domain.vo.EmailVo;

/**
 * @author hlooc
 * @date 2018-12-26
 */
public interface VerificationCodeService {

    /**
     * 发送邮件验证码
     * @param code
     */
    EmailVo sendEmail(VerificationCode code);

    /**
     * 验证
     * @param code
     */
    void validated(VerificationCode code);
}
