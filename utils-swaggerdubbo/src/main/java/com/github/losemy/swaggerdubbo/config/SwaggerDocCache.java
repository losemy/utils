package com.github.losemy.swaggerdubbo.config;

import org.springframework.stereotype.Component;

import io.swagger.models.Swagger;

@Component
public class SwaggerDocCache {

	private Swagger swagger;

	public Swagger getSwagger() {
		return swagger;
	}

	public void setSwagger(Swagger swagger) {
		this.swagger = swagger;
	}

}
