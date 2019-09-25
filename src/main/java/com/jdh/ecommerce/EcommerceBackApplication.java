package com.jdh.ecommerce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("com.jdh.ecommerce.dao")
@EnableScheduling
public class EcommerceBackApplication {

    public static void main(String[] args) {
//        关闭热部署
//        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(EcommerceBackApplication.class, args);
    }


    /**
     *      * 文件上传配置
     *      * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("10240KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }

}
