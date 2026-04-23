package biblio.api;

import biblio.model.Editeur;
import biblio.service.EditeurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editeurs")
@CrossOrigin(origins = "http://localhost:4200")
public class EditeurController {

    @Autowired
    private EditeurService editeurService;

    @GetMapping
    public List<Editeur> getAll() {
        return this.editeurService.findAll();
    }

    @GetMapping("/{id}")
    public Editeur getById(@PathVariable Integer id) {
        return this.editeurService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Editeur create(@RequestBody Editeur editeur) {
        return this.editeurService.create(editeur);
    }

    @PutMapping("/{id}")
    public Editeur update(@PathVariable Integer id, @RequestBody Editeur editeur) {
        return this.editeurService.update(id, editeur);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        this.editeurService.deleteById(id);
    }
}