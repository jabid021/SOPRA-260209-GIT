package duckflix.dao;

import java.util.List;

import duckflix.model.Compte;
import duckflix.model.Utilisateur;

public interface IDAOCompte extends IDAO<Compte,Integer> {

	public Compte findByLoginAndPassword(String login,String password);
	
	public List<Utilisateur> findAllUtilisateur();
	public List<Utilisateur> findAllUtilisateurWithWatchList();
}
