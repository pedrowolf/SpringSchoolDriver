package com.school.driver.application;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"${beans.base.package}"})
@EnableJpaRepositories(basePackages = {"${repositories.base.package}"})
@EntityScan(basePackages = {"${entities.base.package}"})
@EnableFeignClients(basePackages = {"${feign.client.base.package}"})
public class SchoolDriverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolDriverApplication.class, args);
	}

}
