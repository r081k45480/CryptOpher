package io.robii.cryptowallet.manager.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.robii.cryptowallet.model.Buying;

public interface BuyingRepository extends JpaRepository<Buying, Long>{

	public List<Buying> findBySymbol(String symbol);

	@Query
	public List<Object[]> getGrouped();

	@Query
	public List<Object[]> getGroupedBySymbol(@Param("symbol") String symbol);
}
