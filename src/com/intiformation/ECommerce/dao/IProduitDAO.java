package com.intiformation.ECommerce.dao;

import java.util.List;

import com.intiformation.ECommerce.modele.Produit;

public interface IProduitDAO extends IGenericDAO<Produit> {
	
	
	public List<Produit> getAllProduitByCategorie(long pIdCategorie);
	
	public List<Produit> getProduitSelectionne();
	
	public List<Produit> getByMotCle(String pMotCle);

}//end interface
