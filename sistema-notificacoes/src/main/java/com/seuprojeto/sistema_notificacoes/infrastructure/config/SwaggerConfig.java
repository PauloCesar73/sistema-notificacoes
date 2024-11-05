// infrastructure/config/SwaggerConfig.java
package com.seuprojeto.sistema_notificacoes.infrastructure.config;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Define o esquema de segurança para o Bearer Token
        SecurityScheme securityScheme = new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        // Configura o OpenAPI com o esquema de segurança, as informações da API e as tags
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("BearerAuth", securityScheme))
                .info(new Info()
                        .title("Sistema de Notificações API")
                        .version("1.0")
                        .description("API para gerenciamento de notificações")
                        .contact(new Contact()
                                .name("Nome do Contato")
                                .email("contato@exemplo.com")
                                .url("https://exemplo.com")))
                // Aplica o esquema de segurança somente para endpoints sem a tag "Public"
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .tags(List.of(
                        new Tag().name("Public").description("Endpoints públicos sem autenticação")
                ));
    }
}