package com.intiformation.ECommerce.service;

import java.util.List;

import com.intiformation.ECommerce.dao.IUtilisateurDAO;
import com.intiformation.ECommerce.dao.UtilisateurDAOImpl;
import com.intiformation.ECommerce.modele.Utilisateur;

public class UtilisateurServiceImpl implements IUtilisateurService{
	
private IUtilisateurDAO utilisateurDAO;
	
	public UtilisateurServiceImpl() {
		utilisateurDAO = new UtilisateurDAOImpl();
	}//end ctor

	@Override
	public boolean ajouterUtilisateur(Utilisateur pUtilisateur) {
		return utilisateurDAO.add(pUtilisateur);
	}

	@Override
	public boolean modifierUtilisateur(Utilisateur pUtilisateur) {
		return utilisateurDAO.update(pUtilisateur);
	}

	@Override
	public boolean supprimerUtilisateur(long pIdUtilisateur) {
		return utilisateurDAO.delete(pIdUtilisateur);
	}

	@Override
	public List<Utilisateur> afficherTousLesUtilisateurs() {
		return utilisateurDAO.getAll();
	}

	@Override
	public Utilisateur trouverUtilisateurParId(int pIdUtilisateur) {
		return utilisateurDAO.getById(pIdUtilisateur);
	}

}
