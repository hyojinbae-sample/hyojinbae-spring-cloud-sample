package com.hyojinbae.spring.cloud.sample.hello.world.config.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class HelloWorldConfigClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldConfigClient1Application.class, args);
    }
}

@RefreshScope
@RestController
class ClientController {

    @Value("${spring.datasource.username}")
    private String userName;

    @RequestMapping("/username")
    public String query() {
        return this.userName;
    }
}
