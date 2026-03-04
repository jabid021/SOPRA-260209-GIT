package hopital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hopital.model.Compte;
import hopital.model.Medecin;
import hopital.model.Secretaire;

public class DAOCompteJDBC implements IDAOCompte {

	@Override
	public Compte findById(Integer id) {
		Compte compte = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from compte where id=?");
			requete.setInt(1, id);
			ResultSet rs = requete.executeQuery();


			while(rs.next()) {
				if(rs.getString("type_compte").equals("Medecin")) 
				{
					compte = new Medecin(rs.getInt("id"),rs.getString("login"),rs.getString("password"));
				}
				else if(rs.getString("type_compte").equals("Secretaire")) 
				{
					compte = new Secretaire(rs.getInt("id"),rs.getString("login"),rs.getString("password"));
				}
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return compte;
	}

	@Override
	public List<Compte> findAll() {
		Compte compte = null;
		List<Compte> comptes = new ArrayList();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from compte");
			ResultSet rs = requete.executeQuery();


			while(rs.next()) {
				if(rs.getString("type_compte").equals("Medecin")) 
				{
					compte = new Medecin(rs.getInt("id"),rs.getString("login"),rs.getString("password"));
				}
				else if(rs.getString("type_compte").equals("Secretaire")) 
				{
					compte = new Secretaire(rs.getInt("id"),rs.getString("login"),rs.getString("password"));
				}
				comptes.add(compte);
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return comptes;
	}

	@Override
	public Compte insert(Compte compte) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("INSERT INTO compte (login,password,type_compte) VALUES (?,?,?)");
			
			requete.setString(1,compte.getLogin());
			requete.setString(2,compte.getPassword());
			requete.setString(3,compte.getClass().getSimpleName());
			requete.executeUpdate();
		
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Compte update(Compte compte) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("UPDATE compte set login=?,password=?,type_compte=? where id=?");
			
			if(compte instanceof Medecin) 
			{
				requete.setString(1,compte.getLogin());
				requete.setString(2,compte.getPassword());
				requete.setString(3,"Medecin");
				requete.setInt(4, compte.getId());
			}
			else if(compte instanceof Secretaire) 
			{
				requete.setString(1,compte.getLogin());
				requete.setString(2,compte.getPassword());
				requete.setString(3,"Secretaire");
				requete.setInt(4, compte.getId());
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

			PreparedStatement requete = conn.prepareStatement("DELETE FROM compte where id=?");
			
			requete.setInt(1, id);
			
			requete.executeUpdate();
		
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Compte findByLoginAndPassword(String login,String password) {
		Compte compte = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from compte where login=? and password=?");
			requete.setString(1,login);
			requete.setString(2, password);
			ResultSet rs = requete.executeQuery();


			while(rs.next()) {
				if(rs.getString("type_compte").equals("Medecin")) 
				{
					compte = new Medecin(rs.getInt("id"),rs.getString("login"),rs.getString("password"));
				}
				else if(rs.getString("type_compte").equals("Secretaire")) 
				{
					compte = new Secretaire(rs.getInt("id"),rs.getString("login"),rs.getString("password"));
				}
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return compte;
	}


}
