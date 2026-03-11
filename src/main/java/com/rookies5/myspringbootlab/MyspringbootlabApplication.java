package com.rookies5.myspringbootlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyspringbootlabApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyspringbootlabApplication.class, args);
    }

    @Bean
    public String hello(){
        System.out.println("=======Spring Bean입니다.========");
        return "Hello Bean";
    }
}