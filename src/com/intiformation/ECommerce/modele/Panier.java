package com.intiformation.ECommerce.modele;

public class Panier {
	
	/* __________________ props __________________________ */
	
	private long idPanier;
	
	/* __________________ ctors __________________________ */

	public Panier(long idPanier) {
		super();
		this.idPanier = idPanier;
	}//end ctor avec id

	public Panier() {
		super();
	}//end ctor sans id
	
	/* __________________ getters/setters ________________ */

	public long getIdPanier() {
		return idPanier;
	}

	public void setIdPanier(long idPanier) {
		this.idPanier = idPanier;
	}

	
	/* __________________ toString() ________________ */
	
	@Override
	public String toString() {
		return "Panier [idPanier=" + idPanier + "]";
	}
	
	

}//end class
