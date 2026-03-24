package orchestre.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import orchestre.composant.IMusicien;

public class Test {	
	
	 @Autowired
	 IMusicien pianiste;
	 
	 @Autowired
	 IMusicien guitariste;
	 
	 @Autowired
	 IMusicien flutiste;
	 
	 @Autowired
	 @Qualifier("musicien")
	 IMusicien olivier;
	
	public void run() {
		
	
	//	pianiste.jouer();
	//	olivier.jouer();
	//	flutiste.jouer();
		
		
		
		//guitariste.jouer();
		
		
		guitariste.toString();

	}

}
