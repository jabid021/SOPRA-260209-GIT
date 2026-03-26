package quest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import quest.model.Module;

public interface IDAOModule extends JpaRepository<Module,Integer> {

	public Module findByQuest(int quest);
}
