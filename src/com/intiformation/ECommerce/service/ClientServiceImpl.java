package com.intiformation.ECommerce.service;


import java.util.List;

import com.intiformation.ECommerce.dao.ClientDAOImpl;
import com.intiformation.ECommerce.dao.IClientDAO;
import com.intiformation.ECommerce.modele.Client;

public class ClientServiceImpl implements IClientService {

	private IClientDAO clientDAO;
	
	public ClientServiceImpl() {
		clientDAO = new ClientDAOImpl();
	}//end ctor

	@Override
	public boolean ajouterClient(Client pClient) {
		return clientDAO.add(pClient);
	}

	@Override
	public boolean modifierClient(Client pClient) {
		return clientDAO.update(pClient);
	}

	@Override
	public boolean supprimerClient(long pIdClient) {
		return clientDAO.delete(pIdClient);
	}

	@Override
	public List<Client> afficherTousLesClients() {
		return clientDAO.getAll();
	}

	@Override
	public Client trouverClientParId(int pIdClient) {
		return clientDAO.getById(pIdClient);
	}
	

}
