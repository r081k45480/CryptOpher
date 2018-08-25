package io.robii.cryptoffer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.robii.cryptoads.model.Ad;
import io.robii.cryptoffer.manager.api.AdAPIManager;

@RestController
@RequestMapping("ads")
@CrossOrigin
public class AdsController {

	@Autowired
	AdAPIManager adAPIManager;
	
	@GetMapping()
	public List<Ad> adsForSybmol(@RequestParam(name="symbol",defaultValue="") String symbol){
		List<Ad> ads;
		if(symbol != null && !"".equals(symbol.trim()))
			ads = adAPIManager.adsNotDone(symbol);
		else
			ads = adAPIManager.adsNotDone();
		return  ads;
	}
	
	@PutMapping
	public Boolean putAd(@RequestBody Ad ad){
		Ad adret = adAPIManager.putAd(ad);
		return adret != null;
	}
	
	@PostMapping()
	public Boolean adDone(@RequestBody Ad ad){
		boolean ok = adAPIManager.adDone(ad);
		return ok;
	}
	
}
