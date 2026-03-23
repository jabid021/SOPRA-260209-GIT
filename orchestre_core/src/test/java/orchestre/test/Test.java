package orchestre.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ochestre.config.AppConfig;
import orchestre.composant.IMusicien;

public class Test {	
	
	 @Autowired
	 IMusicien pianiste;
	 
	 @Autowired
	 IMusicien guitariste;
	 
	 @Autowired
	 IMusicien flutiste;
	 
	 @Autowired
	 IMusicien olivier;
	
	public void main(String[] args) {
		
		
		//ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		
		//Changer la config principale
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	
	
		pianiste.jouer();
		olivier.jouer();
		flutiste.jouer();
		guitariste.jouer();
		
		
		

	}

}
