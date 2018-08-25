package io.robii.cryptoffer.manager.api;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("CryptoAdvertising")
@RibbonClient("CryptoAdvertising")
@RequestMapping("api")
public interface AdHBMngr {

	@GetMapping("adhi")
	public String hb();

}
