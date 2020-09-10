package com.myapp.crud.appgen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication
//@EnableJpaRepositories
public class CrudAppGenApplication {
	public static void main(String[] args) {
		SpringApplication.run(CrudAppGenApplication.class, args);
	}

}
