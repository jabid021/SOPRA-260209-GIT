package eshop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import eshop.model.Client;
import eshop.model.Fournisseur;
import eshop.model.Personne;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class DAOPersonne implements IDAOPersonne{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Personne findById(Integer id) {
		Personne personne = em.find(Personne.class, id); 
		em.close();
		return personne;
	}

	@Override
	public List<Personne> findAll() {
		List<Personne> personnes = em.createQuery("from Personne").getResultList();
		em.close();
		return personnes;
	}

	@Override
	public Personne save(Personne personne) {
		em.getTransaction().begin();
			personne=em.merge(personne);
		em.getTransaction().commit();
		em.close();
		return personne;
	}

	@Override
	public void deleteById(Integer id) {
		Personne personne = em.find(Personne.class, id);
		em.getTransaction().begin();
			em.remove(personne);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Personne personne) {
		em.getTransaction().begin();
			personne=em.merge(personne);
			em.remove(personne);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Fournisseur> findAllFournisseur() {
		List<Fournisseur> fournisseurs = em.createQuery("from Fournisseur").getResultList();
		em.close();
		return fournisseurs;
	}

	@Override
	public List<Client> findAllClient() {
		List<Client> clients = em.createQuery("from Client").getResultList();
		em.close();
		return clients;
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
		em.close();
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
		em.close();
		return fournisseur;
	}
}
