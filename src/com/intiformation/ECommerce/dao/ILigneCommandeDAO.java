package com.intiformation.ECommerce.dao;

import java.util.Collection;

import com.intiformation.ECommerce.modele.LigneCommande;

public interface ILigneCommandeDAO extends IGenericDAO<LigneCommande> {

	public Collection<LigneCommande> getByPanierId(long pIdPanier);
	
}
