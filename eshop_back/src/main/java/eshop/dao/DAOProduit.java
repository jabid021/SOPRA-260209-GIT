package eshop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import eshop.model.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class DAOProduit implements IDAOProduit{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Produit findById(Integer id) {
		Produit produit = em.find(Produit.class, id); 
		em.close();
		return produit;
	}

	@Override
	public List<Produit> findAll() {
		List<Produit> produits = em.createQuery("from Produit").getResultList();
		em.close();
		return produits;
	}

	@Override
	public Produit save(Produit produit) {
		em.getTransaction().begin();
			produit=em.merge(produit);
		em.getTransaction().commit();
		em.close();
		return produit;
	}

	@Override
	public void deleteById(Integer id) {
		Produit produit = em.find(Produit.class, id);
		em.getTransaction().begin();
			em.remove(produit);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Produit produit) {
		em.getTransaction().begin();
			produit=em.merge(produit);
			em.remove(produit);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Produit> findByLibLike(String lib) {
		List<Produit> produits = em.createQuery("SELECT p from Produit p where p.libelle like :libelle")
				.setParameter("libelle", "%"+lib+"%")
				.getResultList();
		em.close();
		return produits;
	}

	@Override
	public Produit findByIdWithVentes(Integer idProduit) {
		Produit produit = null;
		try {
			produit = em.createQuery("SELECT p from Produit p JOIN FETCH p.ventes where p.id=:id",Produit.class)
				.setParameter("id",idProduit)
				.getSingleResult();
		}
		catch(Exception e) {e.printStackTrace();}
		em.close();
		return produit;
	}
}
