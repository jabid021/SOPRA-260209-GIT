package quest.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import quest.context.Singleton;
import quest.model.Filiere;
import quest.model.Genre;
import quest.model.Stagiaire;


@WebServlet("/stagiaire")
public class StagiaireController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")==null) 
		{
			chercherAll(request,response);
		}
		else 
		{
			if(request.getParameter("delete")==null) 
			{
				chercherById(request,response);
			}
			else 
			{
				supprimer(request,response);
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")=="") 
		{
			ajouter(request,response);
		}
		else 
		{
			modifier(request,response);
		}
	}
	
	
	
	public void chercherById(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Stagiaire stagiaire = (Stagiaire) Singleton.getInstance().getDaoPersonne().findById(id);
		List<Stagiaire> stagiaires = Singleton.getInstance().getDaoPersonne().findAllStagiaire();
		List<Filiere> filieres = Singleton.getInstance().getDaoFiliere().findAll();
		request.setAttribute("stagiaire", stagiaire);
		request.setAttribute("stagiaires", stagiaires);
		request.setAttribute("filieres", filieres);
		request.setAttribute("civilites", Genre.values());
		this.getServletContext().getRequestDispatcher("/WEB-INF/stagiaires.jsp").forward(request, response);
	}
	
	public void chercherAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		List<Stagiaire> stagiaires = Singleton.getInstance().getDaoPersonne().findAllStagiaire();
		List<Filiere> filieres = Singleton.getInstance().getDaoFiliere().findAll();
		request.setAttribute("stagiaire", new Stagiaire());
		request.setAttribute("stagiaires", stagiaires);
		request.setAttribute("filieres", filieres);
		request.setAttribute("civilites", Genre.values());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/stagiaires.jsp").forward(request, response);
	}
	
	public void supprimer(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Singleton.getInstance().getDaoPersonne().deleteById(id);
		response.sendRedirect("stagiaire");
		
	}
	public void ajouter(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String civilite = request.getParameter("civilite");
		String email = request.getParameter("email");
		String numero = request.getParameter("adresse.numero");
		String voie = request.getParameter("adresse.voie");
		String ville = request.getParameter("adresse.ville");
		String cp = request.getParameter("adresse.cp");
		Integer idFiliere =Integer.parseInt(request.getParameter("filiere.id"));
		Filiere filiere = new Filiere();
		filiere.setId(idFiliere);
		Stagiaire stagiaire = new Stagiaire(login, password, nom, prenom, Genre.valueOf(civilite), email, numero, voie, ville, cp, filiere);
		Singleton.getInstance().getDaoPersonne().save(stagiaire);
		
		response.sendRedirect("stagiaire");
	}

	public void modifier(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String civilite = request.getParameter("civilite");
		String email = request.getParameter("email");
		String numero = request.getParameter("adresse.numero");
		String voie = request.getParameter("adresse.voie");
		String ville = request.getParameter("adresse.ville");
		String cp = request.getParameter("adresse.cp");
		Integer idFiliere =Integer.parseInt(request.getParameter("filiere.id"));
		Filiere filiere = new Filiere();
		filiere.setId(idFiliere);
		Stagiaire stagiaire = new Stagiaire(id,login, password, nom, prenom, Genre.valueOf(civilite), email, numero, voie, ville, cp, filiere);
		Singleton.getInstance().getDaoPersonne().save(stagiaire);
		
		response.sendRedirect("stagiaire");
	}
	

}
