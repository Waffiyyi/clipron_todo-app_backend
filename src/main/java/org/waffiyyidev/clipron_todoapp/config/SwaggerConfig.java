package org.waffiyyidev.clipron_todoapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(info = @Info(title = "Todo Application", version = "1.0.0",
                                description = "Todo Application using RESTful API",
                                termsOfService = "#",
                                contact = @Contact(name = "Mr Waffiyyi",
                                                   email = "fasholawafiyyi@gmail.com",
                                                   url = "https://waffiyyi-fashola.vercel.app"),
                                license = @License(name = "licence", url = "#")

),
                   servers = {
                     @Server(
                       url = "https://coastal-turkey-donezo-todo-app-69b1821d.koyeb.app",
                       description = "Production Server"),

                     @Server(url = "http://localhost:8065",
                             description = "Local Server")
                   },
                   security = @SecurityRequirement(name = "bearerAuth")

)
@SecurityScheme(
  name = "bearerAuth",
  description = "JWT Auth",
  scheme = "bearer",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}