package robii.io.cryptowallet.manager.db;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.springframework.stereotype.Service;

import robii.io.cryptowallet.model.Buying;
import robii.io.cryptowallet.model.Coin;
import robii.io.cryptowallet.model.CoinDetailed;
import robii.io.cryptowallet.model.CoinImageUrl;

@Service
public interface DBReader {

	// fill the coin with data from database:
	// group by symbol and get sums
	
	
	// get all coins filled with amount and input
	List<Coin> getAllMyCoins();
	
	// gett coin filled with amount and input
	CoinDetailed getCoin(String symbol);
	
	Double getSumInput();

	SortedMap<String, Coin> getAllCoins();
	
	void insertAll(Buying... bu);
	
	Map<String, CoinImageUrl> findBySymbol(List<String> symbols);
}
