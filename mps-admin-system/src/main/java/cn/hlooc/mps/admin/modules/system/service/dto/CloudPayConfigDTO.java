package cn.hlooc.mps.admin.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author hlooc
* @date 2019-05-21
*/
@Data
public class CloudPayConfigDTO implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 商户秘钥
     */
    private String authenKey;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 服务商ID
     */
    private String outMchId;

    /**
     * 商户ID
     */
    private String outSubMchId;
}