package com.rkzt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class rkztApplication {
    public static void main(String[] args) {
        SpringApplication.run(rkztApplication.class,args);
        System.out.println("111");
    }
}
