package com.intiformation.ECommerce.service;

import java.util.List;

import com.intiformation.ECommerce.modele.Role;

public interface IRoleService {
	public boolean ajouterRole (Role pRole);
	public boolean modifierRole (Role pRole);
	public boolean supprimerRole (long pIdRole);
	public List<Role> afficherTousLesRoles();	
	public Role trouverRoleParId(int pIdRole);
	
}
