package com.intiformation.ECommerce.modele;

public class Categorie {
	
	
/* __________________ props __________________________ */
	
	private long idCategorie ;
	private String nomCategorie ;
	private String descriptionCategorie;
	private String urlImageCategorie;
	
	
	/* __________________ ctors __________________________ */

	public Categorie(long idCategorie, String nomCategorie, String descriptionCategorie, String urlImageCategorie) {
		super();
		this.idCategorie = idCategorie;
		this.nomCategorie = nomCategorie;
		this.descriptionCategorie = descriptionCategorie;
		this.urlImageCategorie = urlImageCategorie;
	}//end ctor avec id

	
	public Categorie(String nomCategorie, String descriptionCategorie, String urlImageCategorie) {
		super();
		this.nomCategorie = nomCategorie;
		this.descriptionCategorie = descriptionCategorie;
		this.urlImageCategorie = urlImageCategorie;
	}//end ctor sans id



	/* __________________ getters/setters ________________ */
	
	public Categorie() {
	}


	public long getIdCategorie() {
		return idCategorie;
	}


	public void setIdCategorie(long idCategorie) {
		this.idCategorie = idCategorie;
	}


	public String getNomCategorie() {
		return nomCategorie;
	}


	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}


	public String getDescriptionCategorie() {
		return descriptionCategorie;
	}


	public void setDescriptionCategorie(String descriptionCategorie) {
		this.descriptionCategorie = descriptionCategorie;
	}


	public String getUrlImageCategorie() {
		return urlImageCategorie;
	}


	public void setUrlImageCategorie(String urlImageCategorie) {
		this.urlImageCategorie = urlImageCategorie;
	}


	/* __________________ toString() ________________ */
	
	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", nomCategorie=" + nomCategorie + ", descriptionCategorie="
				+ descriptionCategorie + ", urlImageCategorie=" + urlImageCategorie + "]";
	}
	
	
	
}//end class