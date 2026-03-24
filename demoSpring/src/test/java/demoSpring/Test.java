package demoSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import demo.aop.ClassMetier;
import demo.composant.Audio;
import demo.composant.Game;
import demo.composant.IConfig;

public class Test {

	@Autowired
	Audio audio;
	
	@Autowired
	IConfig graphisme;
	
	@Autowired
	Game game;
	
	@Autowired
	@Qualifier("audioCustom")
	IConfig audio2;
	
	
	
	@Autowired
	@Qualifier("graphismeCustom")
	IConfig graphisme2;
	
	
	
	 @Autowired
	 ClassMetier classMetier;
	
	public void run() {
	
		//Recup un objet GAME qui contient sa config Audio + Graphisme
		//Singleton.getInstance().getGame();

	
		/*Audio audio =  ctx.getBean("audio",Audio.class);
		IConfig graphisme = (IConfig) ctx.getBean("graphisme");
		Game game = ctx.getBean("game",Game.class);
	
		
		IConfig audio2 =  ctx.getBean("audioCustom",Audio.class);
		IConfig graphisme2 = (IConfig) ctx.getBean("graphismeCustom");*/
		
		System.out.println("----------Beans par defaut @component-----------");
		System.out.println(audio);
		System.out.println(graphisme);
		System.out.println(game);
		System.out.println("----------Beans du AppConfig-----------");
		System.out.println(audio2);
		System.out.println(graphisme2);
		
		
		System.out.println("-----------DEMO AOP Before + After-----------------");
		
		
		classMetier.demo();
		
		System.out.println("-----------DEMO AOP Succes / Echec-----------------");
		
		try {
		classMetier.demo2("Message envoyé en param à demo2");
		}
		catch(Exception e) {}
		
	}

}
