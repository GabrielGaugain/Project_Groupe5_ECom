package com.intiformation.ECommerce.modele;

public class Photo {

	/* __________________ props __________________________ */
	
	private String urlPhoto;
	private String nomPhoto ;
	
	/* __________________ ctors __________________________ */
	

	
	public Photo(String urlPhoto, String nomPhoto) {
		super();
		this.urlPhoto = urlPhoto;
		this.nomPhoto = nomPhoto;
	}//end ctor sans id
	
	
	/* __________________ getters/setters ________________ */


	public String getUrlPhoto() {
		return urlPhoto;
	}

	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	public String getNomPhoto() {
		return nomPhoto;
	}

	public void setNomPhoto(String nomPhoto) {
		this.nomPhoto = nomPhoto;
	}

	/* __________________ toString() ________________ */
	
	@Override
	public String toString() {
		return "Photo [urlPhoto=" + urlPhoto + ", nomPhoto=" + nomPhoto + "]";
	}
	
	
	
}//end class
