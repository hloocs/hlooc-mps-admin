package cn.hlooc.mps.admin.modules.system.service;

import cn.hlooc.mps.admin.modules.system.service.dto.CloudPayConfigDTO;
import cn.hlooc.mps.admin.modules.system.domain.CloudPayConfig;
import cn.hlooc.mps.admin.modules.system.service.dto.CloudPayConfigDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author hlooc
* @date 2019-05-21
*/
@CacheConfig(cacheNames = "cloudPayConfig")
public interface CloudPayConfigService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    CloudPayConfigDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    CloudPayConfigDTO create(CloudPayConfig resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(CloudPayConfig resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}