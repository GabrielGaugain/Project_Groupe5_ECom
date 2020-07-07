package com.intiformation.ECommerce.service;

import java.util.List;

import com.intiformation.ECommerce.modele.Commande;

public interface ICommandeService {
	
	public boolean ajouterCommande (Commande pCommande);
	public boolean modifierCommande (Commande pCommande);
	public boolean supprimerCommande (long pIdCommande);
	public List<Commande> afficherToutesLesCommandes();	
	public Commande trouverCommandeParId(int pIdCommande);

}
