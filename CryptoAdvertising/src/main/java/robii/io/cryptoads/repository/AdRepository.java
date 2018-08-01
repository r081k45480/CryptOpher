package robii.io.cryptoads.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import robii.io.cryptoads.model.Ad;
/*
 * @NamedQueries({
 
	@NamedQuery(name="Ad.getBySymbol", 
				query="SELECT a FROM Ad a WHERE a.symbol=:symbol AND a.adDone is null ORDER by a.date DESC"),

	@NamedQuery(name="Ad.getAll", 
				query="SELECT a FROM Ad a ORDER by a.date DESC"),

	@NamedQuery(name="Ad.getNotDone", 
				query="SELECT a FROM Ad a WHERE a.adDone is null ORDER by a.date DESC")
})
*/
public interface AdRepository extends JpaRepository<Ad, Integer>{
	//@Query(name="SELECT a FROM Ad a WHERE a.symbol=:symbol AND a.adDone is null ORDER by a.date DESC")
	@Query
	public List<Ad> getBySymbolNotDone(@Param("symbol") String symbol);
	
	//@Query(name="SELECT a FROM Ad a WHERE a.adDone is null ORDER by a.date DESC")
	@Query
	public List<Ad> getAllNotDone();
	
	@Query
	public List<Ad> getAll();
	
	public List<Ad> findBySymbol(String symbol);
}
