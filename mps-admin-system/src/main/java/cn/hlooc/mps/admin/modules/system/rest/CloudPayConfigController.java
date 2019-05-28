package cn.hlooc.mps.admin.modules.system.rest;

import cn.hlooc.mps.admin.modules.system.domain.CloudPayConfig;
import cn.hlooc.mps.admin.modules.system.service.CloudPayConfigService;
import cn.hlooc.mps.admin.modules.system.service.dto.CloudPayConfigDTO;
import cn.hlooc.mps.admin.modules.system.service.query.CloudPayConfigQueryService;
import cn.hlooc.mps.admin.aop.log.Log;
import cn.hlooc.mps.admin.exception.BadRequestException;
import cn.hlooc.mps.admin.modules.system.domain.CloudPayConfig;
import cn.hlooc.mps.admin.modules.system.service.CloudPayConfigService;
import cn.hlooc.mps.admin.modules.system.service.dto.CloudPayConfigDTO;
import cn.hlooc.mps.admin.modules.system.service.query.CloudPayConfigQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author hlooc
* @date 2019-05-21
*/
@RestController
@RequestMapping("api")
public class CloudPayConfigController {

    @Autowired
    private CloudPayConfigService cloudPayConfigService;

    @Autowired
    private CloudPayConfigQueryService cloudPayConfigQueryService;

    private static final String ENTITY_NAME = "cloudPayConfig";

    @Log("查询CloudPayConfig")
    @GetMapping(value = "/cloudPayConfig")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getCloudPayConfigs(CloudPayConfigDTO resources, Pageable pageable){
        return new ResponseEntity(cloudPayConfigQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增CloudPayConfig")
    @PostMapping(value = "/cloudPayConfig")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody CloudPayConfig resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(cloudPayConfigService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改CloudPayConfig")
    @PutMapping(value = "/cloudPayConfig")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody CloudPayConfig resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        cloudPayConfigService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除CloudPayConfig")
    @DeleteMapping(value = "/cloudPayConfig/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        cloudPayConfigService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}