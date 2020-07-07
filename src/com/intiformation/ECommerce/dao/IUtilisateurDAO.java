package com.intiformation.ECommerce.dao;

import com.intiformation.ECommerce.modele.Utilisateur;

public interface IUtilisateurDAO extends IGenericDAO<Utilisateur>{
	
	/**
	 * méthode spécifique de l'utilisateur
	 */

	
	public boolean isUtilisateurExists(String pIdentifiant, String pMotDePasse);

}
