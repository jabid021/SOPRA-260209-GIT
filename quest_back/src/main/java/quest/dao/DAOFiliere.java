package quest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import quest.model.Filiere;

@Repository
@Transactional
public class DAOFiliere implements IDAOFiliere{

	@PersistenceContext
	private EntityManager em;

	@Override
	public Filiere findById(Integer id) {
		return em.find(Filiere.class, id); 
	}

	@Override
	public List<Filiere> findAll() {
		return em.createQuery("from Filiere").getResultList();
	}

	@Override
	public Filiere save(Filiere filiere) {
		return em.merge(filiere);
	}

	@Override
	public void deleteById(Integer id) {
		Filiere filiere = em.find(Filiere.class, id);
		em.remove(filiere);
	}

	@Override
	public void delete(Filiere filiere) {
		filiere=em.merge(filiere);
		em.remove(filiere);
	}

	@Override
	public Filiere findByIdWithEleves(Integer idFiliere) {
		Filiere filiere =null;
		try {
			filiere = em.createQuery("SELECT f from Filiere f LEFT JOIN FETCH f.eleves where f.id=:id",Filiere.class).setParameter("id", idFiliere).getSingleResult();
		}catch(Exception e) {e.printStackTrace();};
		return filiere;
	}
}
