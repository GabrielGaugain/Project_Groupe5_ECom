package com.intiformation.ECommerce.controller;

import java.io.Serializable;
import java.util.Collection;

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
import com.intiformation.ECommerce.service.ILigneCommandeService;
import com.intiformation.ECommerce.service.IPanierService;
import com.intiformation.ECommerce.service.LigneCommandeServiceImpl;
import com.intiformation.ECommerce.service.PanierServiceImpl;

@ManagedBean(name = "panierBean")
@SessionScoped
public class GestionPanierBean implements Serializable {
	
	/* _____________________________props_______________________________ */
	IProduitDAO prodDAO;
	private Collection<LigneCommande> lignesDeCommande;
	private LigneCommande ligneDeCmd;
	private Panier panierTemp;
	private ILigneCommandeService ligneCmdService;
	private IPanierService panierService;
	private int quantite;
	private Produit prod;
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
	
	public Collection<LigneCommande> getCurrentPanierLignes(){
		return this.getLignesCommandeByPanier(panierTemp.getIdPanier());
	}//end getCurrentPanierLignes


	public void ajouterArticleAuPanier(ActionEvent event) {
		
		/**
		 * Normalement lors du click sur ajouter au panier on devrait avoir ça d'afficher dans la console 
		 * mais pas là => ça veut pas lancer la méthode je sais pas pk
		 */
		System.out.println("Dans ajouterArticleAuPanier ....");
		
		// 1. recup context
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		// 2.recup param id du produit
		UIParameter uip = (UIParameter) event.getComponent().findComponent("pdtID");
		System.out.println("Dans ajouterArticleAuPanier ....");
		
		
		
		// 3. init du panier au premier ajout d'un article
		if (panierTemp ==null) {
			panierService.ajouterPanier(null);
			panierTemp = panierService.getLastBasket();
		}		
		
		// 4. recup du produit via DAO
		prod = prodDAO.getById((long) uip.getValue() );
		double montant = (double) quantite * prod.getPrixProduit();
		
		// 5.création de la ligne de cmde a partir de l'article
		// ====> pb idCommande on peut ajouter des lignes de cmde au panier sans etre log donc ça va pas
		//ligneDeCmd = new LigneCommande(quantite, montant, prod.getIdProduit(), idCommande, panierTemp.getIdPanier());
		
		
		// ajout de la ligne dans la bdd
		ligneCmdService.ajouterLigneCommande(ligneDeCmd);
		// rechargement de la liste des lignes dans le panier
		lignesDeCommande =  this.getLignesCommandeByPanier(panierTemp.getIdPanier());
		
	}//end ajouterArticleAuPanier

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
		System.out.println("quantité entrée : "+quantite);
		this.quantite = quantite;
	}


	public Produit getProd() {
		return prod;
	}


	public void setProd(Produit prod) {
		
		this.prod = prod;
	}
	
	
	
	
	

}//end class
