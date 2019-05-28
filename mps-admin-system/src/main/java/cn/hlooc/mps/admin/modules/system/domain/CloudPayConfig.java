package cn.hlooc.mps.admin.modules.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * 微信云支付配置类
 *
 * @author hlooc
 * @date 2019-05-21 21:14:36
 */
@Data
@Entity
@Table(name = "cloud_pay_config")
@ApiModel(value = "微信云支付配置对象")
public class CloudPayConfig implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 服务商ID
     */
    @NotBlank
    @Column(name = "out_mch_id")
    @ApiModelProperty(value = "服务商ID", example = "mch12346", required = true)
    private String outMchId;

    /**
     * 商户ID
     */
    @NotBlank
    @Column(name = "out_sub_mch_id")
    @ApiModelProperty(value = "商户ID", example = "sub12346", required = true)
    private String outSubMchId;

    /**
     * 商户的authen_key
     */
    @NotBlank
    @ApiModelProperty(value = "商户的authen_key", example = "key123456780", required = true)
    @Column(name = "authen_key", columnDefinition = "text")
    private String authenKey;

    /**
     * 创建日期
     */
    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;
}