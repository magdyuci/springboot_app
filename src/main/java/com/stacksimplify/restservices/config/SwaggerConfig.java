package com.stacksimplify.restservices.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                             .group("springshop-public")
                             .pathsToMatch("/users/**")
                             .build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(getInfo())
                            .externalDocs(getExternalDocs());
    }

    private ExternalDocumentation getExternalDocs() {
        return new ExternalDocumentation().description("Develop the first spring boot project")
                                          .url("https://myproject.maga.com/");
    }

    private Info getInfo() {
        return new Info().title("SpringBoot curse")
                         .description("WS restful API for SpringBoot curse")
                         .version("V1.0")
                         .license(new License().name("License of API")
                                               .url("http://www.magacurse.com"))
                         .termsOfService("Terms of service")
                         .contact(new Contact().email("maga@magacompany.com")
                                               .name("MagaMJ")
                                               .url("http://www.magacurse.com"));
    }
    // Swagger Metadata: http://localhost:8080/v2/api-docs
    // Swagger UI URL: http://localhost:8080/swagger-ui.html
}
