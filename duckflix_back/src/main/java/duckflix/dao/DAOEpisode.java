package duckflix.dao;

import java.util.List;

import duckflix.context.Singleton;
import duckflix.model.Episode;
import jakarta.persistence.EntityManager;

public class DAOEpisode implements IDAOEpisode{

	@Override
	public Episode findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Episode episode = em.find(Episode.class, id); 
		em.close();
		return episode;
	}

	@Override
	public List<Episode> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Episode> episodes = em.createQuery("from Episode").getResultList();
		em.close();
		return episodes;
	}

	@Override
	public Episode save(Episode episode) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			episode=em.merge(episode);
		em.getTransaction().commit();
		em.close();
		return episode;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Episode episode = em.find(Episode.class, id);
		em.getTransaction().begin();
			em.remove(episode);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Episode episode) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	
		em.getTransaction().begin();
			episode=em.merge(episode);
			em.remove(episode);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Episode> findAllByDureeLowerThan(int duree) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Episode> episodes = em.createQuery("SELECT e from Episode e where e.duree < "+duree).getResultList();
		em.close();
		return episodes;
	}

}
