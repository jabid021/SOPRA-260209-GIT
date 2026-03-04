package quest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import quest.model.Matiere;

public class DAOMatiere implements IDAO<Matiere,Integer>{

	@Override
	public Matiere findById(Integer id) {
		Matiere matiere =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from matiere where id=?");
			requete.setInt(1, id);

			ResultSet rs = requete.executeQuery();

			while(rs.next()) {
				matiere = new Matiere(rs.getInt("id"),rs.getString("libelle"));
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return matiere;
	}

	@Override
	public List<Matiere> findAll() {
		List<Matiere> matieres=new ArrayList();
		Matiere matiere =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from matiere");

			ResultSet rs = requete.executeQuery();

			while(rs.next()) {
				matiere = new Matiere(rs.getInt("id"),rs.getString("libelle"));
				matieres.add(matiere);
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return matieres;
	}

	@Override
	public Matiere insert(Matiere matiere) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("INSERT INTO matiere (libelle) VALUES (?)",Statement.RETURN_GENERATED_KEYS);
			requete.setString(1, matiere.getLibelle());
			requete.executeUpdate();
		
			//Pour recuper l'id auto-increment (,Statement.RETURN_GENERATED_KEYS apres la requete + les lignes suivantes : )
			ResultSet rs = requete.getGeneratedKeys();
			if(rs.next()) 
			{
				Integer id = rs.getInt(1);
				matiere.setId(id);
			}
			
			
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return matiere;
	}

	@Override
	public Matiere update(Matiere matiere) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("UPDATE matiere set libelle=? where id=?");
			requete.setString(1, matiere.getLibelle());
			requete.setInt(2,matiere.getId());
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

			PreparedStatement requete = conn.prepareStatement("DELETE from matiere where id=?");
			requete.setInt(1,id);
			
			requete.executeUpdate();
			
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
