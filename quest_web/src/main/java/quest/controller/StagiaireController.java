package quest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import quest.dao.IDAOFiliere;
import quest.model.Filiere;
import quest.model.Genre;
import quest.model.Stagiaire;
import quest.service.StagiaireService;

@Controller
@RequestMapping("/stagiaire")
public class StagiaireController {

	@Autowired
	StagiaireService stagiaireSrv;
	
	@Autowired
	IDAOFiliere daoFiliere;

	
	@GetMapping("/{id}")
	public String chercherById(@PathVariable Integer id,Model model) 
	{
		Stagiaire stagiaire = stagiaireSrv.getById(id);
		List<Stagiaire> stagiaires = stagiaireSrv.getAll();
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
		List<Stagiaire> stagiaires = stagiaireSrv.getAll();
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
		stagiaireSrv.deleteById(id);
		return "redirect:/stagiaire";

	}
	
	@PostMapping
	public String ajouter(@ModelAttribute Stagiaire stagiaire)  
	{
		stagiaireSrv.insert(stagiaire);
		return "redirect:/stagiaire";
	}

	@PostMapping("/{id}")
	public String modifier(@PathVariable Integer id,@ModelAttribute Stagiaire stagiaire)  
	{
		stagiaireSrv.update(stagiaire);
		return "redirect:/stagiaire";
	}


}
