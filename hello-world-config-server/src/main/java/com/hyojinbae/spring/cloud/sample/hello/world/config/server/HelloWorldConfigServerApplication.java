package com.hyojinbae.spring.cloud.sample.hello.world.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.ConfigServerApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class HelloWorldConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldConfigServerApplication.class, args);
    }
}
