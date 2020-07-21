package com.intiformation.ECommerce.test;

import com.intiformation.ECommerce.service.CategorieServiceImpl;
import com.intiformation.ECommerce.service.ClientServiceImpl;
import com.intiformation.ECommerce.service.CommandeServiceImpl;
import com.intiformation.ECommerce.service.ICategorieService;

import java.sql.Date;
import java.util.List;

import com.intiformation.ECommerce.service.IClientService;
import com.intiformation.ECommerce.service.ICommandeService;
import com.intiformation.ECommerce.service.ILigneCommandeService;
import com.intiformation.ECommerce.service.IPanierService;
import com.intiformation.ECommerce.service.IPhotoService;
import com.intiformation.ECommerce.service.IProduitService;
import com.intiformation.ECommerce.service.IRoleService;
import com.intiformation.ECommerce.service.IUtilisateurService;
import com.intiformation.ECommerce.service.LigneCommandeServiceImpl;
import com.intiformation.ECommerce.service.PanierServiceImpl;
import com.intiformation.ECommerce.service.PhotoServiceImpl;
import com.intiformation.ECommerce.service.ProduitServiceImpl;
import com.intiformation.ECommerce.service.RoleServiceImpl;
import com.intiformation.ECommerce.service.UtilisateurServiceImpl;
import com.intiformation.ECommerce.modele.*;
/**
 * Classe de test des DAO/SERVICES
 * 
 * @author gabri et cam
 *
 */
public class Test {
	

	
	
