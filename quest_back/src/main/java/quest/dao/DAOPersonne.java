package quest.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quest.context.Singleton;
import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;

public class DAOPersonne implements IDAOPersonne{

	@Override
	public Personne findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Personne personne = em.find(Personne.class, id); 
		em.close();
		return personne;
	}

	@Override
	public List<Personne> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Personne> personnes = em.createQuery("from Personne").getResultList();
		em.close();
		return personnes;
	}

	@Override
	public Personne save(Personne personne) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			personne=em.merge(personne);
		em.getTransaction().commit();
		em.close();
		return personne;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Personne personne = em.find(Personne.class, id);
		em.getTransaction().begin();
			em.remove(personne);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Personne personne) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	
		em.getTransaction().begin();
			personne=em.merge(personne);
			em.remove(personne);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Stagiaire> findAllStagiaire() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Stagiaire> personnes = em.createQuery("from Stagiaire").getResultList();
		em.close();
		return personnes;
	}

	@Override
	public List<Formateur> findAllFormateur() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Formateur> personnes = em.createQuery("from Formateur").getResultList();
		em.close();
		return personnes;
	}
}
