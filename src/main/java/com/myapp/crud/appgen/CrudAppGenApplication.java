package com.myapp.crud.appgen;

import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication
//@EnableJpaRepositories
public class CrudAppGenApplication {
	private static Log log = LogFactory.getLog(CrudAppGenApplication.class);

	static {
		log.info("\n\n\n"
				+ "****************************************\n"
				+ "** CrudAppGenApplication starting... **\n"
				+ "****************************************\n"
				+ "** Deployment date: " + Calendar.getInstance().getTime()+"\n\n");
	}

	@Bean
	public String profilesActive(@Value("${profiles.active}") String profilesActive) {
		log.info("\n\n\n" + "***************************************************\n"
				+ "** CrudAppGenApplication PROFILESACTIVE ==>>" + profilesActive + "\n"
				+ "***************************************************\n\n\n");
		return profilesActive;
	}

	public static void main(String[] args) {
		SpringApplication.run(CrudAppGenApplication.class, args);
	}

}
