package quest.test;

import quest.context.Singleton;

public class App {

	public static void main(String[] args) {
	
		
		System.out.println(Singleton.getInstance().getDaoPersonne().findAllStagiaire());
		
		System.out.println(Singleton.getInstance().getDaoOrdinateur().findByMarque("Asuss"));
	
		System.out.println(Singleton.getInstance().getDaoModule().findByQuest(777));
		
		Singleton.getInstance().getEmf().close();
	
		System.out.println("TOUT EST OK");
	}

}
