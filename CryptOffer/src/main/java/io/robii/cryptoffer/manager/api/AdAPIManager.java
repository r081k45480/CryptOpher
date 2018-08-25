package io.robii.cryptoffer.manager.api;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.robii.cryptoads.model.Ad;

@FeignClient("CryptoAdvertising")
@RibbonClient("CryptoAdvertising")
@RequestMapping("api/ads")
public interface AdAPIManager {

	@GetMapping
	public List<Ad> ads();

	@GetMapping("{symbol}")
	public List<Ad> ads(@PathVariable("symbol") String symbol);

	@GetMapping("notdone")
	public List<Ad> adsNotDone();

	@GetMapping("notdone/{symbol}")
	public List<Ad> adsNotDone(@PathVariable("symbol") String symbol);

	@PutMapping
	public Ad putAd(Ad ad);

	@PostMapping
	public Ad postAd(Ad ad);

	@PostMapping("/addone")
	public boolean adDone(Ad ad);
}
