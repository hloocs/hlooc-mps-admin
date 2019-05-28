package cn.hlooc.mps.admin.modules.system.service.mapper;

import cn.hlooc.mps.admin.mapper.EntityMapper;
import cn.hlooc.mps.admin.mapper.EntityMapper;
import cn.hlooc.mps.admin.modules.system.domain.Dict;
import cn.hlooc.mps.admin.modules.system.service.dto.DictDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hlooc
* @date 2019-04-10
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictMapper extends EntityMapper<DictDTO, Dict> {

}