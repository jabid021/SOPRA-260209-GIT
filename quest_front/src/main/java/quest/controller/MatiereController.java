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

	//findById + findAll + delete
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//findAll car pas d'id
		if(request.getParameter("id")==null) 
		{
			chercherAll(request,response);
		}
		else 
		{	//findById
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

	//insert + update
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//insert car pas d'id
		if(request.getParameter("id")==null) 
		{
			ajouter(request,response);
		}
		else 
		{
			modifier(request,response);
		}
	}
	
	
	/* On adapte pour chaque model a partir d'ici */
	
	public void chercherById(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Matiere matiere = Singleton.getInstance().getDaoMatiere().findById(id);
		List<Matiere> matieres = Singleton.getInstance().getDaoMatiere().findAll();
		
		request.setAttribute("matiere", matiere);
		request.setAttribute("matieres", matieres);
		
		this.getServletContext().getRequestDispatcher("/matieres.jsp").forward(request, response);
	}
	
	public void chercherAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		List<Matiere> matieres = Singleton.getInstance().getDaoMatiere().findAll();
		
		request.setAttribute("matieres", matieres);
		
		this.getServletContext().getRequestDispatcher("/matieres.jsp").forward(request, response);
	}
	
	public void supprimer(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {}
	public void ajouter(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {}
	public void modifier(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {}
	

}
