package demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class AspectDemo {

	public void lancerAvantDemo()
	{
		System.out.println("Se lance avant");
	}

	public void lancerApresDemo()
	{
		System.out.println("Se lance apres");
	}

	public void lancerDemo2Succes(String resultFonctionEcoute)
	{
		System.out.println("Se lance apres succes, msg :" + resultFonctionEcoute);
	}
	
	public void lancerDemo2Echec(Exception e)
	{
		System.out.println("Se lance apres echec, l'exception etait : "+e.getMessage());
	}

	
	
	public void lancerAutoursDemo(ProceedingJoinPoint pj) throws Throwable 
	{
		System.out.println("Se lance avant (dans around)");
		pj.proceed(); //Execute le code de la fonction qu'on ecoute
		System.out.println("Se lance apres (dans around)");
	}
}
