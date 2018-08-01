package robii.io.cryptowallet.manager.db;

import org.springframework.data.jpa.repository.JpaRepository;

import robii.io.cryptowallet.model.CoinImageUrl;

public interface CoinImageUrlRepository extends JpaRepository<CoinImageUrl, String>{

}
