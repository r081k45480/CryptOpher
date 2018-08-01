package io.robii.cryptowallet.manager.db;

import org.springframework.data.jpa.repository.JpaRepository;

import io.robii.cryptowallet.model.CoinImageUrl;

public interface CoinImageUrlRepository extends JpaRepository<CoinImageUrl, String>{

}
