package io.robii.cryptowallet.manager.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.robii.cryptowallet.model.Buying;

public interface BuyingRepository extends JpaRepository<Buying, Long>{

	public List<Buying> findBySymbolAndUsername(String symbol, String username);

	@Query
	public List<Object[]> getGrouped(@Param("username") String username);

	@Query
	public List<Object[]> getGroupedBySymbol(@Param("symbol") String symbol,@Param("username") String username);
}
