package io.robii.cryptowallet.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jdk.nashorn.internal.ir.annotations.Ignore;


@Entity
@Table(name = "buying")
@NamedQueries({
	@NamedQuery(name="Buying.getAllBySymbol", 
			query="SELECT b FROM Buying b WHERE b.symbol = :symbol ORDER BY b.date DESC"),
	@NamedQuery(name="Buying.getGrouped", 
	query="SELECT b.symbol, sum(b.input) as input, max(b.date) as date, sum(b.amount) as amount " +
            "FROM Buying b GROUP BY b.symbol HAVING sum(b.amount) > 0"),
	@NamedQuery(name="Buying.getGroupedBySymbol", 
	query="SELECT b.symbol, sum(b.input) as input, max(b.date) as date, sum(b.amount) as amount " +
            "FROM Buying b GROUP BY b.symbol HAVING b.symbol = :symbol AND sum(b.amount) > 0"),

})
public class Buying implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;

	protected String symbol;

	protected Date date;

	protected Double input;
	protected Double price;
	protected Double amount;
	
	@JsonIgnore
	@Transient
	protected Coin coin;
	
	public Double getAmount(){
		//manual added amount
		if(amount != null)
			return amount;

		if(input == null) input = 0.0;
		if(price == null) return 0.0;
		return input/price;
	}

	public void setAmount(double amount){
		this.amount = amount;
	}
	
	public Coin getCoin() {
		return coin;
	}
	public void setCoin(Coin coin) {
		this.coin = coin;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getInput() {
		return input;
	}
	public void setInput(Double in) {
		this.input = in;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Buying [id=" + id + ", symbol=" + symbol + ", date=" + date + ", input=" + input + ", price=" + price
				+ ", amount=" + amount + ", coin=" + coin + "]";
	}
	
	@JsonIgnore
	public Double getCurrentCapital(){
		return getAmount() * getCoin().getCurrentPrice();
	}

	@JsonIgnore
	public Double getProfit(){
		return getCurrentCapital() - input;
	}

	@JsonIgnore
	public double getPercentualProfit(){
    	return (getProfit()/input) * 100;
    }
	
	@JsonIgnore
	public void setAndCalculateAmount(double amount){
		if(price == null || price == 0.0) return;
		input = amount*price;
		setAmount(amount);
	}
}
