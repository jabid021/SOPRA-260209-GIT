package biblio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import biblio.model.Avis;
import biblio.model.Livre;

public interface IDAOAvis extends JpaRepository<Avis,Integer> {
	
	public List<Livre> findByLivre(Integer livre);
	
}
