package io.robii.cryptoads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.robii.cryptoads.model.Ad;
import io.robii.cryptoads.repository.AdRepository;
import io.robii.cryptoads.services.AdService;

@RestController
@RequestMapping("/ads")
public class AdController {

	@Autowired
	AdRepository adRepository;
	
	@Autowired
	AdService adService;
	
	@GetMapping()
	public List<Ad> getAll(){
		List<Ad> ads = adRepository.getAll();
		return (ads);
	}
	@GetMapping("/{symbol}")
	public List<Ad> getBySymbol(@PathVariable("symbol") String symbol){
		List<Ad> ads = adRepository.findBySymbol(symbol);
		return (ads);
	}
	
	@GetMapping("/notdone")
	public List<Ad> getAllNotDone(){
		List<Ad> ads = adRepository.getAllNotDone();
		return (ads);
	}
	@GetMapping("/notdone/{symbol}")
	public List<Ad> getAllNotDone(@PathVariable("symbol") String symbol){
		List<Ad> ads = adRepository.getBySymbolNotDone(symbol);
		return (ads);
	}
	@PutMapping()
	public Ad put(@RequestBody Ad ad){
		Ad ads = adRepository.save(ad);
		return (ads);
	}
	@PostMapping()
	public Ad post(@RequestBody Ad ad){
		Ad ads = adRepository.save(ad);
		return (ads);
	}
	@PostMapping("/addone")
	public Boolean postAdDone(@RequestBody Ad ad){
		boolean ok = adService.adDone(ad);
		return (ok);
	}
}
