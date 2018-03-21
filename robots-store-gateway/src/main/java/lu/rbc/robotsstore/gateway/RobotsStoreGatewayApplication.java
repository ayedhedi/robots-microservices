package lu.rbc.robotsstore.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import lu.rbc.robotsstore.gateway.configurations.RibbonConfiguration;

@EnableZuulProxy
@EnableEurekaClient
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
@SpringBootApplication
public class RobotsStoreGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RobotsStoreGatewayApplication.class, args);
	}
}
