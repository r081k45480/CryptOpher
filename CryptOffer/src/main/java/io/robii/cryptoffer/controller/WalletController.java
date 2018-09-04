package io.robii.cryptoffer.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.robii.cryptoffer.manager.api.WalletAPIManager;
import io.robii.cryptowallet.model.Buying;
import io.robii.cryptowallet.model.Coin;
import io.robii.cryptowallet.model.CoinDetailed;

@RestController
@RequestMapping("coins")
@CrossOrigin(allowedHeaders="*")
public class WalletController {

	String currentUser = "jaki";
	@Autowired
	WalletAPIManager walletAPIManager;
	
	@GetMapping()
	public List<Coin> coins(Principal principal){
		String currentUser = principal.getName();
		List<Coin> coins = walletAPIManager.coins(currentUser);
		return coins;
	}
	
	@GetMapping("{symbol}")
	public CoinDetailed coin(@PathVariable("symbol") String symbol, Principal principal){
		String currentUser = principal.getName();
		CoinDetailed det = walletAPIManager.coin(symbol, currentUser);
		return det;
	}
	
	@GetMapping("all")
	public List<Coin> allcoins(){
		List<Coin> allCoins = walletAPIManager.allcoins();
		return allCoins;
	}
	
	@PutMapping
	public Boolean put(@RequestBody Buying b, Principal principal){
		String currentUser = principal.getName();
		b.setUsername(currentUser);
		boolean ok = walletAPIManager.put(b);
		return ok;
	}
}
