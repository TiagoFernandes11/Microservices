package com.udemy.accounts;

import com.udemy.accounts.dto.AccountsContactInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDTO.class})
@OpenAPIDefinition(info = @Info(
		title = "Accounts microservice REST API documentation",
		description = "EazyBank Accounts microservice REST API documentation",
		version = "v1",
		contact = @Contact(
				name = "Tiago Fernandes Ribeiro",
				email = "tiagofernandesribeiro@yahoo.com.br",
				url = "https://github.com/TiagoFernandes11"
		),
		license = @License(
				name = "Apache 2.0",
				url = "https://github.com/TiagoFernandes11"
		)
), externalDocs = @ExternalDocumentation(
		description = "EazyBank Accounts microservice REST API documentation",
		url = "https://github.com/TiagoFernandes11"
))
public class AccountsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}
}
