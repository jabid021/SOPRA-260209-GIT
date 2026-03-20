package quest.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import quest.context.Singleton;
import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;


@WebServlet("/login")
public class LoginController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("logout")!=null) 
		{
			request.getSession().invalidate();
			this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		Personne personne = Singleton.getInstance().getDaoPersonne().findByLoginAndPassword(login, password);

	

		if(personne==null) 
		{
			request.setAttribute("error", "");
			this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		}
		else {

			request.getSession().setAttribute("connected", personne);
			if(personne instanceof Stagiaire) 
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
