package com.hyojinbae.spring.cloud.sample.hello.world.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
public class WorldServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorldServiceApplication.class, args);
    }
}

@RefreshScope
@RestController
class Controller {

    @Value("${world.service.message}")
    private String message;

    @RequestMapping("/message")
    public String message() {
        return this.message;
    }
}