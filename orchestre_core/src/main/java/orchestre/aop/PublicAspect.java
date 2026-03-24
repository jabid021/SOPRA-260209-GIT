package orchestre.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class PublicAspect {

	@AfterReturning(returning = "phrase", pointcut = "execution(public String orchestre.composant.Guitariste.toString())")
	public void afterToStringGuitariste(String phrase) 
	{
		System.out.println(phrase);
		System.out.println("Le guitariste vient de se presenter");
	}
	
	//public void monPointCutJouer() {}
	
	public void installer(){System.out.println("Le public s'installe");}
	
	
	public void applaudir(){System.out.println("Le public applaudit");}
	
	
	public void huer(){System.out.println("Le public jette des tomates");}
	
}
