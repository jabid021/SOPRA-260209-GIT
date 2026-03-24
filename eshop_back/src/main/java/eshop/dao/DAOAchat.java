package eshop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import eshop.model.Achat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class DAOAchat implements IDAOAchat{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Achat findById(Integer id) {
		Achat achat = em.find(Achat.class, id); 
		em.close();
		return achat;
	}

	@Override
	public List<Achat> findAll() {
		List<Achat> achats = em.createQuery("from Achat").getResultList();
		em.close();
		return achats;
	}

	@Override
	public Achat save(Achat achat) {
		em.getTransaction().begin();
			achat=em.merge(achat);
		em.getTransaction().commit();
		em.close();
		return achat;
	}

	@Override
	public void deleteById(Integer id) {
		Achat achat = em.find(Achat.class, id);
		em.getTransaction().begin();
			em.remove(achat);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Achat achat) {
		em.getTransaction().begin();
			achat=em.merge(achat);
			em.remove(achat);
		em.getTransaction().commit();
		em.close();
	}
}
