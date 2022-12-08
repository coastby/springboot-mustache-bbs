package com.mustache.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing
public class SpringbootMustacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMustacheApplication.class, args);
    }

}
