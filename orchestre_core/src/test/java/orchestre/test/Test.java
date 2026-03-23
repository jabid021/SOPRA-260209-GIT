package orchestre.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import orchestre.composant.IMusicien;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		
		IMusicien pianiste;
		
		IMusicien olivier;
		
		IMusicien guitariste;
		
		
		pianiste.jouer();
		
		olivier.jouer();
		
		guitariste.jouer();
		
		
		

	}

}
