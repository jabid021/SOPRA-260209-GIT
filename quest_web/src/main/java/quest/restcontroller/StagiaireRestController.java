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

import quest.model.Stagiaire;
import quest.service.StagiaireService;

@RestController
@RequestMapping("/api/stagiaire")
public class StagiaireRestController{

	@Autowired
	StagiaireService stagiaireSrv;

	
	@GetMapping("/{id}")
	public Stagiaire chercherById(@PathVariable Integer id) 
	{
		Stagiaire stagiaire = stagiaireSrv.getById(id);
		return  null;
	}

	@GetMapping
	public List<Stagiaire> chercherAll()  
	{
		List<Stagiaire> stagiaires = stagiaireSrv.getAll();
		return null;
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
