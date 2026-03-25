package quest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import quest.model.Ordinateur;

public interface IDAOOrdinateur extends JpaRepository<Ordinateur,Integer> {

	public List<Ordinateur> findByMarque(String marque);
}
