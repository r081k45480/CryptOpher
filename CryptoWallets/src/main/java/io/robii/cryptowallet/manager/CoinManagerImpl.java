package io.robii.cryptowallet.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.robii.cryptowallet.manager.api.RESTReader;
import io.robii.cryptowallet.manager.api.RESTReaderImpl;
import io.robii.cryptowallet.manager.db.DBReader;
import io.robii.cryptowallet.manager.db.DBReaderImpl;
import io.robii.cryptowallet.model.Buying;
import io.robii.cryptowallet.model.Coin;
import io.robii.cryptowallet.model.CoinDetailed;
import io.robii.cryptowallet.model.CoinImageUrl;
import io.robii.cryptowallet.model.comparators.CoinAmountComparator;

/**
 * Created by Robert Sabo on 04-Feb-18.
 */
@Component
public class CoinManagerImpl implements CoinManager{

	private static final boolean READ_ALL = false;

	@Autowired
	RESTReader restReader;
	
	@Autowired
	DBReader dbReader;

	public RESTReader restReader(){return restReader;}
	public DBReader dbReader(){return dbReader;}
	
	public CoinManagerImpl(){
		this.dbReader = new DBReaderImpl();
		restReader = new RESTReaderImpl();

			
	}
    SortedMap<String, Coin> allCoins;
    Map<String, Coin> myCoinsMap;
    ArrayList<Coin> myCoins;

	@Override
    public CoinDetailed getDetailedCoin(final String symbol, final String username) {
    	Double price = restReader.getCurrentPrice(symbol);
    			
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -10);
		Date d =  c.getTime();
		Double priceBefore7Days = restReader.getHistoricalPrice(symbol, d);

    	CoinDetailed coin = dbReader.getCoin(symbol, username);

    	if(READ_ALL){
    		SortedMap<Date, Double> history = restReader.getPriceForLast10Days(symbol);
			coin.setHistori(history);
    	}
    	
		coin.setPriceBefore7days(priceBefore7Days);
		coin.setCurrentPrice(price);
		coin.getBuyings().forEach(t -> t.setCoin(coin));
		
		return coin;
    }
  

    @Override
    public double getHistorycalPrice(String symbol, Date dateTime) {
        return restReader.getHistoricalPrice(symbol, dateTime);
    }

    @Override
    public SortedMap<String, Coin> getAllCoins() {
    	if(allCoins==null)
    		allCoins = dbReader.getAllCoins();

		return allCoins;

    }

	private void getMyCoinsWithoutPrices(String username){
		this.myCoinsMap = new HashMap<>();
		this.myCoins = new ArrayList<>();
		final List<Coin> myCoins = dbReader.getAllMyCoins(username);

		for (Coin v : myCoins) {
			this.myCoinsMap.put(v.getSymbol(), v);
			this.myCoins.add(v);
		}
	}

	@Override
    public ArrayList<Coin> getMyCoins(final String username) {
		//if(this.myCoins == null)
		getMyCoinsWithoutPrices(username);

    	List<String> myCoinsSymbols = new ArrayList<>(this.myCoinsMap.keySet());

    	Map<String, Double> prices = restReader.getPricesFor(myCoinsSymbols);
		Map<String, CoinImageUrl> coinDets = dbReader.findBySymbol(myCoinsSymbols);
    	for (Coin v : myCoins){
    		v.setCurrentPrice(prices.get(v.getSymbol()));
    		CoinImageUrl ciu = coinDets.get(v.getSymbol());
    		v.setName(ciu.getName());
    		v.setImageLink(ciu.getUrl());
    	}
    	Collections.sort(myCoins, new CoinAmountComparator());
        return this.myCoins;
    }
    
	@Override
    public Double getSumPercentualProfit(String username){
    	return (getSumProfit(username)/getSumInput(username))*100;
    }
    
	@Override
    public Double getSumProfit(String username){
    	return getSumCurrentCapital(username) - getSumInput(username);
    }
    
    @Override
    public Double getSumInput(String username){
    	return dbReader.getSumInput(username);
    }
    
	@Override
    public Double getSumCurrentCapital(String username){
		
    	//if(myCoins == null) commented... no cachin
    		getMyCoinsWithoutPrices(username);

    	Double sum = 0.0;
    	for (Coin t : myCoins){
    		sum += t.getCurrentCapital();
		}

    	return sum;
    }
	
	@Override
	public void insertAll(Buying... buy) throws Exception {
		if(buy == null || buy.length == 0) return;
		String username = buy[0].getUsername();
		getMyCoinsWithoutPrices(username);
		checkSell(buy);
		
		Arrays.asList(buy).stream()
				.filter(t->t.getPrice()==0)
				.forEach(t->{
			t.setPrice(restReader.getCurrentPrice(t.getSymbol()));
			t.setAndCalculateAmount(t.getAmount());
		});
		dbReader().insertAll(buy);
	}
	private void checkSell(Buying... buys) throws Exception {
		for(Buying t:buys){
			// chekc only sells
			if(t.getAmount() >= 0) continue;
			
			//check does we have it
			if(!myCoinsMap.containsKey(t.getSymbol())){
				throw new Exception(delimiter()+"You do not have any "+t.getSymbol()+"!"+delimiter());
			}
			Coin c = myCoinsMap.get(t.getSymbol());
			double iHave = c.getAmount();
			double wantToSell = t.getAmount() * -1;
			if(iHave< wantToSell )
				throw new Exception(delimiter()+"You have only "+iHave+" "+t.getSymbol()+", buy you want to sell "+wantToSell+delimiter());
			}
	}
	
	String delimiter(){
		return "\n\n+++++++++++++++++++++++++++++++++++++\n\n+++++++++++++++++++++++++++++++++++\n\n";
	}
}


