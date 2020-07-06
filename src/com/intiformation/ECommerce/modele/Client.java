package com.intiformation.ECommerce.modele;

public class Client {
	
	/* __________________ props __________________________ */
	
	private long idClient;
	private String nomClient;
	private String adresseClient;
	private String emailClient;
	private String telephoneClient;
	
	/* __________________ ctors __________________________ */
	
	public Client(long idClient, String nomClient, String adresseClient, String emailClient, String telephoneClient) {
		super();
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.adresseClient = adresseClient;
		this.emailClient = emailClient;
		this.telephoneClient = telephoneClient;
	}//end ctor avec id
	
	public Client(String nomClient, String adresseClient, String emailClient, String telephoneClient) {
		super();
		this.nomClient = nomClient;
		this.adresseClient = adresseClient;
		this.emailClient = emailClient;
		this.telephoneClient = telephoneClient;
	}//end ctor sans id
	
	/* __________________ getters/setters ________________ */

	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getAdresseClient() {
		return adresseClient;
	}

	public void setAdresseClient(String adresseClient) {
		this.adresseClient = adresseClient;
	}

	public String getEmailClient() {
		return emailClient;
	}

	public void setEmailClient(String emailClient) {
		this.emailClient = emailClient;
	}

	public String getTelephoneClient() {
		return telephoneClient;
	}

	public void setTelephoneClient(String telephoneClient) {
		this.telephoneClient = telephoneClient;
	}

	/* __________________ toString() ________________ */
	
	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nomClient=" + nomClient + ", adresseClient=" + adresseClient
				+ ", emailClient=" + emailClient + ", telephoneClient=" + telephoneClient + "]";
	}
	
	
	

}//end class
