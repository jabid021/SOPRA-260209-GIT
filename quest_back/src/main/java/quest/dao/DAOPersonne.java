package quest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import quest.model.Filiere;
import quest.model.Formateur;
import quest.model.Genre;
import quest.model.Personne;
import quest.model.Stagiaire;

public class DAOPersonne implements IDAO<Personne,Integer> {

	DAOFiliere daoFiliere = new DAOFiliere();
	@Override
	public Personne findById(Integer id) {
		Personne personne =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from personne where id=?");
			requete.setInt(1, id);

			ResultSet rs = requete.executeQuery();

			while(rs.next()) {
				if(rs.getString("type_personne").equals("Formateur")) 
				{
					personne = new Formateur(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),Genre.valueOf(rs.getString("civilite")),rs.getBoolean("admin"));
				}
				else if(rs.getString("type_personne").equals("Stagiaire")) 
				{
					Filiere filiere = daoFiliere.findById(rs.getInt("filiere"));
					personne = new Stagiaire(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),Genre.valueOf(rs.getString("civilite")),rs.getString("email"),rs.getString("numero"),rs.getString("voie"),rs.getString("ville"),rs.getString("cp"),filiere);
				}
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return personne;
	}	

	@Override
	public List<Personne> findAll() {
		List<Personne> personnes = new ArrayList();
		Personne personne =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from personne");

			ResultSet rs = requete.executeQuery();

			while(rs.next()) {
				if(rs.getString("type_personne").equals("Formateur")) 
				{
					personne = new Formateur(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),Genre.valueOf(rs.getString("civilite")),rs.getBoolean("admin"));
				}
				else if(rs.getString("type_personne").equals("Stagiaire")) 
				{
					Filiere filiere = daoFiliere.findById(rs.getInt("filiere"));
					personne = new Stagiaire(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),Genre.valueOf(rs.getString("civilite")),rs.getString("email"),rs.getString("numero"),rs.getString("voie"),rs.getString("ville"),rs.getString("cp"),filiere);
				}
				personnes.add(personne);
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return personnes;
	}

	@Override
	public Personne insert(Personne personne) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("INSERT INTO personne (login,password,nom,prenom,civilite,admin,email,numero,voie,ville,cp,filiere,type_personne) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			//Si la personne est un stagiaire :
			if(personne instanceof Stagiaire) 
			{
				Stagiaire stagiaire = (Stagiaire) personne;
				
				requete.setString(1, stagiaire.getLogin());
				requete.setString(2, stagiaire.getPassword());
				requete.setString(3, stagiaire.getNom());
				requete.setString(4, stagiaire.getPrenom());
				requete.setString(5, stagiaire.getCivilite().toString());
				requete.setObject(6, null);
				requete.setString(7, stagiaire.getEmail());
				requete.setString(8, stagiaire.getAdresse().getNumero());
				requete.setString(9, stagiaire.getAdresse().getVoie());
				requete.setString(10, stagiaire.getAdresse().getVille());
				requete.setString(11, stagiaire.getAdresse().getCp());
				requete.setInt(12, stagiaire.getFiliere().getId());
				requete.setString(13, "Stagiaire");
			}
			else if(personne instanceof Formateur) 
			{
				requete.setString(1, personne.getLogin());
				requete.setString(2, personne.getPassword());
				requete.setString(3, personne.getNom());
				requete.setString(4, personne.getPrenom());
				requete.setString(5, personne.getCivilite().toString());
				requete.setBoolean(6, ((Formateur) personne).isAdmin());
				requete.setString(7, null);
				requete.setString(8, null);
				requete.setString(9, null);
				requete.setString(10, null);
				requete.setString(11, null);
				requete.setObject(12, null);
				requete.setString(13, "Formateur");
			}
		
			requete.executeUpdate();
		
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Personne update(Personne personne) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("UPDATE personne set login=?,password=?,nom=?,prenom=?,civilite=?,admin=?,email=?,numero=?,voie=?,ville=?,cp=?,filiere=?,type_personne=? where id=?");
			
			//Si la personne est un stagiaire :
			if(personne instanceof Stagiaire) 
			{
				Stagiaire stagiaire = (Stagiaire) personne;
				
				requete.setString(1, stagiaire.getLogin());
				requete.setString(2, stagiaire.getPassword());
				requete.setString(3, stagiaire.getNom());
				requete.setString(4, stagiaire.getPrenom());
				requete.setString(5, stagiaire.getCivilite().toString());
				requete.setObject(6, null);
				requete.setString(7, stagiaire.getEmail());
				requete.setString(8, stagiaire.getAdresse().getNumero());
				requete.setString(9, stagiaire.getAdresse().getVoie());
				requete.setString(10, stagiaire.getAdresse().getVille());
				requete.setString(11, stagiaire.getAdresse().getCp());
				requete.setInt(12, stagiaire.getFiliere().getId());
				requete.setString(13, "Stagiaire");
				requete.setInt(14, stagiaire.getId());
			}
			else if(personne instanceof Formateur) 
			{
				requete.setString(1, personne.getLogin());
				requete.setString(2, personne.getPassword());
				requete.setString(3, personne.getNom());
				requete.setString(4, personne.getPrenom());
				requete.setString(5, personne.getCivilite().toString());
				requete.setBoolean(6, ((Formateur) personne).isAdmin());
				requete.setString(7, null);
				requete.setString(8, null);
				requete.setString(9, null);
				requete.setString(10, null);
				requete.setString(11, null);
				requete.setObject(12, null);
				requete.setString(13, "Formateur");
				requete.setInt(14, personne.getId());
			}
		
			requete.executeUpdate();
		
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("DELETE from personne where id=?");
			requete.setInt(1,id);
			
			requete.executeUpdate();
			
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Personne findByLoginAndPassword(String login,String password) 
	{
		Personne personne =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from personne where login=? and password=?");
			requete.setString(1, login);
			requete.setString(2, password);

			ResultSet rs = requete.executeQuery();

			while(rs.next()) {
				if(rs.getString("type_personne").equals("Formateur")) 
				{
					personne = new Formateur(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),Genre.valueOf(rs.getString("civilite")),rs.getBoolean("admin"));
				}
				else if(rs.getString("type_personne").equals("Stagiaire")) 
				{
					Filiere filiere = daoFiliere.findById(rs.getInt("filiere"));
					personne = new Stagiaire(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),Genre.valueOf(rs.getString("civilite")),rs.getString("email"),rs.getString("numero"),rs.getString("voie"),rs.getString("ville"),rs.getString("cp"),filiere);
				}
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return personne;
	}


	public List<Formateur> findAllFormateur() {
		List<Formateur> formateurs = new ArrayList();
		Formateur formateur =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from personne where type_personne='Formateur'");

			ResultSet rs = requete.executeQuery();

			while(rs.next()) {
				formateur = new Formateur(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),Genre.valueOf(rs.getString("civilite")),rs.getBoolean("admin"));
				formateurs.add(formateur);
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return formateurs;
	}
	
	public List<Stagiaire> findAllStagiaire() {
		List<Stagiaire> stagiaires = new ArrayList();
		Stagiaire stagiaire =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from personne where type_personne=?");
			requete.setString(1, "Stagiaire");

			ResultSet rs = requete.executeQuery();

			while(rs.next()) {
				
				Filiere filiere = daoFiliere.findById(rs.getInt("filiere"));
				stagiaire = new Stagiaire(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),Genre.valueOf(rs.getString("civilite")),rs.getString("email"),rs.getString("numero"),rs.getString("voie"),rs.getString("ville"),rs.getString("cp"),filiere);

				stagiaires.add(stagiaire);
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stagiaires;
	}
}
