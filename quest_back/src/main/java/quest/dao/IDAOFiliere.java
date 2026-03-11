package quest.dao;

import quest.model.Filiere;

public interface IDAOFiliere extends IDAO<Filiere,Integer> {

	public Filiere findByIdWithEleves(Integer idFiliere);
}
