package quest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import quest.model.Ordinateur;


@Repository
@Transactional
public class DAOOrdinateur implements IDAOOrdinateur{

	@PersistenceContext
	private EntityManager em;


	@Override
	public Ordinateur findById(Integer id) {

		return em.find(Ordinateur.class, id); 
	}

	@Override
	public List<Ordinateur> findAll() {
		return em.createQuery("from Ordinateur").getResultList();
	}

	@Override
	public Ordinateur save(Ordinateur ordinateur) {
		return em.merge(ordinateur);
	}

	@Override
	public void deleteById(Integer id) {
		Ordinateur ordinateur = em.find(Ordinateur.class, id);
		em.remove(ordinateur);
	}

	@Override
	public void delete(Ordinateur ordinateur) {

		ordinateur=em.merge(ordinateur);
		em.remove(ordinateur);
	}

	@Override
	public List<Ordinateur> findByMarque(String marque) {
		return em.createQuery("SELECT o from Ordinateur o where o.marque=:marque")
				.setParameter("marque", marque)
				.getResultList();
	}
}
