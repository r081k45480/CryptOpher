package io.robii.cryptowallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class HBController {
	@Autowired
	Environment environment;
	
	@GetMapping(value={"","wallethi"})
	public String getAllUsers(){
		String randomServerPort =environment.getProperty("local.server.port"); 
		String map =  "<h1>Hello from CryptoWallets["+randomServerPort+"]</h1>";
		return (map);
	}
		
}
