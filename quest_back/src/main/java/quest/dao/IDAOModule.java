package quest.dao;

import quest.model.Module;

public interface IDAOModule extends IDAO<Module,Integer> {

	public Module findByQuest(int quest);
}
