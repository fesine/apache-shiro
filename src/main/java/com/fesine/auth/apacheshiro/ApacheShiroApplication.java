package com.fesine.auth.apacheshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@MapperScan(basePackages = {"com.fesine.auth.apacheshiro.mapper"})
public class ApacheShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApacheShiroApplication.class, args);
	}
}
