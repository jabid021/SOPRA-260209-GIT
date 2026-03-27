package quest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServlet;
import quest.dao.IDAOOrdinateur;
import quest.dao.IDAOPersonne;
import quest.model.Ordinateur;
import quest.model.Stagiaire;

@Controller
@RequestMapping("/ordinateur")
public class OrdinateurController extends HttpServlet {

	@Autowired
	IDAOOrdinateur daoOrdinateur;
	
	@Autowired
	IDAOPersonne daoPersonne;

	
	@GetMapping("/{id}")
	public String chercherById(@PathVariable Integer id,Model model) 
	{
		Ordinateur ordinateur = daoOrdinateur.findById(id).orElse(null);
		List<Ordinateur> ordinateurs = daoOrdinateur.findAll();
		List<Stagiaire> stagiaires = daoPersonne.findAllStagiaireDisponibles();

		if(ordinateur.getUtilisateur()!=null) 
		{
			stagiaires.add(ordinateur.getUtilisateur());
		}
		
		model.addAttribute("ordinateur", ordinateur);
		model.addAttribute("ordinateurs", ordinateurs);
		model.addAttribute("stagiaires",stagiaires);
		return "ordinateurs.jsp";
	}

	@GetMapping
	public String chercherAll(Model model)  
	{
		List<Ordinateur> ordinateurs = daoOrdinateur.findAll();
		List<Stagiaire> stagiaires = daoPersonne.findAllStagiaireDisponibles();
		model.addAttribute("ordinateur", new Ordinateur());
		model.addAttribute("ordinateurs", ordinateurs);
		model.addAttribute("stagiaires",stagiaires);
		return "ordinateurs.jsp";
	}

	@GetMapping("/delete/{id}")
	public String supprimer(@PathVariable Integer id)  
	{
		daoOrdinateur.deleteById(id);
		return "redirect:/ordinateur";

	}
	
	@PostMapping
	public String ajouter(@RequestParam String marque,@RequestParam Integer ram,@RequestParam(name="utilisateur.id", required = false) Integer idStagiaire)  
	{
		Ordinateur ordinateur = new Ordinateur(marque, ram);
		if(idStagiaire!=null) 
		{
			Stagiaire stagiaire = new Stagiaire();
			stagiaire.setId(idStagiaire);
			ordinateur.setUtilisateur(stagiaire);
		}
		
		daoOrdinateur.save(ordinateur);
		return "redirect:/ordinateur";
	}

	@PostMapping("/{id}")
	public String modifier(@PathVariable Integer id,@RequestParam String marque,@RequestParam Integer ram,@RequestParam(name="utilisateur.id", required = false) Integer idStagiaire)  
	{
		Ordinateur ordinateur = new Ordinateur(id,marque, ram);
		if(idStagiaire!=null) 
		{
			Stagiaire stagiaire = new Stagiaire();
			stagiaire.setId(idStagiaire);
			ordinateur.setUtilisateur(stagiaire);
		}
		daoOrdinateur.save(ordinateur);
		return "redirect:/ordinateur";
	}


}
