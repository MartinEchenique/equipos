package com.echenique.equipos.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@SuppressWarnings("unused")
public class SwaggerConfiguration {

    private static final String DOCUMENTATION_DESCRIPTION = "API Documentation";

    @Value("${apiinfo.title}")
    private String title;

    @Value("${apiinfo.version}")
    private String version;

    @Value("${apiinfo.description}")
    private String description;

    @Value("${apiinfo.nameContact}")
    private String contactName;

    @Value("${apiinfo.mailContact}")
    private String contactEmail;

    @Value("${swag.url.docs}")
    private String docsUrl;

    @Value("${swag.url.terms}")
    private String termsUrl;

    @Bean
    public OpenAPI openAPI() {
        final var contact = new Contact()
                .name(contactName)
                .email(contactEmail);

        final var info = new Info()
                .title(title)
                .version(version)
                .description(description)
                .termsOfService(termsUrl)
                .contact(contact);

        final var externalDocumentation = new ExternalDocumentation()
                .url(docsUrl)
                .description(DOCUMENTATION_DESCRIPTION);

        return new OpenAPI()
                .info(info)
                .externalDocs(externalDocumentation);
    }
}