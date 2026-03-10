package eshop.dao;

import java.util.List;

import eshop.context.Singleton;
import eshop.model.Fournisseur;
import eshop.model.Produit;
import jakarta.persistence.EntityManager;

public class DAOProduit implements IDAOProduit{

	@Override
	public Produit findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Produit produit = em.find(Produit.class, id); 
		em.close();
		return produit;
	}

	@Override
	public List<Produit> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Produit> produits = em.createQuery("from Produit").getResultList();
		em.close();
		return produits;
	}

	@Override
	public Produit save(Produit produit) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			produit=em.merge(produit);
		em.getTransaction().commit();
		em.close();
		return produit;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Produit produit = em.find(Produit.class, id);
		em.getTransaction().begin();
			em.remove(produit);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Produit produit) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	
		em.getTransaction().begin();
			produit=em.merge(produit);
			em.remove(produit);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Produit> findByLibLike(String lib) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		/*List<Produit> produits = em.createNativeQuery("SELECT * from product join person on person.id=product.fournisseur where label like :libelle")
				.setParameter("libelle", "%"+lib+"%")
				.getResultList();*/
		List<Produit> produits = em.createQuery("SELECT p from Produit p where p.libelle like :libelle")
				.setParameter("libelle", "%"+lib+"%")
				.getResultList();
		em.close();
		return produits;
	}

	@Override
	public Produit findByIdWithVentes(Integer idProduit) {
		Produit produit = null;
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	
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
