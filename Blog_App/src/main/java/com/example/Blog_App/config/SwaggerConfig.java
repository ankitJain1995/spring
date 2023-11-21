package com.example.Blog_App.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Alibou",
                        email = "contact@aliboucoding.com",
                        url = "https://aliboucoding.com/courses"
                ),
                description = "Open API Documentation For Blog Application",
                title = "OpenAPI Specification - Alibou",
                version = "1.0",
                license = @License(
                        name = "License Name",
                        url = "https://some-url.com"
                ),
                termsOfService = "Terms of Service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:1900"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://aliboucoding.com/courses"
                ),
        }

)
@SecurityScheme(
        name = "BearerAuth",
        description = "JWT Auth Description",
        scheme = "Bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
