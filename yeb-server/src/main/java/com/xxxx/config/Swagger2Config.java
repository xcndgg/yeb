//package com.xxxx.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@EnableSwagger2


//项目在这里一直没有调试出来，所以换成了knife4j-2.0.7
//在maven中添加坐标
    //<!--knife4j-->
    //<dependency>
    //<groupId>com.github.xiaoymin</groupId>
    //<artifactId>knife4j-spring-boot-starter</artifactId>
    //<version>2.0.7</version>
    //</dependency>
//注意knif4j中已经包含了swwager,所以不要在导入swwager坐标
//在application.yml中添加以下配置
//spring:
//mvc:
// pathmatch:
//     matching-strategy: ant_path_matcher




//public class Swagger2Config extends WebMvcConfigurationSupport {
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.xxxx.controller"))
//                .paths(PathSelectors.any())
//                .build();
//
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("云E办接口文档")
//                .description("云E办接口文档")
//                .contact(new Contact("xxxx", "http:localhost:8081/doc.html", "xxxx@xxxx.com"))
//                .version("1.0")
//                .build();
//    }
//
//}
