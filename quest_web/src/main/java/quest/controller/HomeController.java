package quest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import quest.dao.IDAOPersonne;
import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;

@Controller
public class HomeController {

	@Autowired
	IDAOPersonne daoPersonne;
	
	@GetMapping({"/home","/"})
	public String home(HttpSession session) 
	{
		if(session.getAttribute("connected")==null) 
		{
			return "forward:/home.jsp";
		}
		else 
		{
			Personne personne = (Personne)session.getAttribute("connected");
			if(personne instanceof Stagiaire ) 
			{
				return "espaceStagiaire.jsp";
			}
			else
			{
				if(((Formateur) personne).isAdmin()) 
				{
					return "espaceAdmin.jsp";
				}
				else 
				{
					return "espaceFormateur.jsp";
				}
			}
		}
	}
	
	@PostMapping("/login")
	public String connect(@RequestParam String login, @RequestParam String password,HttpSession session) 
	{

		Personne personne = daoPersonne.findByLoginAndPassword(login, password);
		if(personne==null) 
		{
			return "redirect:/home?error";
		}
		else {
			session.setAttribute("connected", personne);
			List<String> roles = new ArrayList();
			if(personne instanceof Stagiaire) 
			{
				roles.add("ROLE_STAGIAIRE");
			}
			else if(personne instanceof Formateur) 
			{
				roles.add("ROLE_FORMATEUR");
				if(((Formateur) personne).isAdmin()) 
				{
					roles.add("ROLE_ADMIN");
				}
			}
			session.setAttribute("roles", roles);
			return "redirect:/home";
		}
	}
	
	@GetMapping("/logout")
	public String disconnect(HttpSession session) 
	{
		session.invalidate();
		return "redirect:/home";
	}
}
