package hopital.dao;

import java.time.LocalDate;
import java.util.List;

import hopital.model.Visite;

public interface IDAOVisite extends IDAO<Visite,Integer> {

	public List<Visite> findByPatientId(Integer id);
	public List<Visite> findByDateVisiteBetweenAndMedecinId(LocalDate debut,LocalDate fin,Integer id); 
	public double sumPrixByDateVisiteBetweenAndMedecinId(LocalDate debut,LocalDate fin,Integer id); 
}
