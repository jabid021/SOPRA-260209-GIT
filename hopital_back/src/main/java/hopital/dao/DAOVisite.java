package hopital.dao;

import java.time.LocalDate;
import java.util.List;

import hopital.context.Singleton;
import hopital.model.Visite;
import jakarta.persistence.EntityManager;

public class DAOVisite implements IDAOVisite{

	@Override
	public Visite findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Visite visite = em.find(Visite.class, id); 
		em.close();
		return visite;
	}

	@Override
	public List<Visite> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Visite> visites = em.createQuery("from Visite").getResultList();
		em.close();
		return visites;
	}

	@Override
	public Visite save(Visite visite) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			visite=em.merge(visite);
		em.getTransaction().commit();
		em.close();
		return visite;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Visite visite = em.find(Visite.class, id);
		em.getTransaction().begin();
			em.remove(visite);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Visite visite) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	
		em.getTransaction().begin();
			visite=em.merge(visite);
			em.remove(visite);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Visite> findByPatientId(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Visite> visites = em.createQuery("SELECT v from Visite v where v.patient.id=:id")
				.setParameter("id", id)
				.getResultList();
		em.close();
		return visites;
	}

	@Override
	public List<Visite> findByDateVisiteBetweenAndMedecinId(LocalDate debut, LocalDate fin, Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Visite> visites = em.createQuery("SELECT v from Visite v where v.dateVisite between :debut and :fin and v.medecin.id=:id")
				.setParameter("debut", debut)
				.setParameter("fin", fin)
				.setParameter("id", id)
				.getResultList();
		em.close();
		return visites;
	}

	@Override
	public double sumPrixByDateVisiteBetweenAndMedecinId(LocalDate debut, LocalDate fin, Integer id) {
		double total =0;
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		try {
			total = (double) em.createQuery("SELECT SUM(v.prix) FROM Visite v where v.dateVisite between :debut and :fin and v.medecin.id=:id")
					.setParameter("debut", debut)
					.setParameter("fin", fin)
					.setParameter("id", id)
					.getSingleResult();
		}catch(Exception e) {e.printStackTrace();};
		
		em.close();
		return total;
	}
}
