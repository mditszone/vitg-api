package com.vitg.config;

import java.time.LocalDate;


import java.util.Collections;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.google.common.collect.Sets;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	public static final String DEFAULT_INCLUDE_PATTERN = "/.*";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
			.apis(RequestHandlerSelectors.basePackage("com.vitg.controller")).paths(PathSelectors.any()).build()
			.produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
			.consumes(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
			.directModelSubstitute(LocalDate.class, String.class).directModelSubstitute(HttpStatus.class, String.class)
			.genericModelSubstitutes(ResponseEntity.class).apiInfo(apiInfo());
			//.securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.newArrayList(apiKey()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Vision IT Global", "Swagger for Vision IT Global.", "API 0.1",
			"Vision IT Global terms of service", new Contact("", "", ""), "License of API", "API license URL",
			Collections.emptyList());
	}
}
