package io.robii.cryptoauthservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping
@CrossOrigin
public class HBController {

	@Autowired
	Environment environment;
	
	//@PreAuthorize("#auth2.hasScope('user')")
	@GetMapping("public")
	public ResponseEntity<String> hello(){
		String map =  "{ \"msg\":\"Hello from CryptOffer public\"}";
		return ResponseEntity.ok().body(map);
	}
	
	@GetMapping("private")
	public ResponseEntity<String> privateE(){
		String map =  "{ \"msg\":\"Hello from CryptOffer pirvate user\"}";
		return ResponseEntity.ok().body(map);
	}
	
	@GetMapping("admin")
	public ResponseEntity<String> admin(){
		String map =  "{ \"msg\":\"Hello from CryptOffer admin\"}";
		return ResponseEntity.ok().body(map);
	}
}
