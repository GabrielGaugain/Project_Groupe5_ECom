package com.intiformation.ECommerce.modele;

public class Produit {
	
	/* __________________ props __________________________ */
	
	private long idProduit ;
	private String nomProduit ;
	private String descriptionProduit;
	private double prixProduit;
	private boolean selectionne;
	private String urlImageProduit;
	private Long idCategorie;
	

	
	/* __________________ ctors __________________________ */
	
	
	public Produit(long idProduit, String nomProduit, String descriptionProduit, double prixProduit,
			boolean selectionne, String urlImageProduit, Long idCategorie) {
		super();
		this.idProduit = idProduit;
		this.nomProduit = nomProduit;
		this.descriptionProduit = descriptionProduit;
		this.prixProduit = prixProduit;
		this.selectionne = selectionne;
		this.urlImageProduit = urlImageProduit;
		this.idCategorie = idCategorie;
	}//end ctor avec id



	public Produit(String nomProduit, String descriptionProduit, double prixProduit, boolean selectionne,
			String urlImageProduit, Long idCategorie) {
		super();
		this.nomProduit = nomProduit;
		this.descriptionProduit = descriptionProduit;
		this.prixProduit = prixProduit;
		this.selectionne = selectionne;
		this.urlImageProduit = urlImageProduit;
		this.idCategorie = idCategorie;
	}//end ctor sans id

	/* __________________ getters/setters ________________ */

	public long getIdProduit() {
		return idProduit;
	}



	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}



	public String getNomProduit() {
		return nomProduit;
	}



	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}



	public String getDescriptionProduit() {
		return descriptionProduit;
	}



	public void setDescriptionProduit(String descriptionProduit) {
		this.descriptionProduit = descriptionProduit;
	}



	public double getPrixProduit() {
		return prixProduit;
	}



	public void setPrixProduit(double prixProduit) {
		this.prixProduit = prixProduit;
	}



	public boolean isSelectionne() {
		return selectionne;
	}



	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}



	public String getUrlImageProduit() {
		return urlImageProduit;
	}



	public void setUrlImageProduit(String urlImageProduit) {
		this.urlImageProduit = urlImageProduit;
	}



	public Long getIdCategorie() {
		return idCategorie;
	}



	public void setIdCategorie(Long idCategorie) {
		this.idCategorie = idCategorie;
	}

	/* __________________ toString() ________________ */

	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", nomProduit=" + nomProduit + ", descriptionProduit="
				+ descriptionProduit + ", prixProduit=" + prixProduit + ", selectionne=" + selectionne
				+ ", urlImageProduit=" + urlImageProduit + ", idCategorie=" + idCategorie + "]";
	}
	
	
	

}//end class
