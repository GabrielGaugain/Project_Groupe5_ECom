package com.intiformation.ECommerce.test;

import com.intiformation.ECommerce.service.CategorieServiceImpl;
import com.intiformation.ECommerce.service.ClientServiceImpl;
import com.intiformation.ECommerce.service.CommandeServiceImpl;
import com.intiformation.ECommerce.service.ICategorieService;

import java.sql.Date;

import com.intiformation.ECommerce.dao.ICategorieDAO;
import com.intiformation.ECommerce.service.IClientService;
import com.intiformation.ECommerce.service.ICommandeService;
import com.intiformation.ECommerce.service.ILigneCommandeService;
import com.intiformation.ECommerce.service.IPanierService;
import com.intiformation.ECommerce.service.IProduitService;
import com.intiformation.ECommerce.service.IRoleService;
import com.intiformation.ECommerce.service.IUtilisateurService;
import com.intiformation.ECommerce.service.LigneCommandeServiceImpl;
import com.intiformation.ECommerce.service.PanierServiceImpl;
import com.intiformation.ECommerce.service.ProduitServiceImpl;
import com.intiformation.ECommerce.service.RoleServiceImpl;
import com.intiformation.ECommerce.service.UtilisateurServiceImpl;
import com.intiformation.ECommerce.modele.*;
/**
 * Classe de test des DAO/SERVICES
 * 
 * @author gabri
 *
 */
public class Test {
	
	private IClientService clientService;
	private IUtilisateurService userService;
	private ICategorieService categorieService;
	private ILigneCommandeService ligneService;
	private IPanierService panierService;
	private IProduitService prodService;
	private ICommandeService commService;
	private IRoleService roleService;
	
	public void Test() {
		clientService = new ClientServiceImpl();
		userService = new UtilisateurServiceImpl();
		categorieService = new CategorieServiceImpl();
		ligneService = new LigneCommandeServiceImpl();
		panierService = new PanierServiceImpl();
		prodService = new ProduitServiceImpl();
		commService = new CommandeServiceImpl();
		roleService = new RoleServiceImpl();		
	}
	
	
	
	public void main(String[] args) {
		
		System.out.println("");
		
		System.out.println("creation d'un nouveau role ");
		roleService.ajouterRole(	new Role("VIP")	);	
		System.out.println("=========> creation d'un nouveau role REUSSI ");		
		
		
		System.out.println("creation d'un nouveau client ");
		clientService.ajouterClient(	new Client("Michelle","12 rue du bout perdu","moncul@gmail.com","0202020202")	);	
		System.out.println("=========> creation d'un nouveau client REUSSI ");
		
		System.out.println("creation d'un nouveau user ");
		userService.ajouterUtilisateur( 	new Utilisateur("Gabrieldu14","password",1,true ) 	);	
		System.out.println("=========> creation d'un nouveau user REUSSI ");
		
		System.out.println("creation d'un nouveau categorei ");
		categorieService.ajouterCategorie(	new Categorie("Ordinateurs","Ordinateurs fixes et portables",null)		);	
		System.out.println("=========> creation d'un nouveau categorie REUSSI ");		
		
		
		System.out.println("creation d'un nouveau commande ");
		commService.ajouterCommande(	new Commande(new Date(04,05,2020) ,1)	);	
		System.out.println("=========> creation d'un nouveau commande REUSSI ");
		
		
		System.out.println("creation d'un nouveau Panier ");
		panierService.ajouterPanier(	new Panier()	);	
		System.out.println("=========> creation d'un nouveau ligneCommande REUSSI ");
		
		System.out.println("creation d'un nouveau Produit ");
		prodService.ajouterProduit(	new Produit("laptot Asus","Ordinateur portabkle pas trop mal mais cher ", 799.99,false, null,(long)2 )	);	
		System.out.println("=========> creation d'un nouveau ligneCommande REUSSI ");		
		
		
		System.out.println("creation d'un nouveau ligneCommande ");
		ligneService.ajouterLigneCommande(	new LigneCommande(3,300.99, 4, 1, 1 )	);	
		System.out.println("=========> creation d'un nouveau ligneCommande REUSSI ");		
		
		
		
	}//end main

}//end class
