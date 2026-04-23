package biblio.api;

import biblio.dao.IDAOLivre;
import biblio.model.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livre")
public class LivreRestController {

    @Autowired
    IDAOLivre daoLivre;

    @GetMapping("/{id}")
    public Livre findById(@PathVariable Integer id){
        return daoLivre.findById(id).orElse(null);
    }

    @GetMapping
    public List<Livre> findAll(){
        return daoLivre.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id)
    {
        daoLivre.deleteById(id);
    }

    @PostMapping
    public Livre add(@RequestBody Livre livre)
    {
        return daoLivre.save(livre);
    }

    @PutMapping("/{id}")
    public Livre update(@PathVariable Integer id,@RequestBody Livre livre)
    {
        livre.setId(id);
        daoLivre.save(livre);
        return livre;
    }
}
