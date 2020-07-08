package com.intiformation.ECommerce.modele;

public class Photo {

	/* __________________ props __________________________ */
	
	private long idPhoto ;
	private String urlPhoto;
	private String nomPhoto ;
	
	/* __________________ ctors __________________________ */
	
	public Photo(long idPhoto, String urlPhoto, String nomPhoto) {
		super();
		this.idPhoto = idPhoto;
		this.urlPhoto = urlPhoto;
		this.nomPhoto = nomPhoto;
	}//end ctor avec id
	
	public Photo(String urlPhoto, String nomPhoto) {
		super();
		this.urlPhoto = urlPhoto;
		this.nomPhoto = nomPhoto;
	}//end ctor sans id
	
	
	/* __________________ getters/setters ________________ */

	public long getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(long idPhoto) {
		this.idPhoto = idPhoto;
	}

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
		return "Photo [idPhoto=" + idPhoto + ", urlPhoto=" + urlPhoto + ", nomPhoto=" + nomPhoto + "]";
	}
	
	
	
}//end class
