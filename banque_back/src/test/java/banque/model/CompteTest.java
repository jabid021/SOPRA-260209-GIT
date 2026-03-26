package banque.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class CompteTest {
	/*
	@BeforeAll
	public static void fonctionFirst() 
	{
		System.out.println("FIRST (une fois)");
	}

	@AfterAll
	public static void fonctionLast() 
	{
		System.out.println("Last");
	}

	@BeforeEach
	public void x() {System.out.println("Avant chaque test");}

	@AfterEach
	public void x() {System.out.println("Apres chaque test");}

	 */

	@Test
	public void testCreationCompteNotNullAndCarteNotNull() 
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
		assertNotNull(s1);
		assertNotNull(s1.getCarte());
		assertEquals(Carte.CB,s1.getCarte());
		assertEquals(200,s1.getSolde());


		assertNotNull(p1);
		assertNotNull(p1.getCarte());
		assertEquals(Carte.CB,p1.getCarte());
		assertEquals(150,p1.getSolde());
		assertEquals(50,p1.getDecouvert());

		assertNotNull(pre1);
		assertNotNull(pre1.getCarte());
		assertEquals(Carte.CB,pre1.getCarte());
		assertEquals(50,pre1.getSolde());


	}
	@Test
	public void testCoutPrixCarte() 
	{
		//Arrange
		Simple s1;
		Simple s2; 
		Simple s3; 

		//Act
		s1 = new Simple(200,Carte.CB);//0€
		s2 = new Simple(200,Carte.Visa);//5€
		s3 = new Simple(200,Carte.Mastercard); //10€

		//Assert
		assertEquals(200,s1.getSolde());
		assertEquals(195,s2.getSolde());
		assertEquals(190,s3.getSolde());

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
		//assertEquals(valueExcepted,laValueReel)
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

	@Test
	public void testRetraitNotOk() 
	{
		//Arrange
		Simple s1 = new Simple(200,Carte.CB);
		//Act
		s1.retrait(250);
		//Assert
		//assertTrue(s1.getSolde()>=0);
		assertEquals(200,s1.getSolde());
	}

	@Test
	public void testDepotOk() 
	{
		//Arrange
		Simple s1 = new Simple(200,Carte.CB);
		//Act
		s1.depot(10);
		//Assert
		assertEquals(205,s1.getSolde());
	}

	@Test
	public void testDepotNotOk() 
	{
		//Arrange
		Simple s1 = new Simple(1,Carte.CB);
		//Act
		s1.depot(2);
		//Assert
		assertEquals(1,s1.getSolde());
	}

	@Test
	public void testTransfertOk() 
	{
		//Arrange
		Simple s1 = new Simple(200,Carte.CB);
		Simple s2 = new Simple(200,Carte.CB);
		//Act
		s1.transfert(10,s2);
		//Assert
		assertEquals(185,s1.getSolde());
		assertEquals(205,s2.getSolde());
	}

	@Test
	public void testTransfertNotOk() 
	{
		//Arrange
		Simple s1 = new Simple(200,Carte.CB);
		Simple s2 = new Simple(200,Carte.CB);
		//Act
		s1.transfert(250,s2);
		//Assert
		assertEquals(200,s1.getSolde());
		assertEquals(200,s2.getSolde());
	}



}
