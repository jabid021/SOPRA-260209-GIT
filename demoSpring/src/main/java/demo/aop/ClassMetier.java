package demo.aop;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class ClassMetier {

	public void demo()
	{
		System.out.println("---Traitement de la méthode metier----");
	}
	
	
	public String demo2(String message) throws Exception
	{
		System.out.println("---Traitement de la méthode metier 2----");
		System.out.println(message);
		Random r = new Random();
		if(r.nextInt(2)==0)
		{
			throw new Exception("la methode plante !");
		}
		return "un String qu'on retourne !";
	}
}
