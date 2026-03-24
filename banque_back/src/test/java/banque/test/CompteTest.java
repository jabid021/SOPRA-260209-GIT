package banque.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import banque.model.Carte;
import banque.model.Payant;
import banque.model.Premium;
import banque.model.Simple;

public class CompteTest {

	
	
	public void testCreationCompteNotNullAndCarteNotNull() 
	{
		
	}
	
	public void testCoutPrixCarte() 
	{
		
	}
	
	@Test
	public void testCoutCreation() 
	{
		//Arrange
		Simple s1;
		Payant p1;
		Premium pre1;
		
		//Act		
		s1 = new Simple(200,Carte.CB);
		p1 = new Payant(200,Carte.CB,50);
		pre1 = new Premium(200, Carte.CB);	
		
		//Assert
		assertEquals(200, s1.getSolde());
		assertEquals(150,p1.getSolde());
		assertEquals(50, pre1.getSolde());
	}
	
	@Test
	public void testRetraitOk() 
	{
		//Arrange
		Simple s1 = new Simple(200,Carte.CB);
		//Act
		s1.retrait(50);
		//Assert
		assertEquals(145,s1.getSolde());
	}
	
	public void testRetraitNotOk() 
	{
		
	}
	
	public void testDepotOk() 
	{
		
	}
	
	public void testDepotNotOk() 
	{
		
	}
	
	public void testTransfertOk() 
	{
		
	}
	
	public void testTransfertNotOk() 
	{
		
	}
	
	
	
}
