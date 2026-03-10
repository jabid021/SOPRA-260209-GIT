package quest.dao;

import java.util.List;

public interface IDAO<T,K> {

	public T findById(K id);
	public List<T> findAll();
	public T save(T obj);
	public void deleteById(K id);	
	public void delete(T object);	
}
