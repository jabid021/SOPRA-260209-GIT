package ochestre.config;

import org.springframework.context.annotation.Bean;

import orchestre.composant.Flutiste;
import orchestre.composant.Guitariste;
import orchestre.composant.IMusicien;
import orchestre.composant.Pianiste;

//Preciser que c'est une page de config
//Preciser ou se trouvent les composants
//Remplir les 2 beans manquants avec leur prenom
public class AppConfig {


	
	@Bean
	public IMusicien guitariste() 
	{
		Guitariste g = new Guitariste();
		g.setPrenom("Jordan");
		return g;
		
	}
	
	@Bean
	public IMusicien pianiste(){}
	
	
	//Bean du flutiste
	@Bean
	public IMusicien musicien(){}
}
