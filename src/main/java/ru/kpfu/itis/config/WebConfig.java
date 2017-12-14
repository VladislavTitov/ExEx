package ru.kpfu.itis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;

@Configuration
@EnableWebMvc
@ComponentScan("ru.kpfu.itis")
@PropertySource("classpath:ru.kpfu.itis/files.properties")
public class WebConfig {

    private Environment environment;

    private long maxSize = 10 * 1024 * 1024;

    @Autowired
    public WebConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSizePerFile(maxSize);
        return new CommonsMultipartResolver();
    }

    @Bean(name = "baseStoragePath")
    public String getBasePath() {
        String basePath = environment.getProperty("path.base");
        if (basePath == null || basePath.isEmpty()) {
            return System.getProperty("user.home") + File.pathSeparator + "files";
        }
        return basePath;
    }

    @Bean(name = "imageStoragePath")
    public String getImagesStoragePath() {
        String imageStoragePath = environment.getProperty("path.images");
        if (imageStoragePath == null || imageStoragePath.isEmpty()) {
            return getBasePath() + File.pathSeparator + "images";
        }
        return imageStoragePath;
    }

    @Bean(name = "baseStorageDir")
    public File getBaseStorageDir() {
        return new File(getBasePath());
    }

    @Bean(name = "imageStorageDir")
    public File getImageStorageDir() {
        return new File(getImagesStoragePath());
    }

}
