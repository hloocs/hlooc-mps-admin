package cn.hlooc.mps.admin.modules.system.service.mapper;

import cn.hlooc.mps.admin.mapper.EntityMapper;
import cn.hlooc.mps.admin.modules.system.domain.Menu;
import cn.hlooc.mps.admin.mapper.EntityMapper;
import cn.hlooc.mps.admin.modules.system.service.dto.MenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author hlooc
 * @date 2018-12-17
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends EntityMapper<MenuDTO, Menu> {

}
