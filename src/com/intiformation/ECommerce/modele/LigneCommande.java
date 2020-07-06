package com.intiformation.ECommerce.modele;

public class LigneCommande {
	
	/* __________________ props __________________________ */
	
	private long idLigneCommande;
	private int quantiteCommande;
	private double montantCommande;
	private long idProduit;
	private long idCommande;
	private long idPanier;
	
	
	/* __________________ ctors __________________________ */
	
	public LigneCommande(long idLigneCommande, int quantiteCommande, double montantCommande, long idProduit,
			long idCommande, long idPanier) {
		super();
		this.idLigneCommande = idLigneCommande;
		this.quantiteCommande = quantiteCommande;
		this.montantCommande = montantCommande;
		this.idProduit = idProduit;
		this.idCommande = idCommande;
		this.idPanier = idPanier;
	}//end ctor avec id
	
	
	public LigneCommande(int quantiteCommande, double montantCommande, long idProduit, long idCommande, long idPanier) {
		super();
		this.quantiteCommande = quantiteCommande;
		this.montantCommande = montantCommande;
		this.idProduit = idProduit;
		this.idCommande = idCommande;
		this.idPanier = idPanier;
	}//end ctor sans id

	
	/* __________________ getters/setters ________________ */

	public long getIdLigneCommande() {
		return idLigneCommande;
	}


	public void setIdLigneCommande(long idLigneCommande) {
		this.idLigneCommande = idLigneCommande;
	}


	public int getQuantiteCommande() {
		return quantiteCommande;
	}


	public void setQuantiteCommande(int quantiteCommande) {
		this.quantiteCommande = quantiteCommande;
	}


	public double getMontantCommande() {
		return montantCommande;
	}


	public void setMontantCommande(double montantCommande) {
		this.montantCommande = montantCommande;
	}


	public long getIdProduit() {
		return idProduit;
	}


	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}


	public long getIdCommande() {
		return idCommande;
	}


	public void setIdCommande(long idCommande) {
		this.idCommande = idCommande;
	}


	public long getIdPanier() {
		return idPanier;
	}


	public void setIdPanier(long idPanier) {
		this.idPanier = idPanier;
	}

	/* __________________ toString() ________________ */
	
	@Override
	public String toString() {
		return "LigneCommande [idLigneCommande=" + idLigneCommande + ", quantiteCommande=" + quantiteCommande
				+ ", montantCommande=" + montantCommande + ", idProduit=" + idProduit + ", idCommande=" + idCommande
				+ ", idPanier=" + idPanier + "]";
	}
	
	
	

}//end class