	public static void main(String[] args) {
	
		IClientService clientService = new ClientServiceImpl();
		IUtilisateurService userService = new UtilisateurServiceImpl();
		ICategorieService categorieService = new CategorieServiceImpl();
		ILigneCommandeService ligneService = new LigneCommandeServiceImpl();
		IPanierService panierService = new PanierServiceImpl();
		IProduitService prodService = new ProduitServiceImpl();
		ICommandeService commService = new CommandeServiceImpl();
		IRoleService roleService = new RoleServiceImpl();		
		IPhotoService photoService = new PhotoServiceImpl();		
		
		
		
		
		// TEST AJOUT
		
		System.out.println("=============================================================================================================");
		
		System.out.println("creation d'une nouvelle photo ");
		photoService.ajouterPhoto(	new Photo("/Project_Groupe5_ECom/WebContent/resources/images/test.jpg","photo test")	);	
		System.out.println("=========> creation  d'une nouvelle photo REUSSI ");
		
		System.out.println("creation d'un nouveau role ");
		roleService.ajouterRole(new Role("VIP")	);	
		System.out.println("=========> creation d'un nouveau role REUSSI ");		
		
		System.out.println("creation d'un nouveau client ");
		clientService.ajouterClient(	new Client("Michelle","12 rue du bout perdu","mich@gmail.com","0202020202")	);	
		System.out.println("=========> creation d'un nouveau client REUSSI ");
		
		System.out.println("creation d'un nouveau user ");
		userService.ajouterUtilisateur( 	new Utilisateur("Gabrieldu14","password",1,true ) 	);	
		System.out.println("=========> creation d'un nouveau user REUSSI ");
		
		System.out.println("creation d'un nouveau categorei ");
		categorieService.ajouterCategorie(	new Categorie("Ordinateurs","Ordinateurs fixes et portables",null)		);	
		System.out.println("=========> creation d'un nouveau categorie REUSSI ");		
		
		
		System.out.println("creation d'un nouveau commande ");
		commService.ajouterCommande(new Commande(new Date(04/05/2020) ,1)	);	
		System.out.println("=========> creation d'un nouveau commande REUSSI ");
		
		
		System.out.println("creation d'un nouveau Panier ");
		panierService.ajouterPanier(	new Panier()	);	
		System.out.println("=========> creation d'un nouveau ligneCommande REUSSI ");
		
		System.out.println("creation d'un nouveau Produit ");
		prodService.ajouterProduit(	new Produit("laptot Asus","Ordinateur portabkle pas trop mal mais cher ", 799.99,4,false, null,(long)1 )	);	
		System.out.println("=========> creation d'un nouveau ligneCommande REUSSI ");		
		
		
		System.out.println("creation d'un nouveau ligneCommande ");
		ligneService.ajouterLigneCommande(	new LigneCommande(3,300.99, 1, 1, 1 )	);	
		System.out.println("=========> creation d'un nouveau ligneCommande REUSSI ");
		

		
		System.out.println("=============================================================================================================");
		

		
		// TEST MODIF
		
		
		System.out.println("modif d'une categorie ");
		categorieService.modifierCategorie(new Categorie(1,"Ordinateurs MODIF","Ordinateurs fixes et portables",null));	
		System.out.println("=========> modif d'une categorie reussie");	
		
		System.out.println("modif d'un client ");
		clientService.modifierClient(new Client(1,"Michelle MODIF","12 rue du bout perdu","mich@gmail.com","0202020202"));	
		System.out.println("=========> modif d'un client reussie");	
		
		System.out.println("modif d'une commande ");
		commService.modifierCommande(new Commande(1,new Date(04/05/2050),1));	
		System.out.println("=========> modif d'une commande reussie");
		
		System.out.println("modif d'une lignecommande ");
		ligneService.modifierLigneCommande(new LigneCommande(1,3,300.22, 1, 1, 1 ));	
		System.out.println("=========> modif d'une lignecommande reussie");
		
		System.out.println("modif d'un produit ");
		prodService.modifierProduit(new Produit(1,"laptot Asus MODIF","Ordinateur portabkle pas trop mal mais cher ", 799.99,10,false, null,(long)1 ));	
		System.out.println("=========> modif d'un produit  reussie");
		
		System.out.println("modif d'un role ");
		roleService.modifierRole(new Role(1,"MODIF"));	
		System.out.println("=========> modif d'un role reussie");
		
		System.out.println("modif d'un utilisateur ");
		userService.modifierUtilisateur(new Utilisateur(1,"Gabrieldu14 MODIF","password",1,true ));	
		System.out.println("=========> modif d'un utilisateur reussie");
		
		System.out.println("modif d'une photo ");
		photoService.modifierPhoto(new Photo("/Project_Groupe5_ECom/WebContent/resources/images/test.jpg","photo test MODIF"));	
		System.out.println("=========> modif d'un photo reussie");
		
		System.out.println("=============================================================================================================");
		
		// TEST AFFICHAGE byID
		
		
		System.out.println("affichage d'une categorie ");
		Categorie cate = categorieService.trouverCategorieParId(1);	
		System.out.println(cate);
		System.out.println("=========> affichage d'une categorie reussie");	
		
		System.out.println("affichage d'un client ");
		Client client = clientService.trouverClientParId(1)	;
		System.out.println(client);
		System.out.println("=========> affichage d'un client reussie");	
		
		System.out.println("affichage d'une commande ");
		Commande com = commService.trouverCommandeParId(1);
		System.out.println(com);
		System.out.println("=========> affichage d'une commande reussie");
		
		System.out.println("affichage d'une lignecommande ");
		LigneCommande lcom = ligneService.trouverLigneCommandeParId(1);
		System.out.println(lcom);
		System.out.println("=========> affichage d'une lignecommande reussie");
		
		System.out.println("affichage d'un panier ");
		Panier pan = panierService.trouverPanierParId(1);	
		System.out.println(pan);
		System.out.println("=========> affichage d'un panier reussie");
		
		System.out.println("affichage d'un produit ");
		Produit prod = prodService.trouverProduitParId(1);
		System.out.println(prod);
		System.out.println("=========> affichage d'un produit  reussie");
		
		System.out.println("affichage d'un role ");
		Role role = roleService.trouverRoleParId(1);
		System.out.println(role);
		System.out.println("=========> affichage d'un role reussie");
		
		System.out.println("affichage d'un utilisateur ");
		Utilisateur user =userService.trouverUtilisateurParId(1);	
		System.out.println(user);
		System.out.println("=========> affichage d'un utilisateur reussie");
		
		System.out.println("affichage d'une photo ");
		Photo photo =photoService.trouverPhotoParId(1);
		System.out.println(photo);
		System.out.println("=========> affichage d'une photo reussie");
		
		
		// TEST AFFICHAGE ALL
		
		System.out.println("affichage des categories ");
		List<Categorie> listecate = categorieService.afficherToutesLesCategories();
		System.out.println(listecate);
		System.out.println("=========> affichage des categories reussi");	
		
		System.out.println("affichage des clients ");
		List<Client> listecl = clientService.afficherTousLesClients();
		System.out.println(listecl);
		System.out.println("=========> affichagedes clients reussi");	
		
		System.out.println("affichage des commandes ");
		List<Commande> listecom = commService.afficherToutesLesCommandes();
		System.out.println(listecom);
		System.out.println("=========> affichage des commandes reussi");
		
		System.out.println("affichage des lignescommandes ");
		List<LigneCommande> listelcom = ligneService.afficherToutesLesLigneCommandes();
		System.out.println(listelcom);
		System.out.println("=========> affichage des lignescommandes reussi");
		
		System.out.println("affichage des panier ");
		List<Panier> listepan = panierService.afficherTousLesPaniers();
		System.out.println(listepan);
		System.out.println("=========> affichage des paniers reussi");
		
		System.out.println("affichage des produits ");
		List<Produit> listeprod = prodService.afficherTousLesProduits();
		System.out.println(listeprod);
		System.out.println("=========> affichage des produits reussi");
		
		System.out.println("affichage des roles ");
		List<Role> listerole = roleService.afficherTousLesRoles();
		System.out.println(listerole);
		System.out.println("=========> affichage des roles reussi");
		
		System.out.println("affichage des utilisateurs ");
		List<Utilisateur> listeuser = userService.afficherTousLesUtilisateurs();
		System.out.println(listeuser);
		System.out.println("=========> affichage des utilisateurs reussi");
		
		System.out.println("affichage des photoss ");
		List<Photo> listephoto = photoService.afficherToutesLesPhotos();
		System.out.println(listephoto);
		System.out.println("=========> affichage des photos reussi");
		
		System.out.println("=============================================================================================================");
		
		// TEST SUPPRESSION
		
		System.out.println("suppression d'une categorie ");
		categorieService.supprimerCategorie(1);	
		System.out.println("=========> suppression d'une categorie reussie");	
	
		System.out.println("suppression d'un client ");
		clientService.supprimerClient(1);	
		System.out.println("=========> suppression d'un client reussie");	
		
		System.out.println("suppression d'une commande ");
		commService.supprimerCommande(1);	
		System.out.println("=========> suppression d'une commande reussie");
		
		System.out.println("suppression d'une lignecommande ");
		ligneService.supprimerLigneCommande(1);	
		System.out.println("=========> suppression d'une lignecommande reussie");
		
		System.out.println("suppression d'un panier ");
		panierService.supprimerPanier(1);	
		System.out.println("=========> suppression d'un panier reussie");
		
		System.out.println("suppression d'un produit ");
		prodService.supprimerProduit(1);	
		System.out.println("=========> suppression d'un produit  reussie");
		
		System.out.println("suppression d'un role ");
		roleService.supprimerRole(1);	
		System.out.println("=========> suppression d'un role reussie");
		
		System.out.println("suppression d'un utilisateur ");
		userService.supprimerUtilisateur(1);	
		System.out.println("=========> suppression d'un utilisateur reussie");
		
		System.out.println("suppression d'une photo ");
		photoService.supprimerPhoto(1);	
		System.out.println("=========> suppression d'une photo reussie");
		
		
		
		
	}//end main

}//end class
