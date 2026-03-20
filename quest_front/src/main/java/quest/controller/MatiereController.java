package quest.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import quest.context.Singleton;
import quest.model.Matiere;


@WebServlet("/matiere")
public class MatiereController extends HttpServlet {

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
		Matiere matiere = Singleton.getInstance().getDaoMatiere().findById(id);
		List<Matiere> matieres = Singleton.getInstance().getDaoMatiere().findAll();
		
		request.setAttribute("matiere", matiere);
		request.setAttribute("matieres", matieres);
		request.setAttribute("messageForm", "Formulaire d'update (Matiere "+matiere.getId()+" - "+matiere.getLibelle()+")");
		this.getServletContext().getRequestDispatcher("/matieres.jsp").forward(request, response);
	}
	
	public void chercherAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		List<Matiere> matieres = Singleton.getInstance().getDaoMatiere().findAll();
		request.setAttribute("matiere", new Matiere());
		request.setAttribute("matieres", matieres);
		request.setAttribute("messageForm", "Formulaire d'ajout");
		
		this.getServletContext().getRequestDispatcher("/matieres.jsp").forward(request, response);
	}
	
	public void supprimer(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Singleton.getInstance().getDaoMatiere().deleteById(id);
		response.sendRedirect("matiere");
		
	}
	public void ajouter(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		String libelle = request.getParameter("libelle");
		
		Matiere matiere = new Matiere(libelle);
		Singleton.getInstance().getDaoMatiere().save(matiere);
		
		response.sendRedirect("matiere");
	}

	public void modifier(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		String libelle = request.getParameter("libelle");
		Integer version = Integer.parseInt(request.getParameter("version"));
		Matiere matiere = new Matiere(id,libelle);
		matiere.setVersion(version);
		Singleton.getInstance().getDaoMatiere().save(matiere);
		
		response.sendRedirect("matiere");
	}
	

}
