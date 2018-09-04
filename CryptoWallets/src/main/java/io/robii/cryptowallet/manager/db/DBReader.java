package io.robii.cryptowallet.manager.db;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.springframework.stereotype.Service;

import io.robii.cryptowallet.model.Buying;
import io.robii.cryptowallet.model.Coin;
import io.robii.cryptowallet.model.CoinDetailed;
import io.robii.cryptowallet.model.CoinImageUrl;

@Service
public interface DBReader {

	// fill the coin with data from database:
	// group by symbol and get sums
	
	
	// get all coins filled with amount and input
	List<Coin> getAllMyCoins(String username);
	
	// gett coin filled with amount and input
	CoinDetailed getCoin(String symbol, String username);
	
	Double getSumInput(String username);

	SortedMap<String, Coin> getAllCoins();
	
	void insertAll(Buying... bu);
	
	Map<String, CoinImageUrl> findBySymbol(List<String> symbols);
}
