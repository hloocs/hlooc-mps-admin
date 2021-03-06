package cn.hlooc.mps.admin.modules.system.service;

import cn.hlooc.mps.admin.modules.system.service.dto.DeptDTO;
import cn.hlooc.mps.admin.modules.system.domain.Dept;
import cn.hlooc.mps.admin.modules.system.service.dto.DeptDTO;
import cn.hlooc.mps.admin.modules.system.domain.Dept;
import cn.hlooc.mps.admin.modules.system.service.dto.DeptDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
* @author hlooc
* @date 2019-03-25
*/
@CacheConfig(cacheNames = "dept")
public interface DeptService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    DeptDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    DeptDTO create(Dept resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Dept resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * buildTree
     * @param deptDTOS
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    Object buildTree(List<DeptDTO> deptDTOS);

    /**
     * findByPid
     * @param pid
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    List<Dept> findByPid(long pid);
}