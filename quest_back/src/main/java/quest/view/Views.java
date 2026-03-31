package quest.view;

public class Views {

	public class Common{} // Tous les types differents de NOS objets (int,Integer,double,boolean,String...)

	public class Filiere extends Common{} // Tous les objets toOne de la classe filiere  + tous les attributs common
	
		public class FiliereWithEleves extends Filiere{} //La liste eleves + tous les attributs declarés dans le calque filiere
		public class FiliereWithModules extends Filiere{}
	
	public class Stagiaire extends Common{} // Tous les objets toOne de la classe stagiaire  + tous les attributs common
	
	public class Matiere extends Common{} // Tous les objets toOne de la classe matiere  + tous les attributs common
		public class MatiereWithModules extends Matiere{}
		
	public class Formateur extends Common{} // Tous les objets toOne de la classe formateur  + tous les attributs common
	
	public class Ordinateur extends Common{} // Tous les objets toOne de la classe ordinateur  + tous les attributs common
}
