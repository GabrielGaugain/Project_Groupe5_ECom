package com.intiformation.ECommerce.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.intiformation.ECommerce.dao.IUtilisateurDAO;
import com.intiformation.ECommerce.dao.UtilisateurDAOImpl;

/**
 * ManagedBean pour l'authentification de l'utilisateur
 * @author gabri
 *
 */

@ManagedBean(name="authentificationBean")
@SessionScoped
public class AuthentificationBean implements Serializable{

	/*_____________________________props_______________________________*/
	private String userIdentifiant;
	private String userMotDePasse;
	private String userStatut ;
	private IUtilisateurDAO utilisateurDAO;
	
	/*_____________________________ctors_______________________________*/
	public AuthentificationBean() {
		utilisateurDAO = new UtilisateurDAOImpl();
	}//end ctor vide

	
	/*_____________________________meths_______________________________*/
	/**
	 * Permet de connecter l'utilisateur à son espace et lui créer une session http. <br/>
	 * la méthode sera invoquée à la soumission de la form de 'authentification.xhtml'
	 * @return
	 */
	public String connecterUtilisateur() {
		
		// 1. délcaration du context de JSF (l'objet FacesContext)
		/**
		 * javax.faces.FacesContext
		 */
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		// 2. verif si l'utilisateur existe dans la  bdd
		if(utilisateurDAO.isUtilisateurExists(userIdentifiant, userMotDePasse)) {
			//----------------- l'utilisateur existe ----------------------------//
			
			// 2.1 verif du statut de l'utilisateur dans la  bdd par rapport à son statut de connexion
			
			if(utilisateurDAO.isUtilisateurAutorise(userIdentifiant, userMotDePasse, userStatut)) {
				
				//-> création de la session
				HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(true);
				
				System.out.println("status dans connecterUser : " +userStatut);
				
				//->sauvegarde du login de l'utilisatuer dans la session
				session.setAttribute("user_login", userIdentifiant);
				
				//->sauvegarde du statut de l'utilisatuer dans la session
				session.setAttribute("user_statut", userStatut);
				
				if(("admin").equals(userStatut)) {
					//-> navigation vers la page 'accueil-admin.xhtml'
					//return "acces_admin/accueil-client.xhtml";
					return "admin";
				}
				
				else if(("client").equals(userStatut)) {
					System.out.println("acces_client/accueil-client.xhtml");
					//return "acces_client/accueil-client.xhtml";
					return "client";
				}
				System.out.println("authentification.xhtml");
				//-> navigation vers la page 'accueil.xhtml'
				return "authentification.xhtml";
				
			}else{
				//----------------- l'utilisateur essaie de se connecter avec le mauvais statut ----------------------//

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de connexion", "Vous n'êtes pas autorisé à vous connecter en tant que : "+ userStatut);
				
				//-> envoi du massage vers la bue via le constex de JSF (l'objet 'FacesContext') et sa méthode addMessage()
				contextJSF.addMessage(null, message );
				
				return "authentification.xhtml" ;
			
			}//end else
			

		}else {
			//----------------- l'utilisateur n'existe pas ----------------------//
			/**
			 * envoi d'un message vers la vue
			 */
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de connexion", "Identifiant ou Mot de passe incorrect");
			
			//-> envoi du massage vers la bue via le constex de JSF (l'objet 'FacesContext') et sa méthode addMessage()
			contextJSF.addMessage(null, message );
			
			return "authentification.xhtml" ;
		
		}//end else
		
		
		
	}//end connecterUtilisateur
	
	
	public String deconnecterUtilisateur() {
		
		System.out.println("dans deconnexion...");
		// 1. recup du context JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		// 2. recup de la session 
		HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(false);
		
		// 3 deconnexion
		session.invalidate();
		
		// 4. message de deco
		FacesMessage facesMessages = new FacesMessage(FacesMessage.SEVERITY_INFO,"déconnexion", "- vous êtes déconnecté") ; 
		contextJSF.addMessage(null, facesMessages);
		
		// 4. redirection vers 'authentification.xhtml'
		return "logout";
		
	}//end deconnecterUtilisateur
	
	
	/*_____________________________getters/setters_______________________________*/
	
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
	
	public String getUserStatut() {
		return userStatut;
	}

	public void setUserStatut(String userStatut) {
		this.userStatut = userStatut;
	}
	
}// end class
