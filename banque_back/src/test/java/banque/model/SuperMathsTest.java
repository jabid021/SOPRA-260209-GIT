package banque.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SuperMathsTest {

	
	@BeforeAll
	public static void avantTest() 
	{
		System.out.println("Debut des test SuperMathsTest");
	}
	
	@Test
	public void testSuperMathInit() 
	{
		SuperMaths s;
		
		s = new SuperMaths();
		
		assertNotNull(s);
	}
	
	@Test
	public void testAdd() 
	{
		//Arrange
		SuperMaths s = new SuperMaths();
		int a=1;
		int b=5;
		int resultat;
		//Act
		resultat=s.additionner(a, b);
		
		//Assert
		assertEquals(6, resultat);
	}
	
	
	@Test
	public void testMinus() 
	{
		//Arrange
		SuperMaths s = new SuperMaths();
		int a=6;
		int b=5;
		int resultat;
		//Act
		resultat=s.soustraire(a, b);
		
		//Assert
		assertNotEquals(0, resultat);
		
		
	}
	
	
	
}
