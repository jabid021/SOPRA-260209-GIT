package eshop.dao;

import java.util.List;

import eshop.model.Produit;

public interface IDAOProduit extends IDAO<Produit,Integer> {

	public List<Produit> findByLibLike(String lib);
	public Produit findByIdWithVentes(Integer idProduit);
}
