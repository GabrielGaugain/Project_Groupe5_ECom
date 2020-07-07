package com.intiformation.ECommerce.service;

import java.util.List;

import com.intiformation.ECommerce.modele.Utilisateur;

public interface IUtilisateurService {

	public boolean ajouterUtilisateur (Utilisateur pUtilisateur);
	public boolean modifierUtilisateur (Utilisateur pUtilisateur);
	public boolean supprimerUtilisateur (long pIdUtilisateur);
	public List<Utilisateur> afficherTousLesUtilisateurs();	
	public Utilisateur trouverUtilisateurParId(int pIdUtilisateur);
	
}
