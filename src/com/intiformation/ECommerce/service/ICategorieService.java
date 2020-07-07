package com.intiformation.ECommerce.service;

import java.util.List;

import com.intiformation.ECommerce.modele.Categorie;

public interface ICategorieService {

	public boolean ajouterCategorie (Categorie pCategorie);
	public boolean modifierCategorie (Categorie pCategorie);
	public boolean supprimerCategorie (long pIdCategorie);
	public List<Categorie> afficherToutesLesCategories();	
	public Categorie trouverCategorieParId(int pIdCategorie);
	
}
