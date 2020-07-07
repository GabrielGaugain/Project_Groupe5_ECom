package com.intiformation.ECommerce.service;

import java.util.List;

import com.intiformation.ECommerce.dao.CategorieDAOImpl;
import com.intiformation.ECommerce.dao.ICategorieDAO;
import com.intiformation.ECommerce.modele.Categorie;

public class CategorieServiceImpl implements ICategorieService {
	
	private ICategorieDAO categorieDAO;
	
	public CategorieServiceImpl() {
		categorieDAO = new CategorieDAOImpl();
	}//end ctor

	@Override
	public boolean ajouterCategorie(Categorie pCategorie) {
		return categorieDAO.add(pCategorie);
	}

	@Override
	public boolean modifierCategorie(Categorie pCategorie) {
		return categorieDAO.update(pCategorie);
	}

	@Override
	public boolean supprimerCategorie(long pIdCategorie) {
		return categorieDAO.delete(pIdCategorie);
	}

	@Override
	public List<Categorie> afficherToutesLesCategories() {
		return categorieDAO.getAll();
	}

	@Override
	public Categorie trouverCategorieParId(int pIdCategorie) {
		return categorieDAO.getById(pIdCategorie);
	}

}
