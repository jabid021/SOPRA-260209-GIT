package demo.composant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Game {
		//Autowired => Spring va essayer d'injecter la dependance / le bean requis
	//Choix 1 => le bean est introuvable => exception
	//Choix 2 => Spring trouve plus de 1 bean qui correspond => exception (il faut lever l'ambiguite)
	//Choix 3 => Spring trouve 1 et 1 seul bean qui match
	@Autowired
	private IConfig audio;
	
	@Autowired
	@Qualifier("graphisme")
	private IConfig configGraphisme;
	
	
	public Game() {
	}
	
	
	public IConfig getAudio() {
		return audio;
	}
	public void setAudio(IConfig audio) {
		this.audio = audio;
	}
	public IConfig getConfigGraphisme() {
		return configGraphisme;
	}
	public void setConfigGraphisme(IConfig configGraphisme) {
		this.configGraphisme = configGraphisme;
	}
	
	
	@Override
	public String toString() {
		return "Game [audio=" + audio + ", configGraphisme=" + configGraphisme + "]";
	}
	
	
	
	
	
}
