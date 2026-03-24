package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import demo.aop.ClassMetier;
import demo.composant.Audio;
import demo.composant.Graphisme;
import demo.composant.IConfig;

@Configuration
@ComponentScan({"demo.composant","demo.aop"})
@ImportResource("classpath:application-context.xml")
public class AppConfig {

	@Bean
	public IConfig audioCustom() 
	{
		Audio audio = new Audio();
		audio.setTypeSortie("Stereo");
		audio.setVolume(10);
		return audio;
	}
	
	@Bean
	public IConfig graphismeCustom() 
	{
		Graphisme g = new Graphisme();
		g.setFenetre(true);
		return g;
	}

}
