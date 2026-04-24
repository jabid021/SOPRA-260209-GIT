// MARTIN
package biblio.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import biblio.model.Collection;

public interface IDAOCollection extends JpaRepository<Collection, Integer> 
{
    boolean existsByNom(String nom);
}
