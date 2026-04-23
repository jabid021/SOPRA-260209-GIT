// MARTIN
package biblio.dao;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import biblio.model.Collection;

public interface IDAOCollection extends JpaRepository<Collection, Integer> 
{
    Optional<Collection> findByNom(String nom);
    boolean existsByNom(String nom);
}
