package com.intiformation.ECommerce.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;

import com.intiformation.ECommerce.dao.IUtilisateurDAO;
import com.intiformation.ECommerce.dao.UtilisateurDAOImpl;
import com.intiformation.ECommerce.modele.Categorie;
import com.intiformation.ECommerce.modele.Photo;
import com.intiformation.ECommerce.modele.Utilisateur;

@ManagedBean(name = "utilisateurBean")
@SessionScoped
public class GestionUtilisateurBean implements Serializable {
	
	/* _____________________________props_______________________________ */
	IUtilisateurDAO userDAO ;
	private String userIdentifiant;
	private String userMotDePasse;
	private String userMotDePasseAgain;
	private Utilisateur user;
	
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
	
    public void initUser(ActionEvent event) {
        setUser(new Utilisateur());
    }	

    /**
     * modif d'un utilisateur
     *
     * @param event
     */
    public void selectUser(ActionEvent event) {
    	
    	FacesContext contextJSF = FacesContext.getCurrentInstance();
    	
        UIParameter cUserUp = (UIParameter) event.getComponent().findComponent("editIDuser");
        Long idUp = (Long) cUserUp.getValue();
        
        System.out.println("id du user a mod : "+idUp);
        
        Utilisateur userToUpdate = userDAO.getById(idUp) ;
        
        System.out.println("user a mod : "+userToUpdate);

        setUser(userToUpdate);
        
        System.out.println("user a mod : "+user);
    }//end selectUser

    
    
    /**
     * suppression d'un utilisateur
     * @param event
     */
    public void deleteUser(ActionEvent event) {

    	FacesContext contextJSF = FacesContext.getCurrentInstance();
    	
        UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteIDuser");
        Long id = (long) cp.getValue();
       
        if(userDAO.delete(id)) {
		
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Compte supprimé -", 
					"Le compte a bien été supprimé !");
			//-> envoi du massage vers la bue via le constex de JSF (l'objet 'FacesContext') et sa méthode addMessage()
			contextJSF.addMessage(null, message );	     	
        
        }else {
        	
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					"Echec de la suppression -", 
					"Nous n'avons pas reussi à supprimer le compte  ");
			
			//-> envoi du massage vers la bue via le constex de JSF (l'objet 'FacesContext') et sa méthode addMessage()
			contextJSF.addMessage(null, message );           	
        	
        }//end else
        
        listeUserBDD = getListeUser();
        
    }//end deleteUser

    
    
    /**
     * sauvegarder un utilisateur (ajout ou modification via edit-user.xhtml)
     *
     * @param event
     */
    public void saveUser(ActionEvent event) {
    	
    	FacesContext contextJSF = FacesContext.getCurrentInstance();
        
    	//-------------------------------------------
        // cas : ajout 
        //-------------------------------------------
        if (user.getIdUtilisateur() == 0L) {
        	
        	// ajout de l'utilisateur dans la bdd
        	if(userDAO.add(user)) {
    			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
    					"Compte créé -", 
    					"Le compte a bien été créé !");
    			//-> envoi du massage vers la bue via le constex de JSF (l'objet 'FacesContext') et sa méthode addMessage()
    			contextJSF.addMessage(null, message );        		
        	}else {
            	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
    					"Echec de l'ajout  -", 
    					"Nous n'avons pas reussi à créé le compte  ");
    			
    			//-> envoi du massage vers la bue via le constex de JSF (l'objet 'FacesContext') et sa méthode addMessage()
    			contextJSF.addMessage(null, message );         		
        	}//end else
        		
        }//end if

        //-------------------------------------------
        // cas : modif 
        //-------------------------------------------
        if (user.getIdUtilisateur() != 0L) {
        	
            if(userDAO.update(user)) {
    			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
    					"Compte modifié -", 
    					"Le compte a bien été modifié !");
    			//-> envoi du massage vers la bue via le constex de JSF (l'objet 'FacesContext') et sa méthode addMessage()
    			contextJSF.addMessage(null, message );                  	
            }else {
            	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
    					"Echec modification  -", 
    					"Nous n'avons pas reussi à modifier le compte  ");
    			
    			//-> envoi du massage vers la bue via le constex de JSF (l'objet 'FacesContext') et sa méthode addMessage()
    			contextJSF.addMessage(null, message );                	
            }//end else
            
        }//end if

    }//end saveUser()
    
	
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



	public Utilisateur getUser() {
		return user;
	}



	public void setUser(Utilisateur user) {
		this.user = user;
	}
	
	
	
}//end class
