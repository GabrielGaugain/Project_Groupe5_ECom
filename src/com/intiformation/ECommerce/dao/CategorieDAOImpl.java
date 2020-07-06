package com.intiformation.ECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.ECommerce.modele.Categorie;

public class CategorieDAOImpl implements ICategorieDAO {

	@Override
	public boolean add(Categorie pCategorie) {
		
		PreparedStatement ps = null;
		
		try {
			
			String Req ="INSERT INTO categories (nom_categorie,description_categorie,id_photo) VALUES (?,?,?)";
			
			ps = ICategorieDAO.connexion_db_gestionECommerce.prepareStatement(Req);
			
			ps.setString(1, pCategorie.getNomCategorie());
			ps.setString(2,pCategorie.getDescriptionCategorie());
			ps.setString(3, pCategorie.getUrlImageCategorie());
			
			
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
	public boolean update(Categorie pCategorie) {
		
		PreparedStatement ps = null;

	try {
		
		String Req ="UPDATE categories SET nom_categorie=?,description_categorie=?,id_photo=? WHERE id_categorie=?";
		
		ps = ICategorieDAO.connexion_db_gestionECommerce.prepareStatement(Req);
		
		ps.setString(1, pCategorie.getNomCategorie());
		ps.setString(2,pCategorie.getDescriptionCategorie());
		ps.setString(3, pCategorie.getUrlImageCategorie());
		ps.setLong(4, pCategorie.getIdCategorie());

		
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
	public boolean delete(long pIdCategorie) {
		
		PreparedStatement ps = null;

		try {
			
			String Req ="DELETE FROM categories WHERE id_categorie=?";
			
			ps = ICategorieDAO.connexion_db_gestionECommerce.prepareStatement(Req);
			
			ps.setLong(1, pIdCategorie);
			
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
	public List<Categorie> getAll() {
		ResultSet rs = null;
		Categorie categorie = null;
		List<Categorie> listeCategories = new ArrayList<>();

		try {

			rs = ICategorieDAO.connexion_db_gestionECommerce.prepareStatement("SELECT * FROM categories").executeQuery();
			
			while (rs.next()) {

				// ctor :Categorie(long idCategorie, String nomCategorie, String descriptionCategorie, String urlImageCategorie)
				categorie = new Categorie(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));

				listeCategories.add(categorie);

			} // end while

			return listeCategories;

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
	public Categorie getById(long pIdCategorie) {
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		Categorie categorie = null;

		try {
		
		String Req ="SELECT * FROM categories WHERE id_categorie=?";	
		ps = ICategorieDAO.connexion_db_gestionECommerce.prepareStatement(Req);
		ps.setLong(1, pIdCategorie);
		rs = ps.executeQuery();

		rs.next();
		// ctor : Categorie(long idCategorie, String nomCategorie, String descriptionCategorie, String urlImageCategorie)
		categorie = new Categorie(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));

		return categorie;

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

}//end getById