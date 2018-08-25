package io.robii.cryptoffer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.robii.cryptoads.model.Ad;
import io.robii.cryptoffer.manager.BuyingManager;
import io.robii.cryptoffer.manager.api.AdAPIManager;
import io.robii.cryptoffer.manager.api.WalletAPIManager;
import io.robii.cryptowallet.model.Buying;

@RestController
@CrossOrigin
public class AdsAndWalletController {

	
	@Autowired
	AdAPIManager adAPIManager;
	
	@Autowired
	WalletAPIManager walletAPIManager;
	
	@Autowired
	BuyingManager buyingManager;
	
	@PostMapping("adsToWallet")
	@Transactional
	public Boolean asToWallet(@RequestBody Ad ad){
		try{
			/// aaaaahaaaaaa
			Buying buying = buyingManager.makeFromAd(ad);
			
			boolean walletOk = walletAPIManager.put(buying);
			boolean adOk = adAPIManager.adDone(ad);
		
			boolean ok = walletOk && adOk;
			
			if(!ok)
				;//rollBack
			
			return ok;
		} catch (Exception ex) {
			// rollBack
			return false;
		}
	}
}
