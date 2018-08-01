package robii.io.cryptowallet.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Robert Sabo on 04-Feb-18.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Coin", propOrder = {
    "name",
    "symbol",
    "amount",
    "input"
})
public class Coin implements Comparable<Coin>, Serializable{
    protected String symbol;
    protected String name;

    public Coin(){}

    public Coin(String symbol, String name, String imageUrl) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.imageUrl = imageUrl;
	}



	//read from API
    protected double currentPrice;
    //amount is sum of amounts by symbol
    protected double amount;
    //input is sum of inputs by symbol
    protected double input;

    private String imageUrl;

    public void setImageLink(final String url){
        imageUrl = url;
    }

    public String getImageLink(){ return imageUrl; }

    public double getCurrentCapital(){
    	return amount * currentPrice;
    }
    
    public double getPercentualProfit(){
    	return (getProfit()/input) * 100;
    }
    
    public double getProfit(){
    	return (getCurrentCapital() - input);
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setInput(double input) {
        this.input = input;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName(){
        return name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getAmount() {
        return amount;
    }

    public double getInput() {
        return input;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coin coin = (Coin) o;

        return symbol != null ? symbol.equals(coin.symbol) : coin.symbol == null;
    }

    @Override
    public int hashCode() {
        return symbol != null ? symbol.hashCode() : 0;
    }

    @Override
    public int compareTo(Coin coin) {
        return symbol.compareTo(coin.symbol);
    }

	@Override
	public String toString() {
		return "Coin [symbol=" + symbol + ", name=" + name + ", currentPrice=" + currentPrice + ", amount=" + amount
				+ ", input=" + input + ", imageUrl=" + imageUrl + "]";
	}
    
    
}