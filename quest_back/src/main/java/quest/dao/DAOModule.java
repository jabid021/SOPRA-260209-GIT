package quest.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quest.context.Singleton;
import quest.model.Filiere;
import quest.model.Module;

public class DAOModule implements IDAOModule{

	@Override
	public Module findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Module module = em.find(Module.class, id); 
		em.close();
		return module;
	}

	@Override
	public List<Module> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Module> modules = em.createQuery("from Module").getResultList();
		em.close();
		return modules;
	}

	@Override
	public Module save(Module module) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			module=em.merge(module);
		em.getTransaction().commit();
		em.close();
		return module;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Module module = em.find(Module.class, id);
		em.getTransaction().begin();
			em.remove(module);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Module module) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	
		em.getTransaction().begin();
			module=em.merge(module);
			em.remove(module);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Module findByQuest(int quest) {
		Module module =null;
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		try {
			module = em.createQuery("SELECT m from Module m where m.quest=:quest",Module.class).setParameter("quest", quest).getSingleResult();
		}catch(Exception e) {e.printStackTrace();};
		
		em.close();
		return module;
	}
}
