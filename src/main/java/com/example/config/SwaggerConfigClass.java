package com.example.config;

import com.example.controller.ResolvedorController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@PropertySource("classpath:swagger.properties")
@ComponentScan(basePackageClasses = ResolvedorController.class)
public class SwaggerConfigClass {

    private static final String VERSION = "1.0";
    private static final String LICENCE_TEXT = "License";
    private static final String TITLE = "Painel REST API";
    private static final String DESCRIPTION = "RESTFul API para definição da ordem de resolução de incidentes.";

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .version(VERSION)
                .license(LICENCE_TEXT)
                .title(TITLE)
                .description(DESCRIPTION)
                .build();
    }

    @Bean
    public Docket productsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/docs")
                .select()
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
}
