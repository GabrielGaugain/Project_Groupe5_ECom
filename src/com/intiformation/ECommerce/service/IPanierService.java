package com.intiformation.ECommerce.service;

import java.util.List;

import com.intiformation.ECommerce.modele.Panier;

public interface IPanierService {

	public boolean ajouterPanier (Panier pPanier);
	public boolean modifierPanier (Panier pPanier);
	public boolean supprimerPanier (long pIdPanier);
	public List<Panier> afficherTousLesPaniers();	
	public Panier trouverPanierParId(int pIdPanier);
	
}
