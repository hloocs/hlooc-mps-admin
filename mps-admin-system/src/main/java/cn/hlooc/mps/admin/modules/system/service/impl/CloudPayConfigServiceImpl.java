package cn.hlooc.mps.admin.modules.system.service.impl;

import cn.hlooc.mps.admin.modules.system.service.mapper.CloudPayConfigMapper;
import cn.hlooc.mps.admin.modules.system.domain.CloudPayConfig;
import cn.hlooc.mps.admin.utils.ValidationUtil;
import cn.hlooc.mps.admin.modules.system.repository.CloudPayConfigRepository;
import cn.hlooc.mps.admin.modules.system.service.CloudPayConfigService;
import cn.hlooc.mps.admin.modules.system.service.dto.CloudPayConfigDTO;
import cn.hlooc.mps.admin.modules.system.service.mapper.CloudPayConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author hlooc
* @date 2019-05-21
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CloudPayConfigServiceImpl implements CloudPayConfigService {

    @Autowired
    private CloudPayConfigRepository cloudPayConfigRepository;

    @Autowired
    private CloudPayConfigMapper cloudPayConfigMapper;

    @Override
    public CloudPayConfigDTO findById(Long id) {
        Optional<CloudPayConfig> cloudPayConfig = cloudPayConfigRepository.findById(id);
        ValidationUtil.isNull(cloudPayConfig,"CloudPayConfig","id",id);
        return cloudPayConfigMapper.toDto(cloudPayConfig.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CloudPayConfigDTO create(CloudPayConfig resources) {
        return cloudPayConfigMapper.toDto(cloudPayConfigRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CloudPayConfig resources) {
        Optional<CloudPayConfig> optionalCloudPayConfig = cloudPayConfigRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalCloudPayConfig,"CloudPayConfig","id",resources.getId());

        CloudPayConfig cloudPayConfig = optionalCloudPayConfig.get();
        // 此处需自己修改
        resources.setId(cloudPayConfig.getId());
        cloudPayConfigRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        cloudPayConfigRepository.deleteById(id);
    }
}