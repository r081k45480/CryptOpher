package io.robii.cryptoffer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.robii.cryptoffer.manager.api.AdHBMngr;
import io.robii.cryptoffer.manager.api.WalletHBMngr;

@Controller
@RequestMapping
@CrossOrigin
public class HBController {

	@Autowired
	Environment environment;
	
	@Autowired
	AdHBMngr ad;
	@Autowired
	WalletHBMngr wal;
	
	@GetMapping()
	public ResponseEntity<String> getAllUsers(@RequestParam(name="symbol",defaultValue="0") int times){
		String randomServerPort =environment.getProperty("local.server.port"); 
		//String map =  "<h1>["+randomServerPort+"]Hello from CryptOffer["+randomServerPort+"]</h1>";
		String map =  "{ \"msg\":\""+randomServerPort+"]Hello from CryptOffer["+randomServerPort+"\"}";
		
		for(int i = 0; i < times; i++){
			map += ad.hb() ;
			map += wal.hb() ;
		}
		return ResponseEntity.ok().body(map);
	}
		
}
