package quest.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import quest.model.Stagiaire;
import quest.service.StagiaireService;
import quest.view.Views;

@RestController
@RequestMapping("/api/stagiaire")
public class StagiaireRestController{

	@Autowired
	StagiaireService stagiaireSrv;

	
	@GetMapping("/{id}")
	@JsonView(Views.Stagiaire.class) // id + prenom + nom + civ + adresse + email + Ordinateur (id + marque + ram)
	public Stagiaire chercherById(@PathVariable Integer id) 
	{
		return stagiaireSrv.getById(id);
	}

	@GetMapping
	@JsonView(Views.Stagiaire.class)
	public List<Stagiaire> chercherAll()  
	{
		return stagiaireSrv.getAll();
	}

	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)  
	{
		stagiaireSrv.deleteById(id);
	}
	
	@PostMapping
	public Stagiaire ajouter(@RequestBody Stagiaire stagiaire)  
	{
		stagiaireSrv.insert(stagiaire);
		return stagiaire;
	}

	@PutMapping("/{id}")
	public Stagiaire modifier(@PathVariable Integer id,@RequestBody Stagiaire stagiaire)  
	{
		stagiaire.setId(id);
		stagiaireSrv.update(stagiaire);
		return stagiaire;
	}


}
