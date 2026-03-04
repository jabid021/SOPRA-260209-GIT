package quest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import quest.model.Ordinateur;
import quest.model.Stagiaire;

public class DAOOrdinateur implements IDAO<Ordinateur,Integer>{

	static DAOPersonne daoPersonne = new DAOPersonne();
	
	@Override
	public Ordinateur findById(Integer id) {
		Ordinateur ordinateur =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from ordinateur where numero=?");
			requete.setInt(1, id);

			ResultSet rs = requete.executeQuery();

			while(rs.next()) {
				Stagiaire utilisateur = (Stagiaire) daoPersonne.findById(rs.getInt("utilisateur"));
				ordinateur = new Ordinateur(rs.getInt("numero"),rs.getString("marque"),rs.getInt("ram"));
				ordinateur.setUtilisateur(utilisateur);
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordinateur;
	}

	@Override
	public List<Ordinateur> findAll() {
		List<Ordinateur> ordinateurs=new ArrayList();
		Ordinateur ordinateur =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from ordinateur");

			ResultSet rs = requete.executeQuery();

			while(rs.next()) {
				Stagiaire utilisateur = (Stagiaire) daoPersonne.findById(rs.getInt("utilisateur"));
				ordinateur = new Ordinateur(rs.getInt("numero"),rs.getString("marque"),rs.getInt("ram"));
				ordinateur.setUtilisateur(utilisateur);
				ordinateurs.add(ordinateur);
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordinateurs;
	}

	@Override
	public Ordinateur insert(Ordinateur ordinateur) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("INSERT INTO ordinateur (marque,ram,utilisateur) VALUES (?,?,?)");
			requete.setString(1, ordinateur.getMarque());
			requete.setInt(2, ordinateur.getRam());
			if(ordinateur.getUtilisateur()==null) 
			{
				requete.setObject(3, null);
			}
			else 
			{
				requete.setInt(3, ordinateur.getUtilisateur().getId());
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
	public Ordinateur update(Ordinateur ordinateur) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("UPDATE ordinateur set marque=?,ram=?, utilisateur=? where numero=?");
			requete.setString(1, ordinateur.getMarque());
			requete.setInt(2, ordinateur.getRam());
			if(ordinateur.getUtilisateur()==null) 
			{
				requete.setObject(3, null);
			}
			else 
			{
				requete.setInt(3, ordinateur.getUtilisateur().getId());
			}
			requete.setInt(4, ordinateur.getNumero());
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

			PreparedStatement requete = conn.prepareStatement("DELETE from ordinateur where numero=?");
			requete.setInt(1,id);
			
			requete.executeUpdate();
			
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
