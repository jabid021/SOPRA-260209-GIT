package biblio.dao;

import biblio.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDAOLivre extends JpaRepository<Livre, Integer> {
}
