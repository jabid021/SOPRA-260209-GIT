package biblio.dao;

import java.util.List;
import java.util.Optional;

import biblio.model.Editeur;
import org.springframework.data.jpa.repository.JpaRepository;

import biblio.model.Avis;
import biblio.model.Livre;

public interface IDAOAvis extends JpaRepository<Avis,Integer> {
	
	public List<Avis> findByLivreId(Integer livreId);
    boolean existsById(Integer id);
}
