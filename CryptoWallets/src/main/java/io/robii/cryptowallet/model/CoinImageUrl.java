package io.robii.cryptowallet.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by Robert Sabo on 20-Feb-18.
 */

@Entity
@Table(name = "coin_image_url")
@NamedQueries({
	@NamedQuery(name="CoinImageUrl.count", 
			query="SELECT COUNT(c) FROM CoinImageUrl c"),
	@NamedQuery(name="CoinImageUrl.getAll", 
			query="SELECT c FROM CoinImageUrl c"),
	@NamedQuery(name="CoinImageUrl.getById", 
			query="SELECT c FROM CoinImageUrl c WHERE c.symbol in :ids")
	
})
public class CoinImageUrl implements Serializable{
	
	public CoinImageUrl(){}
	
    public CoinImageUrl(String symbol, String url){
        this.symbol = symbol;
        this.url = url;
    }
    
    public CoinImageUrl(Coin coin){
    	this.symbol = coin.symbol;
    	this.name = coin.name;
    	this.url = coin.getImageLink();
    }
    
    @Id
    private String symbol;
    private String name;
    private String url;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "CoinImageUrl [symbol=" + symbol + ", name=" + name + ", url=" + url + "]";
	}
    
    
}
