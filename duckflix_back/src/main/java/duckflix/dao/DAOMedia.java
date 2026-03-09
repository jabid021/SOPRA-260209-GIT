package duckflix.dao;

import java.util.List;

import duckflix.context.Singleton;
import duckflix.model.Media;
import jakarta.persistence.EntityManager;

public class DAOMedia implements IDAOMedia{

	@Override
	public Media findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Media media = em.find(Media.class, id); 
		em.close();
		return media;
	}

	@Override
	public List<Media> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Media> medias = em.createQuery("from Media").getResultList();
		em.close();
		return medias;
	}

	@Override
	public Media save(Media media) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			media=em.merge(media);
		em.getTransaction().commit();
		em.close();
		return media;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Media media = em.find(Media.class, id);
		em.getTransaction().begin();
			em.remove(media);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Media media) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	
		em.getTransaction().begin();
			media=em.merge(media);
			em.remove(media);
		em.getTransaction().commit();
		em.close();
	}

}
