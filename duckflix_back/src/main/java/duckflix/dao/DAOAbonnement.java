package duckflix.dao;

import java.util.List;

import duckflix.context.Singleton;
import duckflix.model.Abonnement;
import jakarta.persistence.EntityManager;

public class DAOAbonnement implements IDAOAbonnement{

	@Override
	public Abonnement findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Abonnement abonnement = em.find(Abonnement.class, id); 
		em.close();
		return abonnement;
	}

	@Override
	public List<Abonnement> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Abonnement> abonnements = em.createQuery("from Abonnement").getResultList();
		em.close();
		return abonnements;
	}

	@Override
	public Abonnement save(Abonnement abonnement) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			abonnement=em.merge(abonnement);
		em.getTransaction().commit();
		em.close();
		return abonnement;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Abonnement abonnement = em.find(Abonnement.class, id);
		em.getTransaction().begin();
			em.remove(abonnement);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Abonnement abonnement) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	
		em.getTransaction().begin();
			abonnement=em.merge(abonnement);
			em.remove(abonnement);
		em.getTransaction().commit();
		em.close();
	}

}
