package biblio.api;

import biblio.dao.IDAOAvis;
import biblio.model.Avis;
import biblio.model.Livre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avis")
public class AvisRestController {
    
    @Autowired
    IDAOAvis daoAvis;

    @GetMapping("/{id}")
    public Avis findById(@PathVariable Integer id){
        return daoAvis.findById(id).orElse(null);
    }

    @GetMapping("/livre/{titre}")
    public List<Livre> findByLivre(@PathVariable String titre){
        return daoAvis.findByLivre(titre);
    }
    */
   
    @GetMapping
    public List<Avis> findAll(){
        return daoAvis.findAll();
    }

     @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id)
    {
        daoAvis.deleteById(id);
    }

    @PostMapping
    public Avis add(@RequestBody Avis avis)
    {
        return daoAvis.save(avis);
    }

    @PutMapping("/{id}")
    public Avis update(@PathVariable Integer id, @RequestBody Avis avis)
    {
        avis.setId(id);
        daoAvis.save(avis);
        return avis;
    }
}