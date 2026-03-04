package hopital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hopital.context.Singleton;
import hopital.model.Medecin;
import hopital.model.Patient;
import hopital.model.Visite;

public class DAOVisite implements IDAOVisite{

	static IDAOPatient daoPatient = Singleton.getInstance().getDaoPatient();
	static IDAOCompte daoCompte = Singleton.getInstance().getDaoCompte();
	
	@Override
	public Visite findById(Integer id) {
		Visite visite = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from visite where numero=?");
			requete.setInt(1, id);
			ResultSet rs = requete.executeQuery();


			while(rs.next()) {
				Patient patient = daoPatient.findById(rs.getInt("id_patient"));
				Medecin medecin = (Medecin) daoCompte.findById(rs.getInt("id_medecin"));
				visite = new Visite(rs.getInt("numero"),patient,medecin,rs.getInt("salle"),rs.getDouble("prix"),LocalDate.parse(rs.getString("date_visite")));
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return visite;
	}

	@Override
	public List<Visite> findAll() {
		Visite visite = null;
		List<Visite> visites = new ArrayList();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from visite");
			ResultSet rs = requete.executeQuery();


			while(rs.next()) {
				Patient patient = daoPatient.findById(rs.getInt("id_patient"));
				Medecin medecin = (Medecin) daoCompte.findById(rs.getInt("id_medecin"));
				visite = new Visite(rs.getInt("numero"),patient,medecin,rs.getInt("salle"),rs.getDouble("prix"),LocalDate.parse(rs.getString("date_visite")));
				visites.add(visite);
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return visites;
	}

	@Override
	public Visite insert(Visite visite) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("INSERT INTO visite (id_patient,id_medecin,prix,salle,date_visite) VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, visite.getPatient().getId());
			requete.setInt(2, visite.getMedecin().getId());
			requete.setDouble(3, visite.getPrix());
			requete.setInt(4, visite.getSalle());
			requete.setString(5, visite.getDateVisite().toString());
			requete.executeUpdate();
			
			ResultSet rs = requete.getGeneratedKeys();
			if(rs.next()) 
			{
				visite.setNumero(rs.getInt(1));
			}
			
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return visite;
	}

	@Override
	public Visite update(Visite visite) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("UPDATE visite set id_patient=?,id_medecin=? prix=?, salle=?, date_visite=? where numero=?");
			if(visite.getPatient()==null) 
			{
				requete.setObject(1, null);
			}
			else 
			{
				requete.setInt(1, visite.getPatient().getId());	
			}
			requete.setInt(2, visite.getMedecin().getId());
			requete.setDouble(3, visite.getPrix());
			requete.setInt(4, visite.getSalle());
			requete.setString(5, visite.getDateVisite().toString());
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

			PreparedStatement requete = conn.prepareStatement("DELETE FROM visite where numero=?");	
			requete.setInt(1, id);
			
			requete.executeUpdate();
			
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Visite> findByPatientId(Integer id) {
		Visite visite = null;
		List<Visite> visites = new ArrayList();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from visite where id_patient=?");
			requete.setInt(1, id);
			ResultSet rs = requete.executeQuery();


			while(rs.next()) {
				Patient patient = daoPatient.findById(rs.getInt("id_patient"));
				Medecin medecin = (Medecin) daoCompte.findById(rs.getInt("id_medecin"));
				visite = new Visite(rs.getInt("numero"),patient,medecin,rs.getInt("salle"),rs.getDouble("prix"),LocalDate.parse(rs.getString("date_visite")));
				visites.add(visite);
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return visites;
	}

	@Override
	public double findByDateVisiteBetweenAndMedecinId(String debut,String fin,Integer id) 
	{
		double total=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT SUM(prix) as total from visite where date_visite between ? and ? and id_medecin=?");
			requete.setString(1,debut);
			requete.setString(2, fin);
			requete.setInt(3, id);
			ResultSet rs = requete.executeQuery();


			while(rs.next()) {
				total=rs.getDouble("total");
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
}
