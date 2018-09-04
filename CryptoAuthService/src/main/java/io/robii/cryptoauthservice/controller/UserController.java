package io.robii.cryptoauthservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.robii.cryptoauthservice.model.CORole;
import io.robii.cryptoauthservice.model.CryptOfferUser;
import io.robii.cryptoauthservice.repository.CryptOfferUserRepository;

@RestController
@RequestMapping("/users")
@CrossOrigin(methods={RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST}, origins="*", allowedHeaders="Content-Type, Authorization, Content-Length, X-Requested-With")
public class UserController {

	@Autowired
	CryptOfferUserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping()
	public List<CryptOfferUser> getAllUsers(){
		List<CryptOfferUser> users = userRepository.findAll();
		return users;
	}
	
	@GetMapping("/{username}")
	public CryptOfferUser getUser(@PathVariable ("username") String username){
		Optional<CryptOfferUser> userO = userRepository.findById(username);
		if(userO.isPresent())
			return userO.get();
		else
			return null;
	}
	@PostMapping
	public Boolean putUser(@RequestBody CryptOfferUser user){
		if(userRepository.findById(user.getUsername()).isPresent())
			return false;

		user.setRoles(Arrays.asList(CORole.USER));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return true;
	}
	
	
}
