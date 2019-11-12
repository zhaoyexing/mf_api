package com.st.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

/**
 * Created by zhaoyx on 2016/8/15.
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.st.web.controller"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(LocalDate.class,String.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("蜜丝佛陀微信优惠券接口")
                .description("蜜丝佛陀微信优惠券数据接口API，包括请求url、请求方式、参数、返回数据格式等！")
                .build();
    }
}
