package com.mu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RabcApp {

    public static void main(String[] args) {
        SpringApplication.run(RabcApp.class,args);
    }
}
