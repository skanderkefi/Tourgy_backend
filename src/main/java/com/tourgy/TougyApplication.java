package com.tourgy;

import org.springframework.boot.SpringApplication;  
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




import com.tourgy.repository.GuideRepo;
import com.tourgy.services.GuideService;

@SpringBootApplication
@ComponentScan(basePackages ={ "com.tourgy.*"})
@EnableJpaRepositories (basePackageClasses  = {GuideService.class,GuideRepo.class})
@EnableAutoConfiguration
public class TougyApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(TougyApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
			}
		};
	}
	 
}
