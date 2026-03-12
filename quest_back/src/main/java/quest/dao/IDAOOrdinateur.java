package quest.dao;

import java.util.List;

import quest.model.Ordinateur;

public interface IDAOOrdinateur extends IDAO<Ordinateur,Integer> {

	public List<Ordinateur> findByMarque(String marque);
}
