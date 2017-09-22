package com.hyojinbae.spring.cloud.sample.hello.world.eureka.feign.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
@RestController
public class HelloWorldEurekaFeignClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldEurekaFeignClientApplication.class, args);
    }

    @Autowired
    HelloClient client;

    @RequestMapping("/helloworld")
    public String helloworld() {
        return client.hello() + " World!";
    }
}
