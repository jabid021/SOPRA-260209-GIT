package biblio.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import biblio.model.Editeur;

public interface IDAOEditeur extends JpaRepository<Editeur, Integer> {

    Optional<Editeur> findByNom(String nom);
    boolean existsByNom(String nom);
}