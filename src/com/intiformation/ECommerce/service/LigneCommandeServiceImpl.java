package com.intiformation.ECommerce.service;

import java.util.List;

import com.intiformation.ECommerce.dao.LigneCommandeDAOImpl;
import com.intiformation.ECommerce.modele.LigneCommande;

public class LigneCommandeServiceImpl implements ILigneCommandeService{
	
private LigneCommandeDAOImpl ligneCommandeDAO;
	
	public LigneCommandeServiceImpl() {
		ligneCommandeDAO = new LigneCommandeDAOImpl();
	}//end ctor

	@Override
	public boolean ajouterLigneCommande(LigneCommande pLigneCommande) {
		return ligneCommandeDAO.add(pLigneCommande);
	}

	@Override
	public boolean modifierLigneCommande(LigneCommande pLigneCommande) {
		return ligneCommandeDAO.update(pLigneCommande);
	}

	@Override
	public boolean supprimerLigneCommande(long pIdLigneCommande) {
		return ligneCommandeDAO.delete(pIdLigneCommande);
	}

	@Override
	public List<LigneCommande> afficherToutesLesLigneCommandes() {
		return ligneCommandeDAO.getAll();
	}

	@Override
	public LigneCommande trouverLigneCommandeParId(int pIdLigneCommande) {
		return ligneCommandeDAO.getById(pIdLigneCommande);
	}

}
