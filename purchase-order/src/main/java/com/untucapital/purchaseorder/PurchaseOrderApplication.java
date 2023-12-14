package com.untucapital.purchaseorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PurchaseOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurchaseOrderApplication.class, args);
    }

}
