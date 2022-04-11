package com.stacksimplify.restservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
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
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo())
                                                      .select()
                                                      .apis(RequestHandlerSelectors.basePackage("com.stacksimplify.restservices"))
                                                      .paths(PathSelectors.ant("/users/**")) //we will see only the controllers that have /users/ at the beginning of the url
                                                      .build();
    }

    // Swagger Metadata: http://localhost:8080/v2/api-docs
    // Swagger UI URL: http://localhost:8080/swagger-ui.html

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("StackSimplify User Management Service")
                                   .description("This page list all the specification Spi User Management Api")
                                   .version("2.0")
                                   .contact(new Contact("Maga Study", "http://www.magastudyever.es", "magauci@gmail.es"))
                                   .license("License 2")
                                   .licenseUrl("http://www.maga.es/license.html")
                                   .build();
    }
}
