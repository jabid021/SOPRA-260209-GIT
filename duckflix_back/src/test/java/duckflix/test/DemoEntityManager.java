package duckflix.test;

import java.util.List;

import duckflix.context.Singleton;
import duckflix.dao.IDAOCompte;
import duckflix.dao.IDAOEpisode;
import duckflix.dao.IDAOSaison;
import duckflix.model.Episode;
import duckflix.model.Saison;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class DemoEntityManager {

	public static void main(String[] args) {
	
		//Par defaut, dans toutes les entity, les liens en XToMany sont en mode "Lazy"
		//JPA ne charge jamais par defaut les Liens en ToMany, le chargement est "Lazy" (!= Eager)
		
		//JPA ne chargera jamais vos liste, c'est à vous de changer le mode (ON NE FAIT JAMAIS CA)
		//OU creer une fonction find...With... ou on fera nous meme un JOIN FETCH sur la liste en question
		
		
		//	=>SELECT x From Class x JOIN FETCH x.liste
		// JOIN permet de pointer sur la liste (pour faire du where dessus en general
		// => SELECT s FROM Saison s JOIN s.episodes e WHERE e.duree > 25 => retourne toutes les saisons ayant un episode de + de 25min (La liste des episodes n'est pas return avec la / les Saisons
		// => SELECT s FROM Saison s JOIN FETCH => Ca vous return  les Saisons avec leur liste d'episodes  dans le main (ne return pas les saisons SANS episode)
		// => SELECT s FROM Saison s LEFT JOIN FETCH => Ca vous return les Saisons avec leur liste d'episodes dans le main (+ les saisons n'ayant PAS d'episodes)
		
		
		IDAOCompte daoC = Singleton.getInstance().getDaoCompte();
		IDAOEpisode daoE = Singleton.getInstance().getDaoEpisode();
		IDAOSaison daoSaison = Singleton.getInstance().getDaoSaison();
		
		
		
		List<Saison> allSaisons = daoSaison.findAllWithEpisodes();
		
		System.out.println("---------Liste des saisons----------------");
		
		for(Saison s : allSaisons) 
		{
			System.out.println(s);
			System.out.println("Voici la liste des episodes de la saison "+s.getId());
			System.out.println(s.getEpisodes());
		}
		
		System.out.println("--------- FIN Liste des saisons----------------");
		
		System.out.println(daoC.findAll());
		
		
		for(Episode e : daoE.findAllByDureeLowerThan(90)) 
		{
			System.out.println(e);
		}
		


		System.out.println(daoC.findByLoginAndPassword("toto", "toto"));
		
		
		
		//persist(x) => x est managed
			//persist un objet x sans id => insert 
			//persist un objet x avec un id => refusé
		
		//Object copie = merge(x) => x n'est pas managed, copie lui est managed
			//merge un objet x sans id => insert
			//merge un objet x avec un id => update
		
		//find(Classe.class,id) => return un objet managed
		//em.createQuery(...) => return une liste d'objet managed
		//em.remove(Object x) => x doit etre managed 
		
		
		//dans les requetes "createQuery", on ecrit pas en SQL, mais en JPQL
		
	
		
		
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		
		String saisie = "A";
		Query query = em.createQuery("SELECT e from Episode e where e.titre like :recherche");
		query.setParameter("recherche", "%"+saisie+"%");
		
	
		
		//Si la query doit retourner plusieurs Episode, on l'execute via .getResultList() => [] (empty), soit un tableau avec les episodes
		List<Episode> episodes = query.getResultList();
		
		List<Episode> episodesV2 = em
				.createQuery("SELECT e from Episode e where e.titre like :recherche")
				.setParameter("recherche", "%"+saisie+"%")
				.getResultList();
		
		//Si la query doit retourner UN seul element, on l'executevia .getSingleResult() => 
		//Si aucun element => Exception
		//Si un seul element => Ok
		//Si plusieurs elements => Exception
	//	Episode episode = (Episode) query.getSingleResult();
		
		//Query query = em.createQuery("from Episode");
				
		query = em.createQuery("SELECT e.titre from Episode e where e.titre like :recherche");
		query.setParameter("recherche", "%"+saisie+"%");
		
		List<String> titres = query.getResultList();
		em.close();
		
		
		
		
		Singleton.getInstance().getEmf().close();
	
	}

}
