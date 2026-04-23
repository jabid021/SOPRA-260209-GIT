//Audric

package biblio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import biblio.model.Auteur;


public interface IDAOAuteur extends JpaRepository<Auteur,Integer>{
    
}
