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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import quest.dao.IDAOPersonne;
import quest.model.Formateur;
import quest.model.Genre;

@Controller
@RequestMapping("/formateur")
public class FormateurController{

	@Autowired
	IDAOPersonne daoPersonne;
	
	
	@GetMapping("/{id}")
	public String chercherById(@PathVariable Integer id,Model model) 
	{
		Formateur formateur = (Formateur) daoPersonne.findById(id).orElse(null);
		List<Formateur> formateurs = daoPersonne.findAllFormateur();

		model.addAttribute("formateur", formateur);
		model.addAttribute("formateurs", formateurs);
		model.addAttribute("civilites",Genre.values());
		return "formateurs.jsp";
	}

	@GetMapping
	public String chercherAll(Model model)  
	{
		List<Formateur> formateurs = daoPersonne.findAllFormateur();
		model.addAttribute("formateur", new Formateur());
		model.addAttribute("formateurs", formateurs);
		model.addAttribute("civilites",Genre.values());

		return "formateurs.jsp";
	}

	@GetMapping("/delete/{id}")
	public String supprimer(@PathVariable Integer id)  
	{
		daoPersonne.deleteById(id);
		return "redirect:/formateur";

	}
	
	@PostMapping
	public String ajouter(@ModelAttribute Formateur formateur)  
	{
		daoPersonne.save(formateur);
		return "redirect:/formateur";
	}

	@PostMapping("/{id}")
	public String modifier(@PathVariable Integer id,@ModelAttribute Formateur formateur)  
	{
		daoPersonne.save(formateur);
		return "redirect:/formateur";
	}
	
	//Update partiel // PATCH
	@PostMapping("/identifiants")
	public String modifierIdentifiants(@RequestParam String login,String password,HttpSession session) 
	{
		Formateur formateur = (Formateur) session.getAttribute("connected");
		formateur.setLogin(login);
		formateur.setPassword(password);
		session.setAttribute("connected", formateur);
		daoPersonne.save(formateur);
		return "redirect:/home";
	}
}
