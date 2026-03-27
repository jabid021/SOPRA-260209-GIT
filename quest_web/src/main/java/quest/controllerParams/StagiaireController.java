package quest.controllerParams;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import quest.dao.IDAOFiliere;
import quest.dao.IDAOPersonne;
import quest.model.Filiere;
import quest.model.Genre;
import quest.model.Stagiaire;

/*@Controller
@RequestMapping("/stagiaire")*/
public class StagiaireController{

	@Autowired
	IDAOPersonne daoPersonne;
	
	@Autowired
	IDAOFiliere daoFiliere;

	
	@GetMapping("/{id}")
	public String chercherById(@PathVariable Integer id,Model model) 
	{
		Stagiaire stagiaire = (Stagiaire) daoPersonne.findById(id).orElse(null);
		List<Stagiaire> stagiaires = daoPersonne.findAllStagiaire();
		List<Filiere> filieres = daoFiliere.findAll();

		model.addAttribute("stagiaire", stagiaire);
		model.addAttribute("stagiaires", stagiaires);
		model.addAttribute("filieres",filieres);
		model.addAttribute("civilites",Genre.values());
		return "stagiaires.jsp";
	}

	@GetMapping
	public String chercherAll(Model model)  
	{
		List<Stagiaire> stagiaires = daoPersonne.findAllStagiaire();
		List<Filiere> filieres = daoFiliere.findAll();
		model.addAttribute("stagiaire", new Stagiaire());
		model.addAttribute("stagiaires", stagiaires);
		model.addAttribute("filieres",filieres);
		model.addAttribute("civilites",Genre.values());

		return "stagiaires.jsp";
	}

	@GetMapping("/delete/{id}")
	public String supprimer(@PathVariable Integer id)  
	{
		daoPersonne.deleteById(id);
		return "redirect:/stagiaire";

	}
	
	@PostMapping
	public String ajouter(@RequestParam String login,@RequestParam String password,@RequestParam String nom, @RequestParam String prenom, @RequestParam Genre civilite,@RequestParam String email, @RequestParam(name="adresse.numero") String numero,@RequestParam(name="adresse.voie") String voie,@RequestParam(name="adresse.ville") String ville,@RequestParam(name="adresse.cp") String cp,@RequestParam("filiere.id") Integer idFiliere)  
	{
		Filiere filiere = new Filiere();
		filiere.setId(idFiliere);
		Stagiaire stagiaire = new Stagiaire(login, password, nom, prenom, civilite, email, numero, voie, ville, cp, filiere);
		daoPersonne.save(stagiaire);
		return "redirect:/stagiaire";
	}

	@PostMapping("/{id}")
	public String modifier(@PathVariable Integer id,@RequestParam String login,@RequestParam String password,@RequestParam String nom, @RequestParam String prenom, @RequestParam Genre civilite,@RequestParam String email, @RequestParam(name="adresse.numero") String numero,@RequestParam(name="adresse.voie") String voie,@RequestParam(name="adresse.ville") String ville,@RequestParam(name="adresse.cp") String cp,@RequestParam("filiere.id") Integer idFiliere)  
	{
	
		Filiere filiere = new Filiere();
		filiere.setId(idFiliere);
		Stagiaire stagiaire = new Stagiaire(id,login, password, nom, prenom, civilite, email, numero, voie, ville, cp, filiere);
		daoPersonne.save(stagiaire);
		return "redirect:/stagiaire";
	}


}
