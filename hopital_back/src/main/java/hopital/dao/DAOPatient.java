package hopital.dao;

import java.util.List;

import hopital.context.Singleton;
import hopital.model.Patient;
import jakarta.persistence.EntityManager;

public class DAOPatient implements IDAOPatient{

	@Override
	public Patient findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Patient patient = em.find(Patient.class, id); 
		em.close();
		return patient;
	}

	@Override
	public List<Patient> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Patient> patients = em.createQuery("from Patient").getResultList();
		em.close();
		return patients;
	}

	@Override
	public Patient save(Patient patient) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			patient=em.merge(patient);
		em.getTransaction().commit();
		em.close();
		return patient;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Patient patient = em.find(Patient.class, id);
		em.getTransaction().begin();
			em.createQuery("UPDATE Visite v set v.patient=null where v.patient.id=:id").setParameter("id", id).executeUpdate();
			em.remove(patient);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Patient patient) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	
		em.getTransaction().begin();
			patient=em.merge(patient);
			em.remove(patient);
		em.getTransaction().commit();
		em.close();
	}
}
