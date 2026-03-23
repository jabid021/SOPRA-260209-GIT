package quest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
			response.sendRedirect("home");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		Personne personne = Singleton.getInstance().getDaoPersonne().findByLoginAndPassword(login, password);
		if(personne==null) 
		{
			response.sendRedirect("home?error");
		}
		else {
			request.getSession().setAttribute("connected", personne);
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
			request.getSession().setAttribute("roles", roles);
			response.sendRedirect("home");
		}
	}

}
