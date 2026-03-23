package ochestre.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import orchestre.composant.Flutiste;
import orchestre.composant.Guitariste;
import orchestre.composant.IMusicien;
import orchestre.composant.Pianiste;

@Configuration
@ComponentScan("orchestre.composant")
public class AppConfig {

	@Bean
	public IMusicien guitariste() 
	{
		Guitariste guitariste = new Guitariste();
		guitariste.setPrenom("Jordan");
		return guitariste;
		
	}
	
	@Bean
	public IMusicien pianiste()
	{
		Pianiste pianiste = new Pianiste();
		pianiste.setPrenom("Eric");
		return pianiste;
	}
	
	//Generer un bean (id=musicien qui est de type Flutiste)
	@Bean
	public IMusicien musicien()
	{
		Flutiste flutiste = new Flutiste();
		flutiste.setPrenom("Olivier");
		return flutiste;
	}
	
	
	
}
