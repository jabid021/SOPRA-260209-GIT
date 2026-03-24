package orchestre.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PublicAspect {

	@AfterReturning(returning = "phrase", pointcut = "execution(public String orchestre.composant.Guitariste.toString())")
	public void afterToStringGuitariste(String phrase) 
	{
		System.out.println(phrase);
		System.out.println("Le guitariste vient de se presenter");
	}
	
	@Pointcut("execution(public void orchestre.composant.Guitariste.jouer())")
	public void monPointCutJouer() {}
	
	@Before("monPointCutJouer()")
	public void installer(){System.out.println("Le public s'installe");}
	
	@AfterReturning("monPointCutJouer()")
	public void applaudir(){System.out.println("Le public applaudit");}
	
	@AfterThrowing(throwing = "e" , pointcut = "monPointCutJouer()")
	public void huer(Exception e)
	{
		System.out.println(e.getMessage());
		System.out.println("Le public jette des tomates");
	}
}
