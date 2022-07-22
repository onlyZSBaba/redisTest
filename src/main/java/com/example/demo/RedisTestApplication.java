package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class RedisTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisTestApplication.class, args);
        log.info("open http://162.14.96.42:8080/apidoc");
        log.info("open http://localhost:8080/apidoc \n" +
                "http://localhost:8080/druid/index.html");
    }

}
