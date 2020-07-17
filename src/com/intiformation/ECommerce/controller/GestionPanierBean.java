package com.intiformation.ECommerce.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.catalina.ha.backend.CollectedInfo;

import com.intiformation.ECommerce.dao.ProduitDAOImpl;
import com.intiformation.ECommerce.modele.LigneCommande;
import com.intiformation.ECommerce.modele.Panier;
import com.intiformation.ECommerce.service.ILigneCommandeService;
import com.intiformation.ECommerce.service.IPanierService;
import com.intiformation.ECommerce.service.LigneCommandeServiceImpl;
import com.intiformation.ECommerce.service.PanierServiceImpl;

@ManagedBean(name = "panierBean")
@SessionScoped
public class GestionPanierBean implements Serializable {
	
	/* _____________________________props_______________________________ */
	private Collection<LigneCommande> lignesDeCommande;
	private Panier panierTemp;
	private ILigneCommandeService ligneCmdService;
	private IPanierService panierService;
	
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

	public void addLigneCommandeToPanier(LigneCommande pLigne) {
		
		// init du panier au premier ajout d'un article
		if (panierTemp ==null) {
			panierService.ajouterPanier(null);
			panierTemp = panierService.getLastBasket();
		}
		// ajout de la ligne dans la bdd
		ligneCmdService.ajouterLigneCommande(pLigne);
		// rechargement de la liste des lignes dans le panier
		lignesDeCommande =  this.getLignesCommandeByPanier(panierTemp.getIdPanier());
				
	}//end addLigneCommandeToPanier

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
	
	
	
	
	

}//end class
