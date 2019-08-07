package com.workforceconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    	@Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                    .basePackage("com.workforce.beans"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiInfo());
        }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("My REST API", "Some custom description of API.", "API TOS", "Terms of service", 
        		new Contact().toString(), "License of API", "API license URL");
        return apiInfo;
    }
}