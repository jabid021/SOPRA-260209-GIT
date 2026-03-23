package demo.composant;

public class Game {

	
	private IConfig audio;
	private Graphisme configGraphisme;
	
	
	public Game() {
	}
	
	
	public IConfig getAudio() {
		return audio;
	}
	public void setAudio(IConfig audio) {
		this.audio = audio;
	}
	public Graphisme getConfigGraphisme() {
		return configGraphisme;
	}
	public void setConfigGraphisme(Graphisme configGraphisme) {
		this.configGraphisme = configGraphisme;
	}
	
	
	@Override
	public String toString() {
		return "Game [audio=" + audio + ", configGraphisme=" + configGraphisme + "]";
	}
	
	
	
	
	
}
