package quest.test;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import quest.context.Singleton;

public class App {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
		
		System.out.println(Singleton.getInstance().getDaoPersonne().findAllStagiaire());
		
		System.out.println(Singleton.getInstance().getDaoOrdinateur().findByMarque("????"));
	
		System.out.println(Singleton.getInstance().getDaoModule().findByQuest(7777));
		
		emf.close();
		
		
		System.out.println("TOUT EST OK");
	}

}
