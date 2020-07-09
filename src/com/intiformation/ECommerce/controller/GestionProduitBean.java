package com.intiformation.ECommerce.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.intiformation.ECommerce.dao.IProduitDAO;
import com.intiformation.ECommerce.dao.ProduitDAOImpl;
import com.intiformation.ECommerce.modele.Produit;

@ManagedBean(name = "produitBean")
@SessionScoped
public class GestionProduitBean implements Serializable {

	/* _____________________________props_______________________________ */

	IProduitDAO prodDAO;
	private Collection<Produit> listeProdBDD;
	private Collection<Produit> listeProdCateBDD;
	private Collection<Produit> listeProdMCBDD;
	private Produit produit;
	private int idCategorie ;
	private String motCle ;

	/* _____________________________ctors_______________________________ */
	public GestionProduitBean() {
		prodDAO = new ProduitDAOImpl();
	}// end ctor vide

	
	/* _____________________________meths_______________________________ */

	public Collection<Produit> getListeProdCate() {
		return listeProdCateBDD = prodDAO.getAllProduitByCategorie(idCategorie);
	}
	
	public Collection<Produit> getListeProdMC() {
		 return listeProdMCBDD = prodDAO.getByMotCle(motCle) ;
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

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public Collection<Produit> getListeProd() {
		 return listeProdBDD = prodDAO.getAll();
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
	
	

}// end class
