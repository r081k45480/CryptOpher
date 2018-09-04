package io.robii.cryptoffer.controller;

import java.security.Principal;

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
	// this can be call only for Ad that posted buy another user
	public Boolean asToWallet(@RequestBody Ad ad, Principal principal){
		try{
			String currentUser = principal.getName();
			/// aaaaahaaaaaa
			Buying myBuying = buyingManager.makeMeBuyingFromAd(ad, currentUser);
			Buying buying = buyingManager.makeThemBuyingFromAd(ad);
			boolean walletOk = walletAPIManager.put(myBuying,buying);
			boolean adOk = adAPIManager.adDone(ad);
			ad.setDoneWithUser(currentUser);
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
