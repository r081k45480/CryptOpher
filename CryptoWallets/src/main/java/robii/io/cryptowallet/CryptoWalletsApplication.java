package robii.io.cryptowallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CryptoWalletsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoWalletsApplication.class, args);
	}
}
