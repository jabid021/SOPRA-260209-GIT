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
		return em.find(Achat.class, id); 
	}

	@Override
	public List<Achat> findAll() {
		return em.createQuery("from Achat").getResultList();
	}

	@Override
	public Achat save(Achat achat) {
		return em.merge(achat);
	}

	@Override
	public void deleteById(Integer id) {
		Achat achat = em.find(Achat.class, id);
		em.remove(achat);
	}

	@Override
	public void delete(Achat achat) {
		achat=em.merge(achat);
		em.remove(achat);
	}
}
