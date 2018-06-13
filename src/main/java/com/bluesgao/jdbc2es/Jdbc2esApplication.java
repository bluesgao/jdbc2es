package com.bluesgao.jdbc2es;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bluesgao.jdbc2es.dao")
public class Jdbc2esApplication {

    public static void main(String[] args) {
        SpringApplication.run(Jdbc2esApplication.class, args);
    }
}
