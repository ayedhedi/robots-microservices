package lu.rbc.robotsstore.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RobotsStoreApiApplication extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RobotsStoreApiApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(RobotsStoreApiApplication.class, args);
	}
}
