package quest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;

@Repository
@Transactional
public class DAOPersonne implements IDAOPersonne{

	@PersistenceContext
	private EntityManager em;


	@Override
	public Personne findById(Integer id) {
		return em.find(Personne.class, id); 
	}

	@Override
	public List<Personne> findAll() {
		return em.createQuery("from Personne").getResultList();
	}

	@Override
	public Personne save(Personne personne) {
		return em.merge(personne);
	}

	@Override
	public void deleteById(Integer id) {
		Personne personne = em.find(Personne.class, id);
		em.remove(personne);
	}

	@Override
	public void delete(Personne personne) {
		personne=em.merge(personne);
		em.remove(personne);
	}

	@Override
	public List<Stagiaire> findAllStagiaire() {

		return em.createQuery("from Stagiaire").getResultList();
	}

	@Override
	public List<Stagiaire> findAllStagiaireDisponibles() {
		return em.createQuery("SELECT s from Stagiaire s where s.ordinateur is null").getResultList();
	}

	@Override
	public List<Formateur> findAllFormateur() {
		return em.createQuery("from Formateur").getResultList();
	}

	@Override
	public Personne findByLoginAndPassword(String login,String password) {
		Personne personne = null;
		try {
			personne = em.createQuery("SELECT p from Personne p where p.login=:login and p.password=:password",Personne.class)
					.setParameter("login", login)
					.setParameter("password", password)
					.getSingleResult();
		}
		catch(Exception e) {e.printStackTrace();}
		return personne;
	}
}
