package com.github.losemy.swaggerdubbo.annotations;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@ComponentScan(basePackages = { "com.github.losemy.swaggerdubbo"})
public @interface EnableDubboSwagger {

}
