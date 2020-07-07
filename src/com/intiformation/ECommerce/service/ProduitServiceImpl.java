package com.intiformation.ECommerce.service;

import java.util.List;

import com.intiformation.ECommerce.dao.IProduitDAO;
import com.intiformation.ECommerce.dao.ProduitDAOImpl;
import com.intiformation.ECommerce.modele.Produit;

public class ProduitServiceImpl implements IProduitService {
	
private IProduitDAO produitDAO;
	
	public ProduitServiceImpl() {
		produitDAO = new ProduitDAOImpl();
	}//end ctor

	@Override
	public boolean ajouterProduit(Produit pProduit) {
		return produitDAO.add(pProduit);
	}

	@Override
	public boolean modifierProduit(Produit pProduit) {
		return produitDAO.update(pProduit);
	}

	@Override
	public boolean supprimerProduit(long pIdProduit) {
		return produitDAO.delete(pIdProduit);
	}

	@Override
	public List<Produit> afficherTousLesProduits() {
		return produitDAO.getAll();
	}

	@Override
	public Produit trouverProduitParId(int pIdProduit) {
		return produitDAO.getById(pIdProduit);
	}

	@Override
	public List<Produit> trouverProduitParIdCategorie(int pIdCategorie) {
		return produitDAO.getAllProduitByCategorie(pIdCategorie);
	}

	@Override
	public List<Produit> afficherProduitsSelectionnes() {
		return produitDAO.getProduitSelectionne();
	}

	@Override
	public List<Produit> trouverProduitParMotCle(String pMotCle) {
		return produitDAO.getByMotCle(pMotCle);
	}

}
