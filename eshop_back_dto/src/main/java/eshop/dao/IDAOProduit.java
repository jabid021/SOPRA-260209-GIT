package eshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eshop.model.Produit;

public interface IDAOProduit extends JpaRepository<Produit,Integer> {

	@Query("Select p from Produit p where p.libelle like :recherche")
	public List<Produit> findByLibLike(@Param("recherche") String lib);
	
	@Query("Select p from Produit p where p.libelle = :recherche")
	public List<Produit> findParLib(@Param("recherche") String lib);
	
	public List<Produit> findByLibelleContaining(@Param("recherche") String lib);
	
	public List<Produit> findByLibelle(@Param("recherche") String lib);
	
	public List<Produit> findByPrixBetween(double min,double max);
	
	public List<Produit> findByPrixLessThan(double prix);
	
	@Query("SELECT p from Produit p JOIN FETCH p.ventes where p.id=:id")
	public Produit findByIdWithVentes(@Param("id") Integer idProduit);
	
	
	
}
