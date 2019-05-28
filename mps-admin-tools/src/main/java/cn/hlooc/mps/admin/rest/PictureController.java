package cn.hlooc.mps.admin.rest;

import cn.hlooc.mps.admin.domain.Picture;
import cn.hlooc.mps.admin.service.PictureService;
import cn.hlooc.mps.admin.service.query.PictureQueryService;
import cn.hlooc.mps.admin.utils.SecurityUtils;
import cn.hlooc.mps.admin.domain.Picture;
import cn.hlooc.mps.admin.service.PictureService;
import cn.hlooc.mps.admin.service.query.PictureQueryService;
import cn.hlooc.mps.admin.utils.SecurityUtils;
import cn.hlooc.mps.admin.aop.log.Log;
import cn.hlooc.mps.admin.domain.Picture;
import cn.hlooc.mps.admin.service.PictureService;
import cn.hlooc.mps.admin.service.query.PictureQueryService;
import cn.hlooc.mps.admin.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hlooc
 * @date 2018/09/20 14:13:32
 */
@RestController
@RequestMapping("/api")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @Autowired
    private PictureQueryService pictureQueryService;

    @Log("查询图片")
    @PreAuthorize("hasAnyRole('ADMIN','PICTURE_ALL','PICTURE_SELECT')")
    @GetMapping(value = "/pictures")
    public ResponseEntity getRoles(Picture resources, Pageable pageable){
        return new ResponseEntity(pictureQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    /**
     * 上传图片
     * @param file
     * @return
     * @throws Exception
     */
    @Log("上传图片")
    @PreAuthorize("hasAnyRole('ADMIN','PICTURE_ALL','PICTURE_UPLOAD')")
    @PostMapping(value = "/pictures")
    public ResponseEntity upload(@RequestParam MultipartFile file){
        String userName = SecurityUtils.getUsername();
        Picture picture = pictureService.upload(file,userName);
        Map map = new HashMap();
        map.put("errno",0);
        map.put("id",picture.getId());
        map.put("data",new String[]{picture.getUrl()});
        return new ResponseEntity(map,HttpStatus.OK);
    }

    /**
     * 删除图片
     * @param id
     * @return
     */
    @Log("删除图片")
    @PreAuthorize("hasAnyRole('ADMIN','PICTURE_ALL','PICTURE_DELETE')")
    @DeleteMapping(value = "/pictures/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        pictureService.delete(pictureService.findById(id));
        return new ResponseEntity(HttpStatus.OK);
    }
}
