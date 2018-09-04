package io.robii.cryptowallet.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.SortedMap;

import org.springframework.stereotype.Service;

import io.robii.cryptowallet.manager.api.RESTReader;
import io.robii.cryptowallet.manager.db.DBReader;
import io.robii.cryptowallet.model.Buying;
import io.robii.cryptowallet.model.Coin;
import io.robii.cryptowallet.model.CoinDetailed;

/**
 * Created by Robert Sabo on 04-Feb-18.
 */
@Service
public interface CoinManager {

	public RESTReader restReader();
	public DBReader dbReader();
	///DetailedCoinView
	///get all to show on detail veiw
    public CoinDetailed getDetailedCoin(String symbol, String username);
    
    ///AddNewTrans
    // on adding new gets the historical price
    public double getHistorycalPrice(String symbol, Date dateTime);
    
    // on adding new get all to show 
    public SortedMap<String, Coin> getAllCoins();
    
    ///ListOfMyCurrencies
    //
    public ArrayList<Coin> getMyCoins(String username);
    
    //For summer on list
    public Double getSumPercentualProfit(String useranme);
    public Double getSumProfit(String useranme);
    public Double getSumInput(String useranme);
    public Double getSumCurrentCapital(String useranme);
    
	public void insertAll(Buying... buy) throws Exception;
}
