package quest.dao;

import java.util.List;

import quest.model.Ordinateur;

public interface IDAOOrdinateur {

	public List<Ordinateur> findByMarque(String marque);
}
