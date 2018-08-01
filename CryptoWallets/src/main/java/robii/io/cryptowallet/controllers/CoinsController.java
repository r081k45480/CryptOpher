package robii.io.cryptowallet.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import robii.io.cryptowallet.manager.CoinManager;
import robii.io.cryptowallet.model.Buying;
import robii.io.cryptowallet.model.Coin;
import robii.io.cryptowallet.model.CoinDetailed;

@Controller
public class CoinsController{

	@Autowired
	CoinManager coinManager;
	
	@GetMapping("allcoins")
	public ResponseEntity<List<Coin>> getAllCoins() {
		List<Coin> myCoins= coinManager.getAllCoins().values().stream().collect(Collectors.toList());
		return ResponseEntity.ok(myCoins);
	}
	
	@GetMapping("mycoins/{symbol}")
	public ResponseEntity<CoinDetailed> getCoinDetail(@PathVariable("symbol") String symbol) {
		CoinDetailed det = coinManager.getDetailedCoin(symbol);
		return ResponseEntity.ok(det);
	}

	@GetMapping("mycoins")
	public ResponseEntity<List<Coin>> getMyCoins() {
		List<Coin> myCoins = coinManager.getMyCoins();

		return ResponseEntity.ok(myCoins);
	}

	@PutMapping("buying")
	public ResponseEntity<Boolean> addBuying(@RequestBody Buying buying) {
		try {
			coinManager.insertAll(buying);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.ok(false);
		}
	}
}