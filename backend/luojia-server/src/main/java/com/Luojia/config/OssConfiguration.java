package com.Luojia.config;

import com.Luojia.properties.AliOssProperties;
import com.Luojia.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class OssConfiguration {

    @Bean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){

        log.info("开始创建阿里云上传文件工具类对象：{}",aliOssProperties);
        return new AliOssUtil(aliOssProperties.getEndpoint(),
                aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(),
                aliOssProperties.getBucketName());
    }
}
