package io.robii.cryptoads.model;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity

@NamedQueries({
 
	@NamedQuery(name="Ad.getBySymbolNotDone", 
				query="SELECT a FROM Ad a WHERE a.symbol=:symbol AND a.adDone is null ORDER by a.date DESC"),

	@NamedQuery(name="Ad.getAll", 
				query="SELECT a FROM Ad a ORDER by a.date DESC"),

	@NamedQuery(name="Ad.getAllNotDone", 
				query="SELECT a FROM Ad a WHERE a.adDone is null ORDER by a.date DESC")
})

public class Ad  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String username;
	private String symbol;
	private double amount;
	private boolean isBuying;
	private Date date;
	private Date adDone;

	public Ad() {
	}

	public Ad(int id, String username, String symbol, double amount, boolean isBuying, Date date) {
		this.id = id;
		this.username = username;
		this.symbol = symbol;
		this.amount = amount;
		this.isBuying = isBuying;
		this.date = date;
	}

	public Ad(int id, String username, String symbol, double amount, boolean isBuying, Date date, Optional<Date> adDone) {
		this.id = id;
		this.username = username;
		this.symbol = symbol;
		this.amount = amount;
		this.isBuying = isBuying;
		this.date = date;
		this.adDone =adDone.orElse(null);
	}
	
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean getIsBuying() {
		return this.isBuying;
	}

	public void setIsBuying(boolean isBuying) {
		this.isBuying = isBuying;
	}
	
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Optional<Date> getAdDone() {
		return this.adDone == null ?
					Optional.empty():
					Optional.of(this.adDone);
	}

	public void setAdDoneOptional(Optional<Date> adDone) {
		this.adDone = adDone.orElse(null);
	}
	public void setAdDone(Date adDone) {
		this.adDone = adDone;
	}

	@Override
	public String toString() {
		return "Ad [id=" + id + ", username=" + username + ", symbol=" + symbol + ", amount=" + amount + ", isBuying="
				+ isBuying + ", date=" + date + ", adDone=" + adDone + "]";
	}

	
}
