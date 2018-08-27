package io.robii.cryptoffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import io.robii.cryptoffer.model.CORole;
import io.robii.cryptoffer.model.CryptOfferUser;
import io.robii.cryptoffer.model.UserDetailsAdapter;
import io.robii.cryptoffer.repository.CryptOfferUserRepository;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
public class CryptoOfferApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoOfferApplication.class, args);
	}
	
}
