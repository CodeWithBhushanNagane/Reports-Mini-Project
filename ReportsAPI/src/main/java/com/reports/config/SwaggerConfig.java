package com.reports.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Reports API").version("1.0").description("API documentation for ReportsAPIApp")
						.contact(new Contact().name("Bhushan Nagane").email("naganebhushan77@gmail.com")
								.url("https://yourdomain.com"))
						.license(new License().name("Apache 2.0")
								.url("http://www.apache.org/licenses/LICENSE-2.0.html")))
				.servers(List.of(new Server().url("http://localhost:8080").description("Local Server"),
						new Server().url("https://api.yourdomain.com").description("Production Server")))
				.components(new Components());
	}
}
