package cn.hlooc.mps.admin.utils;

import cn.hlooc.mps.admin.exception.BadRequestException;
import cn.hlooc.mps.admin.exception.BadRequestException;

import java.util.Optional;

/**
 * 验证工具
 *
 * @author hlooc
 * @date 2018-11-23
 */
public class ValidationUtil {

    /**
     * 验证空
     *
     * @param optional 验证对象
     */
    public static void isNull(Optional optional, String entity, String parameter, Object value) {
        if (!optional.isPresent()) {
            String msg = entity
                    + " 不存在 "
                    + "{ " + parameter + ":" + value.toString() + " }";
            throw new BadRequestException(msg);
        }
    }

    /**
     * 验证是否为邮箱
     *
     * @param string 邮箱
     * @return 校验结果
     */
    public static boolean isEmail(String string) {
        if (string == null) {
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return string.matches(regEx1);
    }
}
