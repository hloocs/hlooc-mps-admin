package cn.hlooc.mps.admin.modules.system.service.mapper;

import cn.hlooc.mps.admin.mapper.EntityMapper;
import cn.hlooc.mps.admin.mapper.EntityMapper;
import cn.hlooc.mps.admin.modules.system.domain.Job;
import cn.hlooc.mps.admin.modules.system.service.dto.JobDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hlooc
* @date 2019-03-29
*/
@Mapper(componentModel = "spring",uses = {DeptMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper extends EntityMapper<JobDTO, Job> {

}