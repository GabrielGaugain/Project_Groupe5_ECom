package com.intiformation.ECommerce.modele;

import java.util.Date;

public class Commande {
	
	
	/* __________________ props __________________________ */
	
	private long idCommande;
	private Date dateCommande;
	private long idClient;
	
	/* __________________ ctors __________________________ */
	
	
	public Commande(long idCommande, Date dateCommande, long idClient) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
		this.idClient = idClient;
	}//end ctor avec id


	public Commande(Date dateCommande, long idClient) {
		super();
		this.dateCommande = dateCommande;
		this.idClient = idClient;
	}//end ctor sans id

	
	/* __________________ getters/setters ________________ */

	public long getIdCommande() {
		return idCommande;
	}


	public void setIdCommande(long idCommande) {
		this.idCommande = idCommande;
	}


	public Date getDateCommande() {
		return dateCommande;
	}


	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}


	public long getIdClient() {
		return idClient;
	}


	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}

	/* __________________ toString() ________________ */

	@Override
	public String toString() {
		return "Commande [idCommande=" + idCommande + ", dateCommande=" + dateCommande + ", idClient=" + idClient + "]";
	}
	

	
	
}//end class
