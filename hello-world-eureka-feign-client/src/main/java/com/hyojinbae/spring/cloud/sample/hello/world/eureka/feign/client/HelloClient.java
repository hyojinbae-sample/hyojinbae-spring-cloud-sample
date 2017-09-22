package com.hyojinbae.spring.cloud.sample.hello.world.eureka.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("hello-world-eureka-client")
public interface HelloClient {
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String hello();
}
