package biblio.restcontroller;

import biblio.dao.IDAOLivre;
import biblio.model.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/livre")
public class LivreRestController {

    @Autowired
    IDAOLivre daoLivre;

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id){
        return id.toString();
    }
}
