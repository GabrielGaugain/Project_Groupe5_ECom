package com.intiformation.ECommerce.service;

import java.util.List;

import com.intiformation.ECommerce.modele.Produit;

public interface IProduitService {

	public boolean ajouterProduit (Produit pProduit);
	public boolean modifierProduit (Produit pProduit);
	public boolean supprimerProduit (long pIdProduit);
	public List<Produit> afficherTousLesProduits();	
	public Produit trouverProduitParId(int pIdProduit);
	public List<Produit> trouverProduitParIdCategorie(int pIdCategorie);	
	public List<Produit> afficherProduitsSelectionnes();	
	public List<Produit> trouverProduitParMotCle(String pMotCle);	
	
	
}
