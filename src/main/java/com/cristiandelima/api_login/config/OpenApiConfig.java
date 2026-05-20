package com.cristiandelima.api_login.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Users API")
                        .version("v1.0.0")
                        .description("Documentação completa dos endpoints da minha aplicação.")
                        .contact(new Contact().name("Cristian").email("cristian.meira15@gmail.com")));
    }
}
