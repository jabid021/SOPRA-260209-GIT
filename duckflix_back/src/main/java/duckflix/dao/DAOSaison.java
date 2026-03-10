package duckflix.dao;

import java.util.List;

import duckflix.context.Singleton;
import duckflix.model.Saison;
import jakarta.persistence.EntityManager;

public class DAOSaison implements IDAOSaison{

	@Override
	public Saison findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Saison saison = em.find(Saison.class, id); 
		em.close();
		return saison;
	}

	@Override
	public List<Saison> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Saison> saisons = em.createQuery("from Saison").getResultList();
		em.close();
		return saisons;
	}

	@Override
	public Saison save(Saison saison) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			saison=em.merge(saison);
		em.getTransaction().commit();
		em.close();
		return saison;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Saison saison = em.find(Saison.class, id);
		em.getTransaction().begin();
			em.remove(saison);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Saison saison) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	
		em.getTransaction().begin();
			saison=em.merge(saison);
			em.remove(saison);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Saison> findAllWithEpisodes() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Saison> saisons = em.createQuery("SELECT s from Saison s JOIN FETCH s.episodes").getResultList();
		em.close();
		return saisons;
	}

	@Override
	public Saison findByIdWithEpisodes(Integer id) {
		Saison saison = null;
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		try {
		saison = em.createQuery("SELECT s from Saison s JOIN FETCH s.episodes where s.id=:id",Saison.class)
				.setParameter("id", id)
				.getSingleResult();
		}catch(Exception e) {e.printStackTrace();}
		em.close();
		return saison;
	}

}
