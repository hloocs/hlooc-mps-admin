package cn.hlooc.mps.admin.modules.system.service.impl;

import cn.hlooc.mps.admin.modules.system.service.mapper.DictDetailMapper;
import cn.hlooc.mps.admin.modules.system.repository.DictDetailRepository;
import cn.hlooc.mps.admin.modules.system.service.mapper.DictDetailMapper;
import cn.hlooc.mps.admin.utils.ValidationUtil;
import cn.hlooc.mps.admin.modules.system.domain.DictDetail;
import cn.hlooc.mps.admin.utils.ValidationUtil;
import cn.hlooc.mps.admin.modules.system.repository.DictDetailRepository;
import cn.hlooc.mps.admin.modules.system.service.DictDetailService;
import cn.hlooc.mps.admin.modules.system.service.dto.DictDetailDTO;
import cn.hlooc.mps.admin.modules.system.service.mapper.DictDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author hlooc
* @date 2019-04-10
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictDetailServiceImpl implements DictDetailService {

    @Autowired
    private DictDetailRepository dictDetailRepository;

    @Autowired
    private DictDetailMapper dictDetailMapper;

    @Override
    public DictDetailDTO findById(Long id) {
        Optional<DictDetail> dictDetail = dictDetailRepository.findById(id);
        ValidationUtil.isNull(dictDetail,"DictDetail","id",id);
        return dictDetailMapper.toDto(dictDetail.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DictDetailDTO create(DictDetail resources) {
        return dictDetailMapper.toDto(dictDetailRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DictDetail resources) {
        Optional<DictDetail> optionalDictDetail = dictDetailRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalDictDetail,"DictDetail","id",resources.getId());

        DictDetail dictDetail = optionalDictDetail.get();
        // 此处需自己修改
        resources.setId(dictDetail.getId());
        dictDetailRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        dictDetailRepository.deleteById(id);
    }
}