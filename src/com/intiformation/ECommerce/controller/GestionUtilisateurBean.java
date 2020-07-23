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
	private String userIdentifiant;
	private String userMotDePasse;
	private String userMotDePasseAgain;
	
	private Collection<Utilisateur> listeUserBDD;
	
	/* _____________________________ctors_______________________________ */
	public GestionUtilisateurBean() {
		userDAO = new UtilisateurDAOImpl();
	}// end ctor vide



	/* _____________________________meths_______________________________ */
	
	public String creerCompte() {
		// 1. délcaration du context de JSF (l'objet FacesContext)
		/**
		 * javax.faces.FacesContext
		 */
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if(userMotDePasse.equals(userMotDePasseAgain)) {
			
			Utilisateur newUser = new Utilisateur(userIdentifiant, userMotDePasse, 2L, true);
			
			if(userDAO.add(newUser)) {
				
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
														"Compte créé -", 
														"Vous pouvez désormais vous connecter");
				//-> envoi du massage vers la bue via le constex de JSF (l'objet 'FacesContext') et sa méthode addMessage()
				contextJSF.addMessage(null, message );				
				
				return "authentification.xhtml" ;
				
			}else {

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
														"Echec de creation du compte -", 
														"Nous n'avons pas reussi à ajouter votre compte (sorry not sorry...) ");
				//-> envoi du massage vers la bue via le constex de JSF (l'objet 'FacesContext') et sa méthode addMessage()
				contextJSF.addMessage(null, message );
				
				return "registration.xhtml" ;				
				
			}
			
		
		}else {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
													"Echec de creation du compte -", 
													"Les mots de passes ne correspondent pas ");
			//-> envoi du massage vers la bue via le constex de JSF (l'objet 'FacesContext') et sa méthode addMessage()
			contextJSF.addMessage(null, message );
			
			return "registration.xhtml" ;
		}
		

		
				
	}	
	
	
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



	public String getUserIdentifiant() {
		return userIdentifiant;
	}



	public void setUserIdentifiant(String userIdentifiant) {
		this.userIdentifiant = userIdentifiant;
	}



	public String getUserMotDePasse() {
		return userMotDePasse;
	}



	public void setUserMotDePasse(String userMotDePasse) {
		this.userMotDePasse = userMotDePasse;
	}



	public String getUserMotDePasseAgain() {
		return userMotDePasseAgain;
	}



	public void setUserMotDePasseAgain(String userMotDePasseAgain) {
		this.userMotDePasseAgain = userMotDePasseAgain;
	}
	
	
	
}//end class
