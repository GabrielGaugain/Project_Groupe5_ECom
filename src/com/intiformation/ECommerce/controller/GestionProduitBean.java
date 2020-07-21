package com.intiformation.ECommerce.controller;

import java.io.Serializable;
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

	IProduitDAO prodDAO;
	private Collection<Categorie> listeCateBDD;
	private Collection<Produit> listeProdBDD;
	private Collection<Produit> listeProdCateBDD;
	private Collection<Produit> listeProdMCBDD;
	private Produit produit;
	private Produit SelectedProd;
	private long idCategorie ;
	private String motCle ;
	private boolean coche;
	
	/* _____________________________ctors_______________________________ */
	public GestionProduitBean() {
		prodDAO = new ProduitDAOImpl();
		listeProdMCBDD = prodDAO.getAll();
		motCle = "";
		
	}// end ctor vide

	
	/* _____________________________meths_______________________________ */

	public Collection<Categorie> getListeCate() {
		ICategorieDAO catDAO = new CategorieDAOImpl();
		return listeCateBDD = catDAO.getAll();
	}
	
	public Collection<Produit> getListeProd() {
		return listeProdBDD = prodDAO.getAll();
	}
	
	public Collection<Produit> getListeProdCate() {
		return listeProdCateBDD = prodDAO.getAllProduitByCategorie(idCategorie);
	}
	
	public Collection<Produit> getListeProdMC() {
		 return listeProdMCBDD = prodDAO.getByMotCle(motCle) ;
	}
	public void addMessage() {
        String summary = coche ? "Produit ajouté à la sélection" : "Produit retiré de la sélection";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
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

	public IProduitDAO getProdDAO() {
		return prodDAO;
	}

	public void setProdDAO(IProduitDAO prodDAO) {
		this.prodDAO = prodDAO;
	}

	public Collection<Produit> getListeProdBDD() {
		return listeProdBDD;
	}

	public void setListeProdBDD(Collection<Produit> listeProdBDD) {
		this.listeProdBDD = listeProdBDD;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Collection<Produit> getListeProdCateBDD() {
		return listeProdCateBDD;
	}
	
	public void setListeProdCateBDD(Collection<Produit> listeProdCateBDD) {
		this.listeProdCateBDD = listeProdCateBDD;
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
		this.motCle = motCle;
	}


	public Produit getSelectedProd() {
		return SelectedProd;
	}


	public void setSelectedProd(Produit selectedProd) {
		SelectedProd = selectedProd;
	}


	public boolean isCoche() {
		return coche;
	}


	public void setCoche(boolean coche) {
		this.coche = coche;
	}

}// end class
