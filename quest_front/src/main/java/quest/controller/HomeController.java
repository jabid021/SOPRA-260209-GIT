package quest.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;


@WebServlet("/home")
public class HomeController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("connected")==null) 
		{
			this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		}
		else 
		{
			Personne personne = (Personne) request.getSession().getAttribute("connected");
			if(personne instanceof Stagiaire ) 
			{
				this.getServletContext().getRequestDispatcher("/WEB-INF/espaceStagiaire.jsp").forward(request, response);
			}
			else if(personne instanceof Formateur) 
			{
				if(((Formateur) personne).isAdmin()) 
				{
					this.getServletContext().getRequestDispatcher("/WEB-INF/espaceAdmin.jsp").forward(request, response);
				}
				else 
				{
					this.getServletContext().getRequestDispatcher("/WEB-INF/espaceFormateur.jsp").forward(request, response);
				}
			}
		}
		
		
	}

	
}
