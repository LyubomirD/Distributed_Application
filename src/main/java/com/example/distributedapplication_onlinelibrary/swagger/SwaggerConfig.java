package com.example.distributedapplication_onlinelibrary.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.distributedapplication_onlinelibrary"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfoMetaData())
                .host("http://localhost:8080");
    }

    private ApiInfo apiInfoMetaData() {

        return new ApiInfoBuilder().title("API Documentation")
                .title("Online Library API")
                .description("API documentation for the Online Library application")
                .version("1.0.0")
                .contact(new Contact("Dev-Team", "https://example.com/", "lubodimoff@gmail.com"))
                .build();
    }
}
