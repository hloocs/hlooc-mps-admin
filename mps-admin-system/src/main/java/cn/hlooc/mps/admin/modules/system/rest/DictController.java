package cn.hlooc.mps.admin.modules.system.rest;

import cn.hlooc.mps.admin.modules.system.domain.Dict;
import cn.hlooc.mps.admin.modules.system.service.DictService;
import cn.hlooc.mps.admin.modules.system.service.dto.DictDTO;
import cn.hlooc.mps.admin.modules.system.service.query.DictQueryService;
import cn.hlooc.mps.admin.exception.BadRequestException;
import cn.hlooc.mps.admin.modules.system.domain.Dict;
import cn.hlooc.mps.admin.modules.system.service.DictService;
import cn.hlooc.mps.admin.modules.system.service.dto.DictDTO;
import cn.hlooc.mps.admin.modules.system.service.query.DictQueryService;
import cn.hlooc.mps.admin.aop.log.Log;
import cn.hlooc.mps.admin.exception.BadRequestException;
import cn.hlooc.mps.admin.modules.system.domain.Dict;
import cn.hlooc.mps.admin.modules.system.service.DictService;
import cn.hlooc.mps.admin.modules.system.service.dto.DictDTO;
import cn.hlooc.mps.admin.modules.system.service.query.DictQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author hlooc
* @date 2019-04-10
*/
@RestController
@RequestMapping("api")
public class DictController {

    @Autowired
    private DictService dictService;

    @Autowired
    private DictQueryService dictQueryService;

    private static final String ENTITY_NAME = "dict";

    @Log("查询字典")
    @GetMapping(value = "/dict")
    @PreAuthorize("hasAnyRole('ADMIN','DICT_ALL','DICT_SELECT')")
    public ResponseEntity getDicts(DictDTO resources, Pageable pageable){
        return new ResponseEntity(dictQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增字典")
    @PostMapping(value = "/dict")
    @PreAuthorize("hasAnyRole('ADMIN','DICT_ALL','DICT_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Dict resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(dictService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改字典")
    @PutMapping(value = "/dict")
    @PreAuthorize("hasAnyRole('ADMIN','DICT_ALL','DICT_EDIT')")
    public ResponseEntity update(@Validated(Dict.Update.class) @RequestBody Dict resources){
        dictService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除字典")
    @DeleteMapping(value = "/dict/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DICT_ALL','DICT_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        dictService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}