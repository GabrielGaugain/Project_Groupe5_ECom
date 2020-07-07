package com.intiformation.ECommerce.service;


import java.util.List;

import com.intiformation.ECommerce.dao.CommandeDAOImpl;
import com.intiformation.ECommerce.modele.Commande;


public class CommandeServiceImpl implements ICommandeService {
	
	private CommandeDAOImpl commandeDAO;
	
	public CommandeServiceImpl() {
		commandeDAO = new CommandeDAOImpl();
	}//end ctor

	@Override
	public boolean ajouterCommande(Commande pCommande) {
		return commandeDAO.add(pCommande);
	}

	@Override
	public boolean modifierCommande(Commande pCommande) {
		return commandeDAO.update(pCommande);
	}

	@Override
	public boolean supprimerCommande(long pIdCommande) {
		return commandeDAO.delete(pIdCommande);
	}

	@Override
	public List<Commande> afficherToutesLesCommandes() {
		return commandeDAO.getAll();
	}

	@Override
	public Commande trouverCommandeParId(int pIdCommande) {
		return commandeDAO.getById(pIdCommande);
		
	}

}
