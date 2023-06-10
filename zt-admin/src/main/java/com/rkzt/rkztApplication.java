package com.rkzt;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@MapperScan("com.rkzt.common.mapper")
public class rkztApplication {
    public static void main(String[] args) {
        SpringApplication.run(rkztApplication.class,args);
        System.out.println("111");
    }
}
