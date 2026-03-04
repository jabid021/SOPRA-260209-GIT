package quest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import quest.model.Filiere;
import quest.model.Matiere;
import quest.model.Module;


public class DAOModule implements IDAO<Module,Integer>{

	static DAOMatiere daoMatiere = new DAOMatiere();
	static DAOFiliere daoFiliere = new DAOFiliere();
	
	@Override
	public Module findById(Integer id) {
		Module module =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from module where id=?");
			requete.setInt(1, id);

			ResultSet rs = requete.executeQuery();

			while(rs.next()) {
				Filiere filiere = daoFiliere.findById(rs.getInt("filiere"));
				Matiere matiere = daoMatiere.findById(rs.getInt("matiere"));
				LocalDate debut = (rs.getString("debut")==null)?null : LocalDate.parse(rs.getString("debut"));
				LocalDate fin = (rs.getString("fin")==null)?null : LocalDate.parse(rs.getString("fin"));
				module = new Module(rs.getInt("id"),rs.getInt("quest"),debut,fin,filiere,matiere);
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return module;
	}

	@Override
	public List<Module> findAll() {
		List<Module> modules=new ArrayList();
		Module module =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("SELECT * from module");

			ResultSet rs = requete.executeQuery();

			while(rs.next()) {
				Filiere filiere = daoFiliere.findById(rs.getInt("filiere"));
				Matiere matiere = daoMatiere.findById(rs.getInt("matiere"));
				LocalDate debut = (rs.getString("debut")==null)?null : LocalDate.parse(rs.getString("debut"));
				LocalDate fin = (rs.getString("fin")==null)?null : LocalDate.parse(rs.getString("fin"));
				module = new Module(rs.getInt("id"),rs.getInt("quest"),debut,fin,filiere,matiere);
				modules.add(module);
			}

			rs.close();
			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return modules;
	}

	@Override
	public Module insert(Module module) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("INSERT INTO module (quest,debut,fin,filiere,matiere) VALUES (?,?,?,?,?)");
			requete.setInt(1, module.getQuest());
			if(module.getDebut()==null) 
			{
				requete.setString(2, null);
			}
			else {
				requete.setString(2, module.getDebut().toString());
			}
			if(module.getFin()==null) 
			{
				requete.setString(3, null);
			}
			else 
			{
				requete.setString(3, module.getFin().toString());
			}
			
			requete.setInt(4, module.getFiliere().getId());
			requete.setInt(5, module.getMatiere().getId());
			requete.executeUpdate();

			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Module update(Module module) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement requete = conn.prepareStatement("UPDATE module set quest=?,debut=?,fin=?,filiere=?,matiere=? where id=?");
			requete.setInt(1, module.getQuest());
			if(module.getDebut()==null) 
			{
				requete.setString(2, null);
			}
			else {
				requete.setString(2, module.getDebut().toString());
			}
			if(module.getFin()==null) 
			{
				requete.setString(3, null);
			}
			else 
			{
				requete.setString(3, module.getFin().toString());
			}
			requete.setInt(4, module.getFiliere().getId());
			requete.setInt(5, module.getMatiere().getId());
			requete.setInt(6, module.getId());
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

			PreparedStatement requete = conn.prepareStatement("DELETE from module where id=?");
			requete.setInt(1,id);

			requete.executeUpdate();

			requete.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
