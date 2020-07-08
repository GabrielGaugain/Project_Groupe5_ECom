package com.intiformation.ECommerce.modele;

public class Utilisateur {
	
	/* __________________ props __________________________ */
	
	private long idUtilisateur;
	private String nomUtilisateur;
	private String mdpUtilisateur;
	private long idRole;
	private boolean active;
	
	/* __________________ ctors __________________________ */
	public Utilisateur(long idUtilisateur, String nomUtilisateur, String mdpUtilisateur, long idRole, boolean active) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nomUtilisateur = nomUtilisateur;
		this.mdpUtilisateur = mdpUtilisateur;
		this.idRole = idRole;
		this.active = active;
	}//end ctor avec id

	public Utilisateur(String nomUtilisateur, String mdpUtilisateur, long idRole, boolean active) {
		super();
		this.nomUtilisateur = nomUtilisateur;
		this.mdpUtilisateur = mdpUtilisateur;
		this.idRole = idRole;
		this.active = active;
	}//end ctor sans id

	/* __________________ getters/setters ________________ */
	
	public long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getMdpUtilisateur() {
		return mdpUtilisateur;
	}

	public void setMdpUtilisateur(String mdpUtilisateur) {
		this.mdpUtilisateur = mdpUtilisateur;
	}

	public long getIdRole() {
		return idRole;
	}

	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	/* __________________ toString() ________________ */

	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", nomUtilisateur=" + nomUtilisateur
				+ ", mdpUtilisateur=" + mdpUtilisateur + ", idRole=" + idRole + ", active=" + active + "]";
	}
	
	
	
	

}//end class
