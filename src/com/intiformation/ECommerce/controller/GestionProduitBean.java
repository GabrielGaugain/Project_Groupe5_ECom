package com.intiformation.ECommerce.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.intiformation.ECommerce.dao.CategorieDAOImpl;
import com.intiformation.ECommerce.dao.ICategorieDAO;
import com.intiformation.ECommerce.dao.IProduitDAO;
import com.intiformation.ECommerce.dao.ProduitDAOImpl;
import com.intiformation.ECommerce.modele.Categorie;
import com.intiformation.ECommerce.modele.Produit;

@ManagedBean(name = "produitBean")
@SessionScoped
public class GestionProduitBean implements Serializable {

	/* _____________________________props_______________________________ */
	
	/*____ Services/DAO ____*/
	IProduitDAO prodDAO;
	private Collection<Produit> listeProdDisplayed;
	private Collection<Categorie> listeCateBDD;
	
	private Collection<Produit> listeProdMCBDD;
	
	
	/*_______VARS_____*/
	private Produit produit;
	private Produit SelectedProd;
	private long idCategorie ;
	private String motCle ;

	
	/* _____________________________ctors_______________________________ */
	public GestionProduitBean() {
		prodDAO = new ProduitDAOImpl();
		listeProdDisplayed =  prodDAO.getAll();
		motCle = "";
		
	}// end ctor vide

	
	/* _____________________________meths_______________________________ */

	public Collection<Categorie> getListeCate() {
		ICategorieDAO catDAO = new CategorieDAOImpl();
		return listeCateBDD =  catDAO.getAll();
	}
	
	/**
	 * pour la gestion des quantité dans les selectonemenu
	 * @return
	 */
	public List<Integer> listMaxQte(){
		
		// init de la liste
		List<Integer> listQte = new ArrayList<Integer>();
		
		// remplissage de la liste de 1 jusqu'à produit.quantite (stock dispo)
		for(int qte=1; qte<=produit.getQuantite(); qte++) {
			listQte.add(qte);
			
		}	
		
		return listQte;
		
	}	
	
	/**
	 * retourn les produits par les mots clés entrés dans la barre de recherche
	 * @return
	 */
	public Collection<Produit> getListeProdByMC() {
		 return listeProdDisplayed = prodDAO.getByMotCle(motCle) ;
	}
	
	public void getAllProd() {
		listeProdDisplayed = prodDAO.getAll();
	}
	
	
	/**
	 * Recup de la liste des prod par categorie 
	 */
	public void getListeProdMCCate(ActionEvent event) {
		// 1. recup context
		FacesContext contextJSF = FacesContext.getCurrentInstance();		
		// 2.recup param id du produit
		
		UIParameter uCat = (UIParameter) event.getComponent().findComponent("idCat");
		long idCategorie = (long) uCat.getValue();
		
		listeProdDisplayed = prodDAO.getAllProduitByCategorie(idCategorie);
	} 	
	
	public void VisuFicheProd(ActionEvent event) {
		
		// 1. recup context
		FacesContext contextJSF = FacesContext.getCurrentInstance();
				
		// 2.recup param id du produit
		UIParameter uProd = (UIParameter) event.getComponent().findComponent("idProdSelected");
		long idProduit = (long) uProd.getValue();
		
		this.produit = prodDAO.getById(idProduit);

		
	}
	
	/* _______________________ GETTERS/SETTERS ________________________________ */

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	

	public long getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(long idCategorie) {
		this.idCategorie = idCategorie;
	}



	public Collection<Produit> getListeProdMCBDD() {
		return listeProdMCBDD;
	}


	public void setListeProdMCBDD(Collection<Produit> listeProdMCBDD) {
		this.listeProdMCBDD = listeProdMCBDD;
	}


	public String getMotCle() {
		return motCle;
	}


	public void setMotCle(String motCle) {
		listeProdDisplayed = prodDAO.getByMotCle(motCle) ;
		this.motCle = motCle;
	}


	public Produit getSelectedProd() {
		return SelectedProd;
	}


	public void setSelectedProd(Produit selectedProd) {
		SelectedProd = selectedProd;
	}


	public Collection<Produit> getListeProdDisplayed() {
		return listeProdDisplayed;
	}


	public void setListeProdDisplayed(Collection<Produit> listeProdDisplayed) {
		this.listeProdDisplayed = listeProdDisplayed;
	}




}// end class
