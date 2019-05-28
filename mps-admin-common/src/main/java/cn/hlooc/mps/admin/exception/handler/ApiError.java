package cn.hlooc.mps.admin.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hlooc
 * @date 2019-05-18 17:35:05
 */
@Data
@AllArgsConstructor
@Builder
@ApiModel(value = "异常响应对象", description = "响应异常信息")
class ApiError {


    @ApiModelProperty(value = "响应状态", example = "200")
    private int status;

    @ApiModelProperty(value = "响应时间", example = "2019-05-18 16:17:20")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    @ApiModelProperty(value = "错误代码", example = "100001")
    private String code;

    @ApiModelProperty(value = "错误详情")
    private String message;
}


