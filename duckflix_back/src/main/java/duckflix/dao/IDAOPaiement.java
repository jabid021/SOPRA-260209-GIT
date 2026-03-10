package duckflix.dao;

import java.util.List;

import duckflix.model.Carte;
import duckflix.model.Paiement;
import duckflix.model.Paypal;
import duckflix.model.Virement;

public interface IDAOPaiement extends IDAO<Paiement,Integer> {

	public List<Carte> findAllCarte();
	public List<Paypal> findAllPaypal();
	public List<Virement> findAllVirement();
}
