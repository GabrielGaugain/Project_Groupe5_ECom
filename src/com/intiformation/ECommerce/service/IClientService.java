package com.intiformation.ECommerce.service;

import java.util.List;

import com.intiformation.ECommerce.modele.Client;

public interface IClientService {
	
	public boolean ajouterClient (Client pClient);
	public boolean modifierClient (Client pClient);
	public boolean supprimerClient (long pIdClient);
	public List<Client> afficherTousLesClients();	
	public Client trouverClientParId(int pIdClient);

}
