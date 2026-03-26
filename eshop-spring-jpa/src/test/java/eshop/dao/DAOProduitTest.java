package eshop.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import eshop.config.AppConfig;
import eshop.model.Fournisseur;
import eshop.model.Genre;
import eshop.model.Produit;

@SpringJUnitConfig(AppConfig.class)
@Transactional
@Rollback(true)
public class DAOProduitTest {

	
	@Autowired
	IDAOProduit daoProduit;
	
	@Autowired
	IDAOPersonne daoPersonne;
	
	@Test
	@DisplayName("Test inject DAO Produit Spring")
	public void testAutowiredDAO() 
	{
		assertNotNull(daoProduit);
	}
	
	
	@Test
	public void testInsertProduit() 
	{
		//Arrange
		Fournisseur fournisseur = new Fournisseur("Doe","John",Genre.homme,"AJC");
		fournisseur = (Fournisseur) daoPersonne.save(fournisseur);
		Produit produit = new Produit("TestProduit",200,fournisseur);
		Integer id;
		Produit produitBdd;
		
		//Act
		produit=daoProduit.save(produit);
		
		//Assert
		id= produit.getId();
		produitBdd = daoProduit.findById(id);
		
		assertNotNull(id);
		assertNotNull(produitBdd);
		assertEquals("TestProduit",produitBdd.getLibelle());
		assertEquals(200,produitBdd.getPrix());
		assertNotNull(produitBdd.getFournisseur());
		assertEquals(fournisseur.getId(),produitBdd.getFournisseur().getId());
	}
	
	@Test
	public void testFindProduit() 
	{
		Fournisseur fournisseur = new Fournisseur("Doe","John",Genre.homme,"AJC");
		fournisseur = (Fournisseur) daoPersonne.save(fournisseur);
		Produit produit = new Produit("TestProduit",200,fournisseur);
		produit = daoProduit.save(produit);
		Integer idInsert = produit.getId();
		Produit produitBdd;
		
		produitBdd = daoProduit.findById(idInsert);
		
		assertNotNull(produitBdd);
	}
	
	
	@Test
	public void testSupprimerProduitById() 
	{
		Fournisseur fournisseur = new Fournisseur("Doe","John",Genre.homme,"AJC");
		fournisseur = (Fournisseur) daoPersonne.save(fournisseur);
		Produit produit = new Produit("TestProduit",200,fournisseur);
		produit = daoProduit.save(produit);
		Integer idInsert = produit.getId();
		Produit produitBdd;
		
		daoProduit.deleteById(idInsert);
		
		produitBdd=daoProduit.findById(idInsert);
		assertNull(produitBdd);
	}


	@Test
	public void testSupprimerProduit() 
	{
		Fournisseur fournisseur = new Fournisseur("Doe","John",Genre.homme,"AJC");
		fournisseur = (Fournisseur) daoPersonne.save(fournisseur);
		Produit produit = new Produit("TestProduit",200,fournisseur);
		produit = daoProduit.save(produit);
		Integer idInsert = produit.getId();
		Produit produitBdd;
		
		daoProduit.delete(produit);
		
		produitBdd=daoProduit.findById(idInsert);
		assertNull(produitBdd);
	}
	
	
	
}
