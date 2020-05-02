package com.realestate.re;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@SpringBootApplication
public class ReApplication {

	@Autowired
	private BootStrap bootStrap;

	public static void main(String[] args) {

		try {
			System.setProperty("spring.devtools.restart.enabled", "true");
			SpringApplication.run(ReApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void loader() {
		bootStrap.loader();
	}

}
