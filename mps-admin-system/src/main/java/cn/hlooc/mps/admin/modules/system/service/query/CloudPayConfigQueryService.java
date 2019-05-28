package cn.hlooc.mps.admin.modules.system.service.query;

import cn.hlooc.mps.admin.modules.system.service.mapper.CloudPayConfigMapper;
import cn.hlooc.mps.admin.utils.PageUtil;
import cn.hlooc.mps.admin.modules.system.domain.CloudPayConfig;
import cn.hlooc.mps.admin.modules.system.service.dto.CloudPayConfigDTO;
import cn.hlooc.mps.admin.modules.system.repository.CloudPayConfigRepository;
import cn.hlooc.mps.admin.modules.system.service.mapper.CloudPayConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hlooc
 * @date 2018-12-03
 */
@Service
@CacheConfig(cacheNames = "cloudPayConfig")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CloudPayConfigQueryService {

    @Autowired
    private CloudPayConfigRepository cloudPayConfigRepository;

    @Autowired
    private CloudPayConfigMapper cloudPayConfigMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CloudPayConfigDTO cloudPayConfig, Pageable pageable){
        Page<CloudPayConfig> page = cloudPayConfigRepository.findAll(new Spec(cloudPayConfig),pageable);
        return PageUtil.toPage(page.map(cloudPayConfigMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CloudPayConfigDTO cloudPayConfig){
        return cloudPayConfigMapper.toDto(cloudPayConfigRepository.findAll(new Spec(cloudPayConfig)));
    }

    class Spec implements Specification<CloudPayConfig> {

        private CloudPayConfigDTO cloudPayConfig;

        public Spec(CloudPayConfigDTO cloudPayConfig){
            this.cloudPayConfig = cloudPayConfig;
        }

        @Override
        public Predicate toPredicate(Root<CloudPayConfig> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}