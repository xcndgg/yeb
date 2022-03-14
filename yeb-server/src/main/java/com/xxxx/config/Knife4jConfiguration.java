package com.xxxx.config;

/**
 * @author xcdgg
 * @description
 * @date 2022/2/3 14:17
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
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
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration{

    @Bean(value = "Api")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("云e办")
                        .description("Spring加SpringSecurity配置knife4j测试")
                        .termsOfServiceUrl("http://www.xc.top/")
                        .contact(new Contact("xc","http://www.xc.top/","2207346898@qq.com"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("2.0版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.xxxx.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
