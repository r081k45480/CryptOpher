package io.robii.cryptowallet.controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.robii.cryptowallet.manager.CoinManager;
import io.robii.cryptowallet.model.Buying;
import io.robii.cryptowallet.model.Coin;
import io.robii.cryptowallet.model.CoinDetailed;

@RestController
@RequestMapping("coins")
public class CoinsController{

	@Autowired
	CoinManager coinManager;
	
	@GetMapping("all")
	public List<Coin> getAllCoins() {
		List<Coin> myCoins= coinManager.getAllCoins().values().stream().collect(Collectors.toList());
		return (myCoins);
	}
	
	@GetMapping("{symbol}")
	public CoinDetailed getCoinDetail(@PathVariable("symbol") String symbol) {
		CoinDetailed det = coinManager.getDetailedCoin(symbol);
		return (det);
	}

	@GetMapping()
	public List<Coin> getMyCoins() {
		List<Coin> myCoins = coinManager.getMyCoins();

		return (myCoins);
	}

	@PutMapping()
	public Boolean addBuying(@RequestBody Buying buying) {
		try {
			coinManager.insertAll(buying);
			return (true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (false);
		}
	}
}