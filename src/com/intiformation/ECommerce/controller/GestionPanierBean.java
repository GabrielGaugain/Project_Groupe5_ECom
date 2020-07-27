package com.intiformation.ECommerce.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
	private ArrayList<LigneCommande> lignesDeCommande;
	private ArrayList<Produit> produitLignesDeCmd;
	private LigneCommande ligneDeCmd;
	private Panier panierTemp;
	
	private int quantitePanier;
	private Produit prodLigne;
	private Double montantTotal;



	// ====> service/dao
	IProduitDAO prodDAO;
	private ILigneCommandeService ligneCmdService;
	private IPanierService panierService;
	
	
	/* _____________________________ctors_______________________________ */
	public GestionPanierBean() {
		
		prodDAO = new ProduitDAOImpl();
		ligneCmdService = new LigneCommandeServiceImpl();
		panierService = new PanierServiceImpl();
		
		produitLignesDeCmd = new ArrayList<Produit>();
		lignesDeCommande = new ArrayList<>();
		
		panierTemp = null;
	}// end ctor vide

	
	/* _____________________________meths_______________________________ */	
	public Collection<LigneCommande> getLignesCommandeByPanier(long idPanier) {
		
		Collection<LigneCommande> lignesDeCommande = ligneCmdService.trouverLigneCommandeParPanierID(idPanier);
		return lignesDeCommande;
	}//end getLignesCommandeByPanier 
	
	
	/**
	 * recuperation des lignes de commandes associé au panier courant 
	 * @return
	 */
	public Collection<LigneCommande> getCurrentPanierLignes(){
		return this.getLignesCommandeByPanier(panierTemp.getIdPanier());
	}//end getCurrentPanierLignes

	
	public Produit getCurrentProd(LigneCommande ligne) {	
		prodLigne = prodDAO.getById(ligne.getIdProduit());
		return prodLigne;
	}
	
	/**
	 * Création d'une liste des quantités possible en fonction des stocks
	 * @return
	 */
	public List<Integer> listMaxQte(){
		
		// init de la liste
		List<Integer> listQte = new ArrayList<Integer>();
		
		// remplissage de la liste de 1 jusqu'à produit.quantite (stock dispo)
		for(int qte=1; qte<=prodLigne.getQuantite(); qte++) {
			listQte.add(qte);		
		}			
		return listQte;	
	}

	
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
		//System.out.println("quantite dans ajouterPanier : "+quantite);	
		
		// 3. init du panier au premier ajout d'un article
		if (panierTemp ==null) {
			panierService.ajouterPanier(null);
			panierTemp = panierService.getLastBasket();
			
		}		
		
		// 4. recup du produit via DAO
		prodLigne = prodDAO.getById((long) uip.getValue() );
	
		produitLignesDeCmd.add(prodLigne);
		
		double montant = (double) quantitePanier * prodLigne.getPrixProduit();
		//System.out.println("Montant ajouté au panier : "+montant);
		
		// 5.création de la ligne de cmde a partir de l'article
		ligneDeCmd = new LigneCommande(quantitePanier, montant, prodLigne.getIdProduit(), panierTemp.getIdPanier());
		
		
		// 6. ajout de la ligne dans la bdd
		if (ligneCmdService.ajouterLigneCommande(ligneDeCmd)) {
			
			// 7. rechargement de la liste des lignes dans le panier
			lignesDeCommande =  (ArrayList<LigneCommande>) this.getLignesCommandeByPanier(panierTemp.getIdPanier());
			
			// 8. reset des vars 
			prodLigne =null;
			quantitePanier=0;
			
			// 9. ajout d'un message 'article ajouté au panier'
			contextJSF.addMessage(null,
					  new FacesMessage(FacesMessage.SEVERITY_INFO,
							  	       "ajout panier",
							  		   " - l'article a été ajouté au panier")
					  );

		}else {
			// 9.bis ajout d'un message d'echec
			contextJSF.addMessage(null,
					  			  new FacesMessage(FacesMessage.SEVERITY_FATAL,
							  	       "ajout panier",
							  		   " - l'ajout de l'article au panier a échouée")
					  );
			//=> redirection vers 'accueil-gab.xhtml' (ref cle outcom dans 'faces-config.xml' )
			
		}//end else		
		
		
		
	}//end ajouterArticleAuPanier
	
	/**
	 * Méthode pour supprimer un article du panier = supprimer de la liste
	 * 	des lignesDeCmde
	 * @param event
	 */
	public void supprimerArticleDuPanier(ActionEvent event) {
		
		System.out.println("Dans supprimerArticleDuPanier ....");
		
		// 1. recup context
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		// 2.recup param id du produit
		UIParameter uidLigne = (UIParameter) event.getComponent().findComponent("idligneToDel");
		//System.out.println("quantite dans ajouterPanier : "+quantite);	
		
		// 3. suppression via le service
		if ( ligneCmdService.supprimerLigneCommande((long) uidLigne.getValue()) ) {

			// 4. rechargement de la liste des lignes dans le panier
			lignesDeCommande =  (ArrayList<LigneCommande>) this.getLignesCommandeByPanier(panierTemp.getIdPanier());
			
			// 5. ajout d'un message 'article ajouté au panier'
			contextJSF.addMessage(null,
					  new FacesMessage(FacesMessage.SEVERITY_INFO,
							  	       "suppression panier",
							  		   " - l'article a été supprimé du panier")
					  );			
		}else {
			// 5.bis ajout d'un message d'echec
			contextJSF.addMessage(null,
					  			  new FacesMessage(FacesMessage.SEVERITY_FATAL,
							  	       "suppression panier",
							  		   " - la suppression de l'article du panier a échouée")
					  );			
		}//end else
		
	}//supprimerArticleDuPanier
	
	
	public void passerLaCommande(ActionEvent event) {
		
		System.out.println("Dans passerLaCommande ....");
		
		// 1. recup context
		FacesContext contextJSF = FacesContext.getCurrentInstance();

		
		// 2. declaration d'un iterateur sur lignesDeCOmmande
		Iterator<LigneCommande> it = lignesDeCommande.iterator();
		
		// 3. parcours de la liste avec l'itérateur
		
		while(it.hasNext()) {
			ligneDeCmd = it.next();
			
			// 3.0. modification de la quantite du pd dans la bdd
			Produit prodToUpdate = prodDAO.getById( ligneDeCmd.getIdProduit() );	
			prodToUpdate.setQuantite( prodToUpdate.getQuantite() - ligneDeCmd.getQuantiteCommande() );
			prodDAO.update(prodToUpdate);				
			
			// 3.1. suppression de la ligne dans la bdd
			ligneCmdService.supprimerLigneCommande(ligneDeCmd.getIdLigneCommande());
			
			// 3.2. suppression de la ligne dans la var locale
			it.remove();
			
		}//end while
		
		 
		
		// 4. suppression du panier vide
		if( panierService.supprimerPanier(panierTemp.getIdPanier()) ) {
			
			System.out.println("Suppression panier reussie");
			
			lignesDeCommande.clear();
			
			// 5. remise a zero du panier
			panierTemp = null;
			
			//6. affichage du message
			contextJSF.addMessage(null,
					  new FacesMessage(FacesMessage.SEVERITY_INFO,
							  	       "Commande validée",
							  		   " - la commande a bien été passée")
					  );			
			
			
		}else {
			
			System.out.println("Suppression panier echouée");
			//6.bis affichage du message
			contextJSF.addMessage(null,
		  			  new FacesMessage(FacesMessage.SEVERITY_FATAL,
				  	       "Commande invalidée",
				  		   " - la suppression du panier a échouée")
		  );		
			
		}//end else
		
		panierTemp = null;
	}//end passerLaCommande
	
	


	/* _____________________________Getter/setters_______________________________ */	
	
	public ArrayList<LigneCommande> getLignesDeCommande() {
		return lignesDeCommande;
	}


	public void setLignesDeCommande(ArrayList<LigneCommande> lignesDeCommande) {
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



	public int getQuantitePanier() {
		return quantitePanier;
	}


	public void setQuantitePanier(int quantitePanier) {
		System.out.println("quantité entrée : "+quantitePanier);
		this.quantitePanier = quantitePanier;
	}


	public Produit getProdLigne() {
		return prodLigne;
	}


	public void setProdLigne(Produit prodLigne) {
		this.prodLigne = prodLigne;
	}


	public ArrayList<Produit> getProduitLignesDeCmd() {
		return produitLignesDeCmd;
	}


	public void setProduitLignesDeCmd(ArrayList<Produit> produitLignesDeCmd) {
		this.produitLignesDeCmd = produitLignesDeCmd;
	}

	public Double getMontantTotal() {
		
		if ( (!lignesDeCommande.isEmpty())&(lignesDeCommande!=null) ) {
			montantTotal = (double)Math.round( lignesDeCommande.stream().map(ligne -> ligne.getMontantCommande()).mapToDouble(montant -> (Double) montant).sum()*100) / 100;
		}else {
			montantTotal = 0.0;
		}
		
		return montantTotal;
	}


	public void setMontantTotal(Double montantTotal) {
		this.montantTotal = montantTotal;
	}




}//end class
