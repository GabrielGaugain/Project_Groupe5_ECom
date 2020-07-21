package com.intiformation.ECommerce.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.catalina.ha.backend.CollectedInfo;

import com.intiformation.ECommerce.dao.IProduitDAO;
import com.intiformation.ECommerce.dao.ProduitDAOImpl;

import com.intiformation.ECommerce.modele.LigneCommande;
import com.intiformation.ECommerce.modele.Panier;
import com.intiformation.ECommerce.modele.Produit;

import com.intiformation.ECommerce.service.ICommandeService;
import com.intiformation.ECommerce.service.ILigneCommandeService;
import com.intiformation.ECommerce.service.IPanierService;
import com.intiformation.ECommerce.service.LigneCommandeServiceImpl;
import com.intiformation.ECommerce.service.PanierServiceImpl;

@ManagedBean(name = "panierBean")
@SessionScoped
public class GestionPanierBean implements Serializable {
	
	/* _____________________________props_______________________________ */
	// ====> Var
	private Collection<LigneCommande> lignesDeCommande;
	private LigneCommande ligneDeCmd;
	private Panier panierTemp;

	private int quantite;
	private Produit prodLigne;
	
	// ====> service/dao
	IProduitDAO prodDAO;
	private ILigneCommandeService ligneCmdService;
	private IPanierService panierService;
	
	
	/* _____________________________ctors_______________________________ */
	public GestionPanierBean() {
		prodDAO = new ProduitDAOImpl();
		ligneCmdService = new LigneCommandeServiceImpl();
		panierService = new PanierServiceImpl();
	
		panierTemp = null;
	}// end ctor vide

	
	/* _____________________________meths_______________________________ */	
	public Collection<LigneCommande> getLignesCommandeByPanier(long idPanier) {
		
		Collection<LigneCommande> lignesDeCommande = ligneCmdService.trouverLigneCommandeParPanierID(idPanier);
		return lignesDeCommande;
	}//end getLignesCommandeByPanier 
	
	
	/**
	 * recuperation des lignes de commandes associ� au panier courant 
	 * @return
	 */
	public Collection<LigneCommande> getCurrentPanierLignes(){
		return this.getLignesCommandeByPanier(panierTemp.getIdPanier());
	}//end getCurrentPanierLignes

	
	/**
	 * Ajout d'une ligne de commande via le button ajouter pannier de 'fiche-produit.xhtml'
	 * @param event
	 */
	public void ajouterArticleAuPanier(ActionEvent event) {
	
		System.out.println("Dans ajouterArticleAuPanier ....");
		
		// 1. recup context
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		// 2.recup param id du produit
		UIParameter uip = (UIParameter) event.getComponent().findComponent("pdtID");
		System.out.println("quantite dans ajouterPanier : "+quantite);	
		
		// 3. init du panier au premier ajout d'un article
		if (panierTemp ==null) {
			panierService.ajouterPanier(null);
			panierTemp = panierService.getLastBasket();
			
		}		
		
		// 4. recup du produit via DAO
		prodLigne = prodDAO.getById((long) uip.getValue() );
		double montant = (double) quantite * prodLigne.getPrixProduit();
		System.out.println("Montant ajout� au panier : "+montant);
		
		// 5.cr�ation de la ligne de cmde a partir de l'article
		ligneDeCmd = new LigneCommande(quantite, montant, prodLigne.getIdProduit(), panierTemp.getIdPanier());
		
		
		// 6. ajout de la ligne dans la bdd
		if (ligneCmdService.ajouterLigneCommande(ligneDeCmd)) {
			
			// 7. rechargement de la liste des lignes dans le panier
			lignesDeCommande =  this.getLignesCommandeByPanier(panierTemp.getIdPanier());
			
			prodLigne =null;
			
			// 8. ajout d'un message 'article ajout� au panier'
			contextJSF.addMessage(null,
					  new FacesMessage(FacesMessage.SEVERITY_INFO,
							  	       "ajout panier",
							  		   " - l'article a �t� ajout� au panier")
					  );

		}else {
			contextJSF.addMessage(null,
					  			  new FacesMessage(FacesMessage.SEVERITY_FATAL,
							  	       "ajout panier",
							  		   " - l'ajout de l'article au panier a �chou�e")
					  );
			//=> redirection vers 'accueil-gab.xhtml' (ref cle outcom dans 'faces-config.xml' )
			
		}//end else		
		
		
		
	}//end ajouterArticleAuPanier
	
	
	/**
	 * 
	 * @param event
	 */
	public void recupArticle(ActionEvent event) {
		
	}//end recupArticle

	/* _____________________________Getter/setters_______________________________ */	
	
	public Collection<LigneCommande> getLignesDeCommande() {
		return lignesDeCommande;
	}


	public void setLignesDeCommande(Collection<LigneCommande> lignesDeCommande) {
		this.lignesDeCommande = lignesDeCommande;
	}


	public Panier getPanierTemp() {
		return panierTemp;
	}


	public void setPanierTemp(Panier panierTemp) {
		this.panierTemp = panierTemp;
	}


	public LigneCommande getLigneDeCmd() {
		return ligneDeCmd;
	}


	public void setLigneDeCmd(LigneCommande ligneDeCmd) {
		this.ligneDeCmd = ligneDeCmd;
	}


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		System.out.println("quantit� entr�e : "+quantite);
		this.quantite = quantite;
	}


	public Produit getProdLigne() {
		return prodLigne;
	}


	public void setProdLigne(Produit prodLigne) {
		this.prodLigne = prodLigne;
	}



	

}//end class
