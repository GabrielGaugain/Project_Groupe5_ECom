package com.intiformation.ECommerce.modele;

public class Role {
	
	/* __________________ props __________________________ */

	private long idRole;
	private String nomRole;
	
	/* __________________ ctors __________________________ */
	
	public Role(long idRole, String nomRole) {
		super();
		this.idRole = idRole;
		this.nomRole = nomRole;
	}//end ctor avec id

	public Role(String nomRole) {
		super();
		this.nomRole = nomRole;
	}//end ctor sans id
	
	/* __________________ getters/setters ________________ */

	public long getIdRole() {
		return idRole;
	}

	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}

	public String getNomRole() {
		return nomRole;
	}

	public void setNomRole(String nomRole) {
		this.nomRole = nomRole;
	}

	/* __________________ toString() ________________ */
	
	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", nomRole=" + nomRole + "]";
	}
	
	
	

	
	
	
	
}//end class
