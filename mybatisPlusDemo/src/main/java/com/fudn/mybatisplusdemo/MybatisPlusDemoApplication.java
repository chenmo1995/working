package com.fudn.mybatisplusdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author fdn
 * @since 2021-08-26 15:47
 */
@MapperScan("com.fudn.mybatisplusdemo.module.mapper")
@SpringBootApplication
//@EnableApiLog
@EnableSwagger2
public class MybatisPlusDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusDemoApplication.class, args);
    }

}
