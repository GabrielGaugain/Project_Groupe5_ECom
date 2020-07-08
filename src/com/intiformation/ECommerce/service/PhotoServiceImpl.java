package com.intiformation.ECommerce.service;

import java.util.List;

import com.intiformation.ECommerce.dao.IPhotoDAO;
import com.intiformation.ECommerce.dao.PhotoDAOImpl;
import com.intiformation.ECommerce.modele.Photo;

public class PhotoServiceImpl implements IPhotoService{
	
	private IPhotoDAO photoDAO;
	
	public PhotoServiceImpl() {
		photoDAO = new PhotoDAOImpl();
	}//end ctor

	@Override
	public boolean ajouterPhoto(Photo pPhoto) {
		return photoDAO.add(pPhoto);
	}

	@Override
	public boolean modifierPhoto(Photo pPhoto) {
		return photoDAO.update(pPhoto);
	}

	@Override
	public boolean supprimerPhoto(long pIdPhoto) {
		return photoDAO.delete(pIdPhoto);
	}

	@Override
	public List<Photo> afficherToutesLesPhotos() {
		return photoDAO.getAll();
	}

	@Override
	public Photo trouverPhotoParId(int pIdPhoto) {
		return photoDAO.getById(pIdPhoto);
	}

}//end class
