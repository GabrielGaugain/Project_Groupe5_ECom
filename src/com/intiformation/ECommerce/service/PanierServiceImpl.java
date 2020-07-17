package com.intiformation.ECommerce.service;

import java.util.List;

import com.intiformation.ECommerce.dao.IPanierDAO;
import com.intiformation.ECommerce.dao.PanierDAOImpl;
import com.intiformation.ECommerce.modele.Panier;

public class PanierServiceImpl implements IPanierService{
	
private IPanierDAO panierDAO;
	
	public PanierServiceImpl() {
		panierDAO = new PanierDAOImpl();
	}//end ctor

	@Override
	public boolean ajouterPanier(Panier pPanier) {
		return panierDAO.add(pPanier);
	}

	@Override
	public boolean modifierPanier(Panier pPanier) {
		return panierDAO.update(pPanier);
	}

	@Override
	public boolean supprimerPanier(long pIdPanier) {
		return panierDAO.delete(pIdPanier);
	}

	@Override
	public List<Panier> afficherTousLesPaniers() {
		return panierDAO.getAll();
	}

	@Override
	public Panier trouverPanierParId(int pIdPanier) {
		return panierDAO.getById(pIdPanier);
	}

	@Override
	public Panier getLastBasket() {
		return panierDAO.getLastBasket();
	}

}
