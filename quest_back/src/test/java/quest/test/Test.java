package quest.test;

public class Test {

	/*static DAOFiliere daoFiliere = new DAOFiliere();
	static DAOMatiere daoMatiere = new DAOMatiere();
	static DAOOrdinateur daoOrdinateur = new DAOOrdinateur();
	static DAOPersonne daoPersonne = new DAOPersonne();
	static DAOModule daoModule = new DAOModule();

	public static void testDAOFiliere() 
	{
		Filiere filiere1 = new Filiere("SOPRA-JAVA-BIS-260209",LocalDate.parse("2027-02-09"),LocalDate.parse("2027-05-12"));

		//--------TEST DAOFiliere------------------//
		System.out.println(daoFiliere.findAll());

		System.out.println(daoFiliere.findById(1));
		System.out.println(daoFiliere.findById(23));

		//daoFiliere.insert(filiere1);

		filiere1.setId(2);
		//daoFiliere.update(filiere1);
		//daoFiliere.deleteById(3);
	}


	public static void testDAOMatiere() 
	{
		Matiere matiere1 = new Matiere("Spring");
		Matiere matiere2 = new Matiere("UML");
		Matiere matiere3 = new Matiere("Angular");
		
		//--------TEST DAOMatiere------------------//
		System.out.println(daoMatiere.findAll());
		System.out.println(daoMatiere.findById(1));
		System.out.println(daoMatiere.findById(15));
		daoMatiere.insert(matiere3);
		System.out.println(matiere3);
		
		matiere3.setLibelle("un nouveau libelle");
		daoMatiere.update(matiere3);
		/*Matiere m = daoMatiere.findById(5);
		m.setLibelle("AngularJS");
		daoMatiere.update(m);
		
		System.out.println(daoMatiere.findAll());
		
		daoMatiere.deleteById(5);
	}
	
	
	public static void testDAOModule()
	{
		//--------TEST DAOModule------------------//
				//Pour l'insert / update module, il faut obligatoirement avoir un id dans sa filiere et dans sa matiere
		Matiere matiere1 = new Matiere(1,"Java Objet");
		Matiere matiere2 = new Matiere(2,"SQL");
		Filiere filiere1 = new Filiere(2,"SOPRA-JAVA-260209",LocalDate.parse("2027-02-09"),LocalDate.parse("2027-05-12"));

		
		Module module1 = new Module(7485,LocalDate.parse("2026-02-09"),LocalDate.parse("2026-02-12"),filiere1 ,matiere1);
		Module module2 = new Module(1489,null,null,filiere1 ,matiere2);

		System.out.println(daoModule.findAll());
		
		System.out.println(daoModule.findById(1));
		System.out.println(daoModule.findById(12));
		//daoModule.insert(module2);
		Module m = daoModule.findById(1);
		m.setQuest(1111);
		daoModule.update(m);
		
		System.out.println(daoModule.findAll());
		daoModule.deleteById(3);
		System.out.println(daoModule.findAll());
	}
	
	public static void testDAOOrdinateur() 
	{
		Filiere filiere1 = new Filiere("SOPRA-JAVA-BIS-260209",LocalDate.parse("2027-02-09"),LocalDate.parse("2027-05-12"));
		Stagiaire stagiaire1 = new Stagiaire(3,"stagiaire1","stagiaire1","Doe","John",Genre.Homme,"john@gmail.co","6","rue rougemont","75009","Paris",filiere1);


		Ordinateur ordinateur1 = new Ordinateur("--Asus",8);
		Ordinateur ordinateur2 = new Ordinateur("--Apple",16);
		ordinateur1.setUtilisateur(stagiaire1);
		
		daoOrdinateur.insert(ordinateur1);
		daoOrdinateur.insert(ordinateur2);
		
		for(Ordinateur o : daoOrdinateur.findAll()) 
		{
			System.out.println(o);
		}
		
		Ordinateur ox = daoOrdinateur.findById(1);
		System.out.println("Ordinateur id 1 : "+ox);
		System.out.println("Ordinateur id 100 : "+daoOrdinateur.findById(100));
		ox.setRam(100);
		daoOrdinateur.update(ox);
		
		System.out.println("Ordinateur id 1 : "+daoOrdinateur.findById(1));
		
		daoOrdinateur.deleteById(1);
	}
	
	public static void testDAOPersonne() 
	{
		
		Filiere filiere1 = new Filiere(2,"SOPRA-JAVA-BIS-260209",LocalDate.parse("2027-02-09"),LocalDate.parse("2027-05-12"));

		Stagiaire stagiaire1 = new Stagiaire("stagiaire1","stagiaire1","Doe","John",Genre.Homme,"john@gmail.co","6","rue rougemont","75009","Paris",filiere1);
		Stagiaire stagiaire2 = new Stagiaire("stagiairex","stagiairex","Doe","Jane",Genre.Femme,"jane@gmail.co","161","avenue de verdun","92400","Ivry sur seine",filiere1);


		Formateur formateur1 = new Formateur("formateur1","formateur1","Abid","Jordan",Genre.Homme,true);
		Formateur formateur2 = new Formateur("formateurx","formateurx","Perrouault","Jeremy",Genre.Homme, false);

		//daoPersonne.insert(formateur1);
		//daoPersonne.insert(formateur2);
		daoPersonne.insert(stagiaire1);
		daoPersonne.insert(stagiaire2);
		
		/*System.out.println("Liste des personnes :");
		for(Personne p : daoPersonne.findAll()) 
		{
			System.out.println(p);
		}
		
		System.out.println("\n--------Liste des Stagiaires :");
		for(Personne p : daoPersonne.findAllStagiaire()) 
		{
			System.out.println(p);
		}
		
		System.out.println("\n--------Liste des Formateurs :");
		for(Personne p : daoPersonne.findAllFormateur()) 
		{
			System.out.println(p);
		}
		
		System.out.println(daoPersonne.findByLoginAndPassword("formateurx", "formateurx"));
		System.out.println(daoPersonne.findByLoginAndPassword("stagiairex", "stagiairex"));
		
		Personne px = daoPersonne.findById(1);
		System.out.println("find personne 1 : "+px);
		
		
		px.setCivilite(Genre.NB);
		daoPersonne.update(px);
		
		daoPersonne.deleteById(4);
		
	}
	public static void main(String[] args) {

		testDAOPersonne();

		
	}
*/
}
