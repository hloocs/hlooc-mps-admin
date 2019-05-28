package cn.hlooc.mps.admin.modules.system.service.mapper;

import cn.hlooc.mps.admin.mapper.EntityMapper;
import cn.hlooc.mps.admin.modules.system.domain.CloudPayConfig;
import cn.hlooc.mps.admin.modules.system.service.dto.CloudPayConfigDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hlooc
* @date 2019-05-21
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CloudPayConfigMapper extends EntityMapper<CloudPayConfigDTO, CloudPayConfig> {

}