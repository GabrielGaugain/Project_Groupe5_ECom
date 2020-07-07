package com.intiformation.ECommerce.service;

import java.util.List;

import com.intiformation.ECommerce.modele.LigneCommande;

public interface ILigneCommandeService {

	public boolean ajouterLigneCommande (LigneCommande pLigneCommande);
	public boolean modifierLigneCommande (LigneCommande pLigneCommande);
	public boolean supprimerLigneCommande(long pIdLigneCommande);
	public List<LigneCommande> afficherToutesLesLigneCommandes();	
	public LigneCommande trouverLigneCommandeParId(int pIdLigneCommande);
	
}

