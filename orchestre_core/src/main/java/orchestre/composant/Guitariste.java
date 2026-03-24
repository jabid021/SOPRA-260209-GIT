package orchestre.composant;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Guitariste implements IMusicien {

	private String prenom;

	@Autowired
	@Qualifier("guitare")
	private IInstrument instrument;

	public Guitariste() {}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public IInstrument getInstrument() {
		return instrument;
	}

	public void setInstrument(IInstrument instrument) {
		this.instrument = instrument;
	}

	@Override
	public void jouer() {
		Random random = new Random();
		if(random.nextInt(3)==0) //1 chance sur trois de faire une fausse note
		{
			System.out.println("le guitariste "+prenom+" joue mais fait une fausse note !");
			throw new RuntimeException("Fausse note....");
		}
		System.out.println("le guitariste "+prenom+" joue ! "+instrument.son());

	}

	@Override
	public String toString() {
		return "Guitariste [prenom=" + prenom +"]";
	}



}
