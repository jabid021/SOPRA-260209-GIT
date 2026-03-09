package duckflix.dao;

import java.util.List;

import duckflix.context.Singleton;
import duckflix.model.Compte;
import jakarta.persistence.EntityManager;

public class DAOCompte implements IDAOCompte{

	@Override
	public Compte findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Compte compte = em.find(Compte.class, id); 
		em.close();
		return compte;
	}

	@Override
	public List<Compte> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Compte> comptes = em.createQuery("from Compte").getResultList();
		em.close();
		return comptes;
	}

	@Override
	public Compte save(Compte compte) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			compte=em.merge(compte);
		em.getTransaction().commit();
		em.close();
		return compte;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Compte compte = em.find(Compte.class, id);
		em.getTransaction().begin();
			em.remove(compte);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Compte compte) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			compte=em.merge(compte);
			em.remove(compte);
		em.getTransaction().commit();
		em.close();
	}

}
