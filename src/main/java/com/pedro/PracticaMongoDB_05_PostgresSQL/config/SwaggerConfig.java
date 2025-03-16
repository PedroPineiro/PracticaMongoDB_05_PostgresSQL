package com.pedro.PracticaMongoDB_05_PostgresSQL.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuracion de swagger
 * @author pedro
 * @version 1.0
 */
@Configuration
public class SwaggerConfig {

    //version de la app
    @Value("${spring.application.version}")
    private String version;

    //nombre de la app
    @Value("${spring.application.name}")
    private String appName;

    /**
     * Metodo que devuelve una customizacion de swagger
     * @return el objeto OpenApi
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(appName)
                        .description("Tarea 5 de AD con microservicio de PostgreSQL")
                        .version(version));
    }
}