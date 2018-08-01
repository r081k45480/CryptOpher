package robii.io.cryptoads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import robii.io.cryptoads.model.Ad;
import robii.io.cryptoads.repository.AdRepository;
import robii.io.cryptoads.services.AdService;

@Controller
@RequestMapping("/ads")
public class AdController {

	@Autowired
	AdRepository adRepository;
	
	@Autowired
	AdService adService;
	
	@GetMapping()
	public ResponseEntity<List<Ad>> getAll(){
		List<Ad> ads = adRepository.getAll();
		return ResponseEntity.ok(ads);
	}
	@GetMapping("/{symbol}")
	public ResponseEntity<List<Ad>> getBySymbol(@PathVariable("symbol") String symbol){
		List<Ad> ads = adRepository.findBySymbol(symbol);
		return ResponseEntity.ok(ads);
	}
	
	@GetMapping("/notdone")
	public ResponseEntity<List<Ad>> getAllNotDone(){
		List<Ad> ads = adRepository.getAllNotDone();
		return ResponseEntity.ok(ads);
	}
	@GetMapping("/notdone/{symbol}")
	public ResponseEntity<List<Ad>> getAllNotDone(@PathVariable("symbol") String symbol){
		List<Ad> ads = adRepository.getBySymbolNotDone(symbol);
		return ResponseEntity.ok(ads);
	}
	@PutMapping()
	public ResponseEntity<Ad> put(@RequestBody Ad ad){
		Ad ads = adRepository.save(ad);
		return ResponseEntity.ok(ads);
	}
	@PostMapping()
	public ResponseEntity<Ad> post(@RequestBody Ad ad){
		Ad ads = adRepository.save(ad);
		return ResponseEntity.ok(ads);
	}
	@PostMapping("/addone")
	public ResponseEntity<Boolean> postAdDone(@RequestBody Ad ad){
		boolean ok = adService.adDone(ad);
		return ResponseEntity.ok(ok);
	}
}
