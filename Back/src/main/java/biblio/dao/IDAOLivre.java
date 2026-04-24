package biblio.dao;

import biblio.model.Editeur;
import biblio.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDAOLivre extends JpaRepository<Livre, Integer> {
    Optional<Livre> findByTitre(String titre);
    boolean existsByTitre(String titre);
}
