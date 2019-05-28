package cn.hlooc.mps.admin.modules.system.service.mapper;

import cn.hlooc.mps.admin.mapper.EntityMapper;
import cn.hlooc.mps.admin.modules.system.domain.Role;
import cn.hlooc.mps.admin.mapper.EntityMapper;
import cn.hlooc.mps.admin.modules.system.service.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author hlooc
 * @date 2018-11-23
 */
@Mapper(componentModel = "spring", uses = {PermissionMapper.class, MenuMapper.class, DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

}
