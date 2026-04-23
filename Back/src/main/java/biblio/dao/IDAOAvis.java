package biblio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import biblio.model.Avis;

public interface IDAOAvis extends JpaRepository<Avis,Integer> {
	
}
