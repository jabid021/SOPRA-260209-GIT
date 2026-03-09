package quest.test;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
		
		
		emf.close();
		
		
		System.out.println("TOUT EST OK");
	}

}
