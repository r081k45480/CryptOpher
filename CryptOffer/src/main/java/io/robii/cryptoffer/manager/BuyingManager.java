package io.robii.cryptoffer.manager;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.robii.cryptoads.model.Ad;
import io.robii.cryptowallet.model.Buying;

@Service
public class BuyingManager {

	public Buying makeFromAd(Ad ad){
		Buying b = new Buying();
		double amount = ad.getIsBuying() ? -ad.getAmount() : ad.getAmount();
		b.setAmount(amount);
		b.setSymbol(ad.getSymbol());
		b.setDate(new Date());
		b.setPrice(0.0);// will be calculated in WalletService
		return b;
	}
}
