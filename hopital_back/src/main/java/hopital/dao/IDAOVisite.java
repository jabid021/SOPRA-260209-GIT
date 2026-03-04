package hopital.dao;

import java.util.List;

import hopital.model.Visite;

public interface IDAOVisite extends IDAO<Visite,Integer> {

	public List<Visite> findByPatientId(Integer id);
	public double findByDateVisiteBetweenAndMedecinId(String debut,String fin,Integer id); 

}
