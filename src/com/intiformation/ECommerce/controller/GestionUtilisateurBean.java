package com.intiformation.ECommerce.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import com.intiformation.ECommerce.dao.IUtilisateurDAO;
import com.intiformation.ECommerce.dao.UtilisateurDAOImpl;
import com.intiformation.ECommerce.modele.Utilisateur;

@ManagedBean(name = "utilisateurBean")
@SessionScoped
public class GestionUtilisateurBean implements Serializable {
	
	/* _____________________________props_______________________________ */
	IUtilisateurDAO userDAO ;
	private Collection<Utilisateur> listeUserBDD;
	
	/* _____________________________ctors_______________________________ */
	public GestionUtilisateurBean() {
		userDAO = new UtilisateurDAOImpl();
	}// end ctor vide



	/* _____________________________meths_______________________________ */
	
	public Collection<Utilisateur> getListeUser() {
		return listeUserBDD = userDAO.getAll();
	}
	
	
	/* _______________________ GETTERS/SETTERS ________________________________ */
	
	public Collection<Utilisateur> getListeUserBDD() {
		return listeUserBDD;
	}

	public void setListeUserBDD(Collection<Utilisateur> listeUserBDD) {
		this.listeUserBDD = listeUserBDD;
	}
	
	
}//end class
