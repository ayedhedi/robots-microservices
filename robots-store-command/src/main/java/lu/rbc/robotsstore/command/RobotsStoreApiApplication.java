package lu.rbc.robotsstore.command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RobotsStoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RobotsStoreApiApplication.class, args);
	}
}
