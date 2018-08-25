package io.robii.cryptoffer.manager.api;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.robii.cryptowallet.model.Buying;
import io.robii.cryptowallet.model.Coin;
import io.robii.cryptowallet.model.CoinDetailed;

@FeignClient("CryptoWallets")
@RibbonClient("CryptoWallets")
@RequestMapping("api/coins")
public interface WalletAPIManager {

	@GetMapping
	public List<Coin> coins();

	@GetMapping("{symbol}")
	public CoinDetailed coin(@PathVariable("symbol") String symbol);

	@GetMapping("all")
	public List<Coin> allcoins();

	@PutMapping
	public boolean put(Buying b);
}
