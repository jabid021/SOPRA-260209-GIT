package biblio.api;

import biblio.dao.IDAOAuteur;
import biblio.model.Auteur;
import biblio.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auteur")
@CrossOrigin(origins = "http://localhost:4200")
public class AuteurRestController {
    
    @Autowired
    private AuteurService auteurService;

    @GetMapping("/{id}")
    public Auteur findById(@PathVariable Integer id){
        return this.auteurService.findById(id);
    }

    @GetMapping
    public List<Auteur> findAll(){
        return this.auteurService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {this.auteurService.deleteById(id);}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Auteur add(@RequestBody Auteur auteur) {return this.auteurService.add(auteur);}

    @PutMapping("/{id}")
    public Auteur update(@PathVariable Integer id, @RequestBody Auteur auteur)
    {
        return this.auteurService.update(id,auteur);
    }
}