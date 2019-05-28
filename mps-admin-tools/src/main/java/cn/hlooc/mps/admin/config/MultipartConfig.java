package cn.hlooc.mps.admin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * @author https://blog.csdn.net/llibin1024530411/article/details/79474953
 * @date 2018-12-28
 */
@Configuration
@Slf4j
public class MultipartConfig {

    private static final String SYS_DIR = System.getProperty("user.home") + "/.hlooc/file/tmp";

    /**
     * 文件上传临时路径
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        File tmpFile = new File(SYS_DIR);
        if (!tmpFile.exists() && tmpFile.mkdirs()) {
            log.info("初始化目录:{}", SYS_DIR);
        }
        factory.setLocation(SYS_DIR);
        return factory.createMultipartConfig();
    }
}