package com.xxxx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author xcdgg
 * @description
 * @date 2022/1/27 1:03
 */
@SpringBootApplication
@EnableSwagger2WebMvc
@MapperScan("com.xxxx.mapper")
public class YebApplication {
    public static void main(String[] args) {
        SpringApplication.run(YebApplication.class,args);
    }
}
