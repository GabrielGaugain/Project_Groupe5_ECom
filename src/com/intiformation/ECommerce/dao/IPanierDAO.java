package com.intiformation.ECommerce.dao;

import com.intiformation.ECommerce.modele.Panier;

public interface IPanierDAO extends IGenericDAO<Panier>{

	public Panier getLastBasket();

}//end class
