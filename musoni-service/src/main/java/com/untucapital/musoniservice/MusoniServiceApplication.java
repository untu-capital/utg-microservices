package com.untucapital.musoniservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MusoniServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusoniServiceApplication.class, args);
    }

}
