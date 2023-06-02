package com.neutech.mammalia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class BiologicalScienceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiologicalScienceApplication.class, args);
    }

}
