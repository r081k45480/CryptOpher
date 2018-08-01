package robii.io.cryptoeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CryptoEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoEurekaServerApplication.class, args);
	}
}
