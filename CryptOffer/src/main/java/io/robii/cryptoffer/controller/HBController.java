package io.robii.cryptoffer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.robii.cryptoffer.manager.api.AdHBMngr;
import io.robii.cryptoffer.manager.api.WalletHBMngr;

@RestController
@RequestMapping
@CrossOrigin
public class HBController {

	@Autowired
	Environment environment;
	
	@Autowired
	AdHBMngr ad;
	@Autowired
	WalletHBMngr wal;
	
	//@PreAuthorize("#auth2.hasScope('user')")
	@GetMapping("public/more")
	public ResponseEntity<String> hello(@RequestParam(name="symbol",defaultValue="0") int times){
		String randomServerPort =environment.getProperty("local.server.port"); 
		//String map =  "<h1>["+randomServerPort+"]Hello from CryptOffer["+randomServerPort+"]</h1>";
		String map =  "{ \"msg\":\""+randomServerPort+"]Hello from CryptOffer["+randomServerPort+"\"}";
		
		for(int i = 0; i < times; i++){
			map += ad.hb() ;
			map += wal.hb() ;
		}
		return ResponseEntity.ok().body(map);
	}
	

	@GetMapping("public")
	public String hello(){
		return   "{ \"msg\":\"Hello from CryptOffer\"}";
	}
		
}
