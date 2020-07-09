package com.intiformation.ECommerce.dao;

import com.intiformation.ECommerce.modele.Photo;

public interface IPhotoDAO extends IGenericDAO<Photo> {

	boolean delete(String pUrlPhoto);

}//end interface
