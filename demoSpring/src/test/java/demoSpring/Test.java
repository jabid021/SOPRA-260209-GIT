package demoSpring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import demo.composant.Audio;
import demo.composant.Game;
import demo.composant.IConfig;
import demo.config.AppConfig;

public class Test {

	public static void main(String[] args) {
	
		//Recup un objet GAME qui contient sa config Audio + Graphisme
		//Singleton.getInstance().getGame();
		
		
		
		//On ne peut avoir qu'une seule config principale
			//Config principale en XML
		//ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		//Config principale en JAVA
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
			
		
		Audio audio =  ctx.getBean("audio",Audio.class);
		IConfig graphisme = (IConfig) ctx.getBean("graphisme");
		Game game = ctx.getBean("game",Game.class);
	
		
		IConfig audio2 =  ctx.getBean("audioCustom",Audio.class);
		IConfig graphisme2 = (IConfig) ctx.getBean("graphismeCustom");
		
		System.out.println("----------Beans par defaut @component-----------");
		System.out.println(audio);
		System.out.println(graphisme);
		
		System.out.println("----------Beans du AppConfig-----------");
		System.out.println(audio2);
		System.out.println(graphisme2);
		System.out.println(game);
		
	
		//Game game;
		
		//System.out.println(game);
	}

}
