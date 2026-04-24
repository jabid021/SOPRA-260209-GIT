package biblio.api;

import biblio.dao.IDAOLivre;
import biblio.model.Livre;
import biblio.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livre")
@CrossOrigin(origins = "http://localhost:4200")
public class LivreRestController {

    @Autowired
    private LivreService livreService;

    @GetMapping("/{id}")
    public Livre findById(@PathVariable Integer id){
        return this.livreService.findById(id);
    }

    @GetMapping
    public List<Livre> findAll(){
        return this.livreService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id)
    {
        this.livreService.deleteById(id);
    }

    @PostMapping
    public Livre add(@RequestBody Livre livre)
    {
        return this.livreService.create(livre);
    }

    @PutMapping("/{id}")
    public Livre update(@PathVariable Integer id,@RequestBody Livre livre)
    {
        livre.setId(id);
        this.livreService.create(livre);
        return livre;
    }
}
