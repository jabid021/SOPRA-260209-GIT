package quest.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quest.context.Singleton;
import quest.model.Filiere;

public class DAOFiliere implements IDAOFiliere{

	@Override
	public Filiere findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Filiere filiere = em.find(Filiere.class, id); 
		em.close();
		return filiere;
	}

	@Override
	public List<Filiere> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Filiere> filieres = em.createQuery("from Filiere").getResultList();
		em.close();
		return filieres;
	}

	@Override
	public Filiere save(Filiere filiere) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			filiere=em.merge(filiere);
		em.getTransaction().commit();
		em.close();
		return filiere;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Filiere filiere = em.find(Filiere.class, id);
		em.getTransaction().begin();
			em.remove(filiere);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Filiere filiere) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	
		em.getTransaction().begin();
			filiere=em.merge(filiere);
			em.remove(filiere);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Filiere findByIdWithEleves(Integer idFiliere) {
		Filiere filiere =null;
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		try {
		filiere = em.createQuery("SELECT f from Filiere f LEFT JOIN FETCH f.eleves where f.id=:id",Filiere.class).setParameter("id", idFiliere).getSingleResult();
		}catch(Exception e) {e.printStackTrace();};
		
		em.close();
		return filiere;
	}
}
