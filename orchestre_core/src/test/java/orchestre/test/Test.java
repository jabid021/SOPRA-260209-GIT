package orchestre.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import orchestre.composant.Flutiste;
import orchestre.composant.Guitariste;
import orchestre.composant.IMusicien;
import orchestre.composant.Pianiste;

public class Test {	
	
	public static void main(String[] args) {
		
		
		//ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		
		//Changer la config principale
		//... ctx...
		
	
		IMusicien pianiste = ctx.getBean(Pianiste.class);
		
		IMusicien olivier = (Flutiste) ctx.getBean("musicien");
		
		IMusicien guitariste = ctx.getBean(Guitariste.class);
		
		
		pianiste.jouer();
		
		olivier.jouer();
		
		guitariste.jouer();
		
		
		

	}

}
