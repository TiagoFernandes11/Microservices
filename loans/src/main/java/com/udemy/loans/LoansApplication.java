package com.udemy.loans;

import com.udemy.loans.dto.LoansContactInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {LoansContactInfoDTO.class})
@OpenAPIDefinition(info = @Info(
		title = "Loans microservice REST API documentation",
		description = "EazyBank Loans microservice REST API documentation",
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
		description = "EazyBank Loans microservice REST API documentation",
		url = "https://github.com/TiagoFernandes11"
))
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
