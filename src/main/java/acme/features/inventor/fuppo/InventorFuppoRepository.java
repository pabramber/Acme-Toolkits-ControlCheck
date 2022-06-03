package acme.features.inventor.fuppo;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fuppo.Fuppo;
import acme.entities.inventions.Invention;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorFuppoRepository extends AbstractRepository {

	@Query("SELECT c FROM Fuppo c WHERE c.component.inventor.id = ?1")
	Collection<Fuppo> findFupposByInventorId(int id);

	@Query("SELECt c FROM Fuppo c WHERE c.id = ?1")
	Fuppo findOneFuppoById(int id);

	@Query("SELECT c FROM SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();

	@Query("SELECT i FROM Invention i WHERE i.code = ?1")
	Invention findOneInventionByCode(String code);

	@Query("SELECT COUNT(*) FROM Fuppo c WHERE c.component.id = ?1")
	int countByInventionId(int id);

}
