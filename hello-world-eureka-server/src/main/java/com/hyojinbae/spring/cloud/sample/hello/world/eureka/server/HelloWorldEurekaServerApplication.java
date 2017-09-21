package com.hyojinbae.spring.cloud.sample.hello.world.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HelloWorldEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldEurekaServerApplication.class, args);
    }
}
