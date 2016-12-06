package com.sysu.zigbee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import com.sysu.zigbee.domain.StorageProperties;
import com.sysu.zigbee.service.Iservice.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

	public static void main(String[] args) {

	        SpringApplication.run(Application.class, args);
	    }
	
	@Bean
	CommandLineRunner init(final StorageService storageService) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
			    storageService.deleteAll();
			    storageService.init();
			}
		};
	}
}
