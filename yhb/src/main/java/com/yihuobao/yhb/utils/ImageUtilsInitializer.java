package com.yihuobao.yhb.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class ImageUtilsInitializer {
    @Value("${image.upload.path}")
    private String uploadPath;

    @Value("${image.access.url}")
    private String accessUrl;

    @PostConstruct
    public void init() {
        ImageUtils.init(uploadPath, accessUrl);
    }
}