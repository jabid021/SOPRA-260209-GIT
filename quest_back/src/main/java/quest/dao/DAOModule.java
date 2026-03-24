package quest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import quest.model.Module;

@Repository
@Transactional
public class DAOModule implements IDAOModule{

	@PersistenceContext
	private EntityManager em;


	@Override
	public Module findById(Integer id) {
		return em.find(Module.class, id); 
	}

	@Override
	public List<Module> findAll() {
		return em.createQuery("from Module").getResultList();
	}

	@Override
	public Module save(Module module) {
		return em.merge(module);
	}

	@Override
	public void deleteById(Integer id) {
		Module module = em.find(Module.class, id);
		em.remove(module);
	}

	@Override
	public void delete(Module module) {
		module=em.merge(module);
		em.remove(module);
	}

	@Override
	public Module findByQuest(int quest) {
		Module module =null;
		try {
			module = em.createQuery("SELECT m from Module m where m.quest=:quest",Module.class).setParameter("quest", quest).getSingleResult();
		}catch(Exception e) {e.printStackTrace();};
		return module;
	}
}
