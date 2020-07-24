package com.intiformation.ECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.ECommerce.modele.Produit;

public class ProduitDAOImpl implements IProduitDAO {

	@Override
	public boolean add(Produit pProduit) {
		PreparedStatement ps = null;
		
		try {
			
			String Req ="INSERT INTO produits (nom_produit,description_produit,prix_produit,quantite,url_photo,id_categorie) VALUES (?,?,?,?,?,?)";
			
			ps = IProduitDAO.connection.prepareStatement(Req);
			
			ps.setString(1, pProduit.getNomProduit());
			ps.setString(2,pProduit.getDescriptionProduit());
			ps.setDouble(3, pProduit.getPrixProduit());
			ps.setInt(4, pProduit.getQuantite());
			ps.setString(5, pProduit.getUrlImageProduit());
			ps.setLong(6, pProduit.getIdCategorie());
			
			
			int verif = ps.executeUpdate();

			return verif == 0 ? false : true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
				
			} catch (Exception e) {
				System.out.println("erreur -dao");
				e.printStackTrace();
			}
		}
		return false;
	}//end add

	@Override
	public boolean update(Produit pProduit) {
		PreparedStatement ps = null;

	try {
		
		String Req ="UPDATE produits SET nom_produit=?,description_produit=?,prix_produit=?, selectionne=?, url_photo=?,id_categorie=?, quantite=? WHERE id_produit=?";
		
		ps = IProduitDAO.connection.prepareStatement(Req);
		
		ps.setString(1, pProduit.getNomProduit());
		ps.setString(2,pProduit.getDescriptionProduit());
		ps.setDouble(3, pProduit.getPrixProduit());
		ps.setBoolean(4, false);
		ps.setString(5, pProduit.getUrlImageProduit());
		ps.setLong(6, pProduit.getIdCategorie());
		ps.setInt(7, pProduit.getQuantite());
		ps.setLong(8, pProduit.getIdProduit());

		
		int verif = ps.executeUpdate();
		System.out.println("dans update DAO , req : "+verif);
		
		return verif == 0 ? false : true;

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (ps != null) ps.close();
			
		} catch (Exception e) {
			System.out.println("erreur -dao");
			e.printStackTrace();
		}
	}
	return false;
}//end update 

	@Override
	public boolean delete(long pIdProduit) {
		
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;

		try {
			System.out.println("Dans DAO SUpp prod...");
			String ReqDel ="DELETE FROM produits WHERE id_produit=?";
			
			ps = IProduitDAO.connection.prepareStatement(ReqDel);
			ps.setLong(1, pIdProduit);
			
			ps1 = IProduitDAO.connection.prepareStatement("DELETE FROM lignescommandes WHERE id_produit=?");
			ps1.setLong(1, pIdProduit);
			
			int verif1 = ps1.executeUpdate();
			System.out.println("Supp ligne cmd du prod : "+verif1);
			
			
			int verif = ps.executeUpdate();
			System.out.println("Supp  du prod : "+verif);
			
			System.out.println("Dans DAO SUpp prod après req...");
			return (verif ==1)&&(verif1 == 1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
				if (ps1 != null) ps1.close();
				
			} catch (Exception e) {
				System.out.println("erreur -dao");
				e.printStackTrace();
			}
		}
		return false;
	}//end delete

	@Override
	public List<Produit> getAll() {
		ResultSet rs = null;
		Produit produit = null;
		List<Produit> listeProduits = new ArrayList<>();

		try {

			rs = IProduitDAO.connection.prepareStatement("SELECT * FROM produits").executeQuery();
			
			while (rs.next()) {

				// ctor :Produit(long idProduit, String nomProduit, String descriptionProduit, double prixProduit,boolean selectionne, String urlImageProduit, Long idCategorie)
				produit = new Produit(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4),rs.getInt(5),rs.getBoolean(6),rs.getString(7),rs.getLong(8));

				listeProduits.add(produit);

			} // end while

			return listeProduits;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println("erreur -dao");
				e.printStackTrace();
			}
		}

		return null;
	}// end getAll

	@Override
	public Produit getById(long pIdProduit) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Produit produit = null;

		try {
		
		String Req ="SELECT * FROM produits WHERE id_produit=?";	
		ps = IProduitDAO.connection.prepareStatement(Req);
		ps.setLong(1, pIdProduit);
		rs = ps.executeQuery();

		rs.next();
		// ctor :Produit(long idProduit, String nomProduit, String descriptionProduit, double prixProduit,boolean selectionne, String urlImageProduit, Long idCategorie)
		produit = new Produit(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4),rs.getInt(5),rs.getBoolean(6),rs.getString(7),rs.getLong(8));

		return produit;

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			System.out.println("erreur -dao");
			e.printStackTrace();
		}
	}
		return null;
	}//end getById

	@Override
	public List<Produit> getAllProduitByCategorie(long pIdCategorie) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Produit produit = null;
		List<Produit> listeProduits = new ArrayList<>();

		try {
		
		String Req ="SELECT * FROM produits WHERE id_categorie=?";	
		ps = IProduitDAO.connection.prepareStatement(Req);
		ps.setLong(1, pIdCategorie);
		rs = ps.executeQuery();
			
			while (rs.next()) {

				// ctor :Produit(long idProduit, String nomProduit, String descriptionProduit, double prixProduit,boolean selectionne, String urlImageProduit, Long idCategorie)
				produit = new Produit(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4),rs.getInt(5),rs.getBoolean(6),rs.getString(7),rs.getLong(8));

				listeProduits.add(produit);

			} // end while

			return listeProduits;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println("erreur -dao");
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public List<Produit> getProduitSelectionne() {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Produit produit = null;
		List<Produit> listeProduits = new ArrayList<>();

		try {
		
		String Req ="SELECT * FROM categories WHERE selectionne=?";	
		ps = IProduitDAO.connection.prepareStatement(Req);
		ps.setLong(1, 1);
		rs = ps.executeQuery();
			
			while (rs.next()) {

				// ctor :Produit(long idProduit, String nomProduit, String descriptionProduit, double prixProduit,boolean selectionne, String urlImageProduit, Long idCategorie)
				produit = new Produit(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4),rs.getInt(5),rs.getBoolean(6),rs.getString(7),rs.getLong(8));

				listeProduits.add(produit);

			} // end while

			return listeProduits;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println("erreur -dao");
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public List<Produit> getByMotCle(String pMotCle) {
		ResultSet rs = null;
		Produit produit = null;
		List<Produit> listeProduits = new ArrayList<>();

		try {
		
		rs = IProduitDAO.connection.prepareStatement("SELECT * FROM produits WHERE nom_produit LIKE '%"+pMotCle+"%' OR description_produit LIKE '%"+pMotCle+"%' ;").executeQuery();
			
			while (rs.next()) {

				// ctor :Produit(long idProduit, String nomProduit, String descriptionProduit, double prixProduit,boolean selectionne, String urlImageProduit, Long idCategorie)
				produit = new Produit(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4),rs.getInt(5),rs.getBoolean(6),rs.getString(7),rs.getLong(8));

				listeProduits.add(produit);

			} // end while

			return listeProduits;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println("erreur -dao");
				e.printStackTrace();
			}
		}

		return null;
	}


}//end interface
