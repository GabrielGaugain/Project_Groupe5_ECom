package com.intiformation.ECommerce.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.catalina.ha.backend.CollectedInfo;

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
	private Collection<LigneCommande> lignesDeCommande;
	private LigneCommande ligneDeCmd;
	private Panier panierTemp;
	private ILigneCommandeService ligneCmdService;
	private IPanierService panierService;
	private int quantite;
	private Produit prod;
	/* _____________________________ctors_______________________________ */
	public GestionPanierBean() {
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


	public void ajouterArticleAuPanier(Produit pdt) {
		System.out.println("Dans ajouterArticleAuPanier ....");
		
		// init du panier au premier ajout d'un article
		if (panierTemp ==null) {
			panierService.ajouterPanier(null);
			panierTemp = panierService.getLastBasket();
		}		
		
		//cr�ation de la ligne de cmde a partir de l'article
		
		
		
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
		System.out.println("quantit� entr�e : "+quantite);
		this.quantite = quantite;
	}


	public Produit getProd() {
		return prod;
	}


	public void setProd(Produit prod) {
		
		this.prod = prod;
	}
	
	
	
	
	

}//end class
