package robii.io.cryptoads.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import robii.io.cryptoads.model.Ad;
import robii.io.cryptoads.repository.AdRepository;

@Service
public class AdService {
	
	@Autowired
	AdRepository adRepository;
	
	
	@Transactional
	public boolean adDone(Ad ad){
		Optional<Ad> fromDb = adRepository.findById(ad.getId());
		if(!fromDb.isPresent() || fromDb.get().getAdDone().isPresent())
			return false;
		else{
			ad = fromDb.get();
			ad.setAdDone(new Date());
			adRepository.save(ad);
			return true;
		}
	}
}
