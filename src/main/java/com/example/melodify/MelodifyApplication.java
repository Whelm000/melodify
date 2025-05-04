package com.example.melodify;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.melodify.mapper")
@SpringBootApplication
public class MelodifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MelodifyApplication.class, args);
    }

}
