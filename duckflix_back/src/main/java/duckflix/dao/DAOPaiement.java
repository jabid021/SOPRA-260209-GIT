package duckflix.dao;

import java.util.List;

import duckflix.context.Singleton;
import duckflix.model.Carte;
import duckflix.model.Paiement;
import duckflix.model.Paypal;
import duckflix.model.Virement;
import jakarta.persistence.EntityManager;

public class DAOPaiement implements IDAOPaiement{

	@Override
	public Paiement findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Paiement paiement = em.find(Paiement.class, id); 
		em.close();
		return paiement;
	}

	@Override
	public List<Paiement> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Paiement> paiements = em.createQuery("from Paiement").getResultList();
		em.close();
		return paiements;
	}

	@Override
	public Paiement save(Paiement paiement) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			paiement=em.merge(paiement);
		em.getTransaction().commit();
		em.close();
		return paiement;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Paiement paiement = em.find(Paiement.class, id);
		em.getTransaction().begin();
			em.remove(paiement);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Paiement paiement) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	
		em.getTransaction().begin();
			paiement=em.merge(paiement);
			em.remove(paiement);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Carte> findAllCarte() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Carte> paiements = em.createQuery("from Carte").getResultList();
		em.close();
		return paiements;
	}

	@Override
	public List<Paypal> findAllPaypal() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Paypal> paiements = em.createQuery("from Paypal").getResultList();
		em.close();
		return paiements;
	}

	@Override
	public List<Virement> findAllVirement() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Virement> paiements = em.createQuery("from Virement").getResultList();
		em.close();
		return paiements;
	}

}
