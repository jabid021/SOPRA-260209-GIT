package demo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calculette3")
public class CalculetteController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nb = Integer.parseInt(request.getParameter("nombre"));
		int carre = nb*nb;
		
		request.setAttribute("leCarre", carre);
		
		this.getServletContext().getRequestDispatcher("/calculette3.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nb1 = Integer.parseInt(request.getParameter("number1"));
		int nb2 = Integer.parseInt(request.getParameter("number2"));
		int resultat;
		String message;
		if(request.getParameter("operation").equals("add")) 
		{
			resultat = nb1+nb2;
			message="+";
		}
		else 
		{
			resultat = nb1-nb2;
			message="-";
		}
		
		request.setAttribute("result", resultat);
		request.setAttribute("messg", message);
		
		this.getServletContext().getRequestDispatcher("/addAndSoustract.jsp").forward(request, response);


	}

}
