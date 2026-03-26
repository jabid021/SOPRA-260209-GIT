package eshop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eshop.model.Client;
import eshop.model.Fournisseur;
import eshop.model.Personne;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class DAOPersonne implements IDAOPersonne{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Personne findById(Integer id) {
		return em.find(Personne.class, id); 
	}

	@Override
	public List<Personne> findAll() {
		return em.createQuery("from Personne").getResultList();
	}

	@Override
	public Personne save(Personne personne) {
		return em.merge(personne);
	}

	@Override
	public void deleteById(Integer id) {
		Personne personne = em.find(Personne.class, id);
		em.remove(personne);
	}

	@Override
	public void delete(Personne personne) {
		personne=em.merge(personne);
		em.remove(personne);
	}

	@Override
	public List<Fournisseur> findAllFournisseur() {
		return em.createQuery("from Fournisseur").getResultList();
	}

	@Override
	public List<Client> findAllClient() {
		return em.createQuery("from Client").getResultList();
	}

	@Override
	public Client findByIdWithAchats(Integer idClient) {
		Client client = null;
		try {
		client = em.createQuery("SELECT c from Client c LEFT JOIN FETCH c.achats where c.id=:id",Client.class)
				.setParameter("id",idClient)
				.getSingleResult();
		}
		catch(Exception e) {e.printStackTrace();}
		return client;
	}

	@Override
	public Fournisseur findByIdWithStock(Integer idFournisseur) {
		Fournisseur fournisseur = null;
		try {
		fournisseur = em.createQuery("SELECT f from Fournisseur f JOIN FETCH f.stock where f.id=:id",Fournisseur.class)
				.setParameter("id",idFournisseur)
				.getSingleResult();
		}
		catch(Exception e) {e.printStackTrace();}
		return fournisseur;
	}
}
