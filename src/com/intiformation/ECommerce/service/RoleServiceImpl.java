package com.intiformation.ECommerce.service;

import java.util.List;

import com.intiformation.ECommerce.dao.IRoleDAO;
import com.intiformation.ECommerce.dao.RoleDAOImpl;
import com.intiformation.ECommerce.modele.Role;

public class RoleServiceImpl implements IRoleService {
	
private IRoleDAO roleDAO;
	
	public RoleServiceImpl() {
		roleDAO = new RoleDAOImpl();
	}//end ctor

	@Override
	public boolean ajouterRole(Role pRole) {
		return roleDAO.add(pRole);
	}

	@Override
	public boolean modifierRole(Role pRole) {
		return roleDAO.update(pRole);
	}

	@Override
	public boolean supprimerRole(long pIdRole) {
		return roleDAO.delete(pIdRole);
	}

	@Override
	public List<Role> afficherTousLesRoles() {
		return roleDAO.getAll();
	}

	@Override
	public Role trouverRoleParId(int pIdRole) {
		return roleDAO.getById(pIdRole);
	}

}
