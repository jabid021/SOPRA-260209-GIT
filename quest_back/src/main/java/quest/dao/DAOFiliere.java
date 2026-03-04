package quest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import quest.model.Filiere;

public class DAOFiliere implements IDAO<Filiere,Integer>{

	@Override
	public Filiere findById(Integer id) {
		Filiere filiere =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from filiere where id=?");
			requete.setInt(1, id);

			ResultSet rs = requete.executeQuery();

			while(rs.next()) {
				filiere = new Filiere(rs.getInt("id"),rs.getString("libelle"),LocalDate.parse(rs.getString("debut")),LocalDate.parse(rs.getString("fin")));
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return filiere;
	}

	@Override
	public List<Filiere> findAll() {
		List<Filiere> filieres=new ArrayList();
		Filiere filiere =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from filiere");

			ResultSet rs = requete.executeQuery();

			while(rs.next()) {
				filiere = new Filiere(rs.getInt("id"),rs.getString("libelle"),LocalDate.parse(rs.getString("debut")),LocalDate.parse(rs.getString("fin")));
				filieres.add(filiere);
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return filieres;
	}

	@Override
	public Filiere insert(Filiere filiere) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("INSERT INTO filiere (libelle,debut,fin) VALUES (?,?,?)");
			requete.setString(1, filiere.getLibelle());
			requete.setString(2, filiere.getDebut().toString());
			requete.setString(3, filiere.getFin().toString());
			requete.executeUpdate();
			
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Filiere update(Filiere filiere) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("UPDATE filiere set libelle=?,debut=?,fin=? where id=?");
			requete.setString(1, filiere.getLibelle());
			requete.setString(2, filiere.getDebut().toString());
			requete.setString(3, filiere.getFin().toString());
			requete.setInt(4,filiere.getId());
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

			PreparedStatement requete = conn.prepareStatement("DELETE from filiere where id=?");
			requete.setInt(1,id);
			
			requete.executeUpdate();
			
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
