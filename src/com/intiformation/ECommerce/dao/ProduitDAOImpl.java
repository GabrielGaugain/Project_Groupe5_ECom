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
			
			String Req ="INSERT INTO produits (nom_produit,description_produit,prix_produit,id_photo,id_categorie) VALUES (?,?,?,?,?)";
			
			ps = IProduitDAO.connexion_db_gestionECommerce.prepareStatement(Req);
			
			ps.setString(1, pProduit.getNomProduit());
			ps.setString(2,pProduit.getDescriptionProduit());
			ps.setDouble(3, pProduit.getPrixProduit());
			ps.setString(4, pProduit.getUrlImageProduit());
			ps.setLong(5, pProduit.getIdCategorie());
			
			
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
		
		String Req ="UPDATE produits SET nom_produit=?,description_produit=?,prix_produit=?, id_photo=?,id_categorie=? WHERE id_produit=?";
		
		ps = IProduitDAO.connexion_db_gestionECommerce.prepareStatement(Req);
		
		ps.setString(1, pProduit.getNomProduit());
		ps.setString(2,pProduit.getDescriptionProduit());
		ps.setDouble(3, pProduit.getPrixProduit());
		ps.setString(4, pProduit.getUrlImageProduit());
		ps.setLong(5, pProduit.getIdCategorie());
		ps.setLong(5, pProduit.getIdProduit());

		
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
}//end update 

	@Override
	public boolean delete(long pIdProduit) {
		PreparedStatement ps = null;

		try {
			
			String Req ="DELETE FROM produits WHERE id_produit=?";
			
			ps = IProduitDAO.connexion_db_gestionECommerce.prepareStatement(Req);
			
			ps.setLong(1, pIdProduit);
			
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
	}//end delete

	@Override
	public List<Produit> getAll() {
		ResultSet rs = null;
		Produit produit = null;
		List<Produit> listeProduits = new ArrayList<>();

		try {

			rs = IProduitDAO.connexion_db_gestionECommerce.prepareStatement("SELECT * FROM produits").executeQuery();
			
			while (rs.next()) {

				// ctor :Produit(long idProduit, String nomProduit, String descriptionProduit, double prixProduit,boolean selectionne, String urlImageProduit, Long idCategorie)
				produit = new Produit(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4),rs.getBoolean(5),rs.getString(6),rs.getLong(7));

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
		
		String Req ="SELECT * FROM categories WHERE id_produit=?";	
		ps = IProduitDAO.connexion_db_gestionECommerce.prepareStatement(Req);
		ps.setLong(1, pIdProduit);
		rs = ps.executeQuery();

		rs.next();
		// ctor :Produit(long idProduit, String nomProduit, String descriptionProduit, double prixProduit,boolean selectionne, String urlImageProduit, Long idCategorie)
		produit = new Produit(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4),rs.getBoolean(5),rs.getString(6),rs.getLong(7));

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
		
		String Req ="SELECT * FROM categories WHERE id_categorie=?";	
		ps = IProduitDAO.connexion_db_gestionECommerce.prepareStatement(Req);
		ps.setLong(1, pIdCategorie);
		rs = ps.executeQuery();
			
			while (rs.next()) {

				// ctor :Produit(long idProduit, String nomProduit, String descriptionProduit, double prixProduit,boolean selectionne, String urlImageProduit, Long idCategorie)
				produit = new Produit(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4),rs.getBoolean(5),rs.getString(6),rs.getLong(7));

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
	public List<Produit> getAllProduitSelectionne() {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Produit produit = null;
		List<Produit> listeProduits = new ArrayList<>();

		try {
		
		String Req ="SELECT * FROM categories WHERE selectionne=?";	
		ps = IProduitDAO.connexion_db_gestionECommerce.prepareStatement(Req);
		ps.setLong(1, 1);
		rs = ps.executeQuery();
			
			while (rs.next()) {

				// ctor :Produit(long idProduit, String nomProduit, String descriptionProduit, double prixProduit,boolean selectionne, String urlImageProduit, Long idCategorie)
				produit = new Produit(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4),rs.getBoolean(5),rs.getString(6),rs.getLong(7));

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
		PreparedStatement ps = null;
		Produit produit = null;
		List<Produit> listeProduits = new ArrayList<>();

		try {
		
		String Req ="SELECT * FROM categories WHERE nom_produit=?";	
		ps = IProduitDAO.connexion_db_gestionECommerce.prepareStatement(Req);
		ps.setLong(1, 1);
		rs = ps.executeQuery();
			
			while (rs.next()) {

				// ctor :Produit(long idProduit, String nomProduit, String descriptionProduit, double prixProduit,boolean selectionne, String urlImageProduit, Long idCategorie)
				produit = new Produit(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4),rs.getBoolean(5),rs.getString(6),rs.getLong(7));

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
