package io.robii.cryptoffer.manager.api;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("CryptoWallets")
@RibbonClient("CryptoWallets")
@RequestMapping("api")
public interface WalletHBMngr {

	@GetMapping("wallethi")
	public String hb();

}
