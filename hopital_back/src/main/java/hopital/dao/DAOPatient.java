package hopital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hopital.model.Patient;

public class DAOPatient implements IDAOPatient {

	@Override
	public Patient findById(Integer id) {
		Patient patient = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from patient where id=?");
			requete.setInt(1, id);
			ResultSet rs = requete.executeQuery();


			while(rs.next()) {
				patient = new Patient(rs.getInt("id"),rs.getString("prenom"),rs.getString("nom"));
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return patient;
	}

	@Override
	public List<Patient> findAll() {
		Patient patient = null;
		List<Patient> patients = new ArrayList();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from patient");
			ResultSet rs = requete.executeQuery();


			while(rs.next()) {
				patient = new Patient(rs.getInt("id"),rs.getString("prenom"),rs.getString("nom"));
				patients.add(patient);
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}

	@Override
	public Patient insert(Patient patient) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("INSERT INTO patient (id,prenom,nom) VALUES(?,?,?)");
			requete.setInt(1, patient.getId());
			requete.setString(2,patient.getPrenom());
			requete.setString(3, patient.getNom());
			requete.executeUpdate();
			
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Patient update(Patient patient) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("UPDATE patient set prenom=?,nom=? where id=?");
			requete.setString(1,patient.getPrenom());
			requete.setString(2, patient.getNom());
			requete.setInt(3, patient.getId());
			
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

			//Le fait d'enchainer plusieurs requetes => Transaction
			
			PreparedStatement requete=conn.prepareStatement("UPDATE visite set id_patient=null where id_patient=?");
			requete.setInt(1,id);
			requete.executeUpdate();
			
			requete = conn.prepareStatement("DELETE from patient where id=?");
			requete.setInt(1,id);
			
			requete.executeUpdate();
			
			
			
			
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
