package biblio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import biblio.model.Editeur;

public interface IDAOEditeur extends JpaRepository<Editeur, Long> {
}