package io.robii.cryptoffer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.net.MediaType;

import io.robii.cryptoffer.model.CryptOfferUser;
import io.robii.cryptoffer.repository.CryptOfferUserRepository;

@Controller
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	@Autowired
	CryptOfferUserRepository userRepository;
	
	@GetMapping()
	public ResponseEntity<List<CryptOfferUser>> getAllUsers(){
		List<CryptOfferUser> users = userRepository.findAll();
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<CryptOfferUser> getUser(@PathVariable ("username") String username){
		Optional<CryptOfferUser> userO = userRepository.findById(username);
		if(userO.isPresent())
			return ResponseEntity.ok().body(userO.get());
		else
			return ResponseEntity.notFound().build();
	}
	@PutMapping()
	public ResponseEntity<CryptOfferUser> putUser(@RequestBody CryptOfferUser user){
		CryptOfferUser newUser = userRepository.save(user);
		return ResponseEntity.ok().body(newUser);
	}
	@PostMapping()
	public ResponseEntity<CryptOfferUser> postUser(@RequestBody CryptOfferUser user){
		CryptOfferUser newUser = userRepository.save(user);
		return ResponseEntity.ok().body(newUser);
	}
	
}
