package quest.dao;

import quest.model.Module;

public interface IDAOModule {

	public Module findByQuest(int quest);
}
