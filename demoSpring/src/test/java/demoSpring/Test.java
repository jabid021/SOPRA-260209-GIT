package demoSpring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.composant.Audio;
import demo.composant.Game;
import demo.composant.IConfig;

public class Test {

	public static void main(String[] args) {
	
		//Recup un objet GAME qui contient sa config Audio + Graphisme
		//Singleton.getInstance().getGame();
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
	
		
		Audio audio =  ctx.getBean(Audio.class);
		IConfig graphisme = (IConfig) ctx.getBean("beanGraph");
		Game game = ctx.getBean("game1",Game.class);
		Game game2 = ctx.getBean("game2",Game.class);
		System.out.println(audio);
		System.out.println(graphisme);
		System.out.println(game);
		System.out.println(game2);
		//Game game;
		
		//System.out.println(game);
	}

}
