package demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectDemo {

	@Pointcut("execution(public void demo.aop.ClassMetier.demo())")
	public void pointCutDemo() {}
	
	@Around("pointCutDemo()")
	public void lancerAutoursDemo(ProceedingJoinPoint pj) throws Throwable 
	{
		System.out.println("Se lance avant (dans around)");
		pj.proceed(); //Execute le code de la fonction qu'on ecoute
		System.out.println("Se lance apres (dans around)");
	}
	
	@Before("pointCutDemo()")
	public void lancerAvantDemo()
	{
		System.out.println("Se lance avant");
	}

	@After("pointCutDemo()")
	public void lancerApresDemo()
	{
		System.out.println("Se lance apres");
	}
	

	@AfterReturning(returning = "resultFonctionEcoute",pointcut = "execution(public String demo.aop.ClassMetier.demo2(String))")
	public void lancerDemo2Succes(String resultFonctionEcoute)
	{
		System.out.println("Se lance apres succes, msg :" + resultFonctionEcoute);
	}
	
	@AfterThrowing(throwing = "exception", pointcut = "execution(public String demo.aop.ClassMetier.demo2(String))")
	public void lancerDemo2Echec(Exception exception)
	{
		System.out.println("Se lance apres echec, l'exception etait : "+exception.getMessage());
	}

	
	
	
}
