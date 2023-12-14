package com.untucapital.cms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
public class CmsApplication {

    public static void main(String[] args) {

        SpringApplication.run(CmsApplication.class, args);
        log.info("<<<<<<CMS APPLICATION STARTED SUCCESSFULLY>>>>>>");
    }

}
