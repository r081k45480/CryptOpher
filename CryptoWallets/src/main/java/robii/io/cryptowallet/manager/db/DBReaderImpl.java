package robii.io.cryptowallet.manager.db;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import robii.io.cryptowallet.model.Buying;
import robii.io.cryptowallet.model.Coin;
import robii.io.cryptowallet.model.CoinDetailed;
import robii.io.cryptowallet.model.CoinImageUrl;
import robii.io.cryptowallet.model.comparators.CoinAmountComparator;

/**
 * Created by Robert Sabo on 10-Feb-18.
 */
@Component
public class DBReaderImpl implements DBReader {

    List<Coin> myCoins;

    @Autowired
    BuyingRepository buyingRepository;
    @Autowired
    CoinImageUrlRepository coinImageUrlRepository;
    
    @Override
    public List<Coin> getAllMyCoins() {

        List<Object[]> buyings = buyingRepository.getGrouped();
        myCoins = new ArrayList<>(buyings.size());

        for(Object[] b:buyings)
        {
            Coin c = makeCoinFromBuying(b);
            myCoins.add(c);
        }
        Collections.sort(myCoins, new CoinAmountComparator());
        return myCoins;
    }

    @Override
    public CoinDetailed getCoin(final String symbol) {
        
        List<Object[]> summed = buyingRepository.getGroupedBySymbol(symbol);
        Coin c = makeCoinFromBuying(summed.get(0));
        CoinDetailed cd = new CoinDetailed(c);
        
        CoinImageUrl ciu = coinImageUrlRepository.findById(symbol).get();
        cd.setName(ciu.getName());
        cd.setImageLink(ciu.getUrl());
        
        List<Buying> buyings = buyingRepository.findBySymbol(symbol);
        cd.setBuyings(buyings);

        return cd;
    }

    @Override
    public Double getSumInput() {
        if(myCoins == null)
            getAllMyCoins();
        Double summ = 0.0;
        for(Coin c : myCoins)
            summ+=c.getInput();
        return summ;
    }

    
	@Override
	public SortedMap<String, Coin> getAllCoins() {
		List<CoinImageUrl> alCoins=coinImageUrlRepository.findAll();
		
		SortedMap<String, Coin> map = new TreeMap<>();
		alCoins.stream().forEach(t -> map.put(
												t.getSymbol(), 
												new Coin(t.getSymbol(), t.getName(), t.getUrl())));
		
		return map;
	}
    
	@Override
	public void insertAll(Buying... bu){
		buyingRepository.saveAll(Arrays.stream(bu).collect(Collectors.toList()));
	}
	@Override
	public Map<String, CoinImageUrl> findBySymbol(List<String> symbols){
		List<CoinImageUrl> cils = coinImageUrlRepository.findAllById(symbols);
		Map<String,CoinImageUrl> map = cils.stream().collect(Collectors.toMap((CoinImageUrl t) -> t.getSymbol(),a -> a));
		return map;
	}
	
	private Coin makeCoinFromBuying(Object[] t){
    	Buying b = makeFromStrings(t);
        Coin c= new Coin();
        c.setSymbol(b.getSymbol());
        // in query selected price as amount
        c.setAmount(b.getAmount());
        c.setInput(b.getInput());
        return c;
    }
    private Buying makeFromStrings(Object[] t){
    	Buying b = new Buying();
		b.setSymbol(t[0]+"");
		b.setInput(Double.parseDouble(t[1]+""));
		Timestamp ts = Timestamp.valueOf(t[2] +"");
		b.setDate(new Date(ts.getTime()));
		b.setAmount(Double.parseDouble(t[3]+""));
		return b;
    }

}
