package com.intiformation.ECommerce.service;

import java.util.List;

import com.intiformation.ECommerce.modele.Photo;

public interface IPhotoService {

	public boolean ajouterPhoto (Photo pPhoto);
	public boolean modifierPhoto (Photo pPhoto);
	public boolean supprimerPhoto (long pIdPhoto);
	public List<Photo> afficherToutesLesPhotos();	
	public Photo trouverPhotoParId(int pIdPhoto);
	
}//end interface
