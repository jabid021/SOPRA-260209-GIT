package demo.composant;

import org.springframework.stereotype.Component;

@Component
public class Audio implements IConfig {

	private int volume;
	private String typeSortie;
	
	public Audio() 
	{
		this.volume=100;
		this.typeSortie="Stereo";
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getTypeSortie() {
		return typeSortie;
	}

	public void setTypeSortie(String typeSortie) {
		this.typeSortie = typeSortie;
	}

	@Override
	public String toString() {
		return "Audio [volume=" + volume + ", typeSortie=" + typeSortie + "]";
	}
	
	
}
