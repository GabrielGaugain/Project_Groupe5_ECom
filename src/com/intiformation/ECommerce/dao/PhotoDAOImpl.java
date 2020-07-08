package com.intiformation.ECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.ECommerce.modele.Categorie;
import com.intiformation.ECommerce.modele.Photo;

public class PhotoDAOImpl implements IPhotoDAO {

	@Override
	public boolean add(Photo pPhoto) {
		PreparedStatement ps = null;
		
		try {
			
			String Req ="INSERT INTO photos (url_photo, nom_photo) VALUES (?,?)";
			
			ps = IPhotoDAO.connection.prepareStatement(Req);
			
			ps.setString(1, pPhoto.getUrlPhoto());
			ps.setString(2,pPhoto.getNomPhoto());
			
			
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
	public boolean update(Photo pPhoto) {
		PreparedStatement ps = null;

		try {
			
			String Req ="UPDATE photos SET url_photo=?,nom_photo=? WHERE id_photo=?";
			
			ps = IPhotoDAO.connection.prepareStatement(Req);
			
			ps.setString(1, pPhoto.getUrlPhoto());
			ps.setString(2,pPhoto.getNomPhoto());
			ps.setLong(3, pPhoto.getIdPhoto());

			
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
	public boolean delete(long pIdPhoto) {
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;

		try {
			
			
			ps = IPhotoDAO.connection.prepareStatement("DELETE FROM photos WHERE id_photo=? ; ");
			ps.setLong(1, pIdPhoto);
			
			ps1 = ICategorieDAO.connection.prepareStatement("UPDATE produits SET id_photo=null WHERE id_photo=? ;");
			ps1.setLong(1, pIdPhoto);
			
			ps2 = ICategorieDAO.connection.prepareStatement("UPDATE categories SET id_photo=null WHERE id_photo=? ;");
			ps2.setLong(1, pIdPhoto);
			
			
			int verif2 = ps2.executeUpdate();
			int verif1 = ps1.executeUpdate();
			int verif = ps.executeUpdate();
			

			return verif1 == 0 ? false : true && verif2 == 0 ? false : true && verif == 0 ? false : true;
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
				if (ps1 != null) ps1.close();
				if (ps2 != null) ps2.close();
			} catch (Exception e) {
				System.out.println("erreur -dao");
				e.printStackTrace();
			}
		}
		return false;
	}//end delete

	@Override
	public List<Photo> getAll() {
		ResultSet rs = null;
		Photo photo = null;
		List<Photo> listePhotos = new ArrayList<>();

		try {

			rs = IPhotoDAO.connection.prepareStatement("SELECT * FROM photos").executeQuery();
			
			while (rs.next()) {

				// ctor :Photo(long idPhoto, String urlPhoto, String nomPhoto)
				photo = new Photo(rs.getLong(1), rs.getString(2), rs.getString(3));

				listePhotos.add(photo);

			} // end while

			return listePhotos;

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
	public Photo getById(long pIdPhoto) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Photo photo = null;

		try {
		
		String Req ="SELECT * FROM photos WHERE id_photo=?";	
		ps = ICategorieDAO.connection.prepareStatement(Req);
		ps.setLong(1, pIdPhoto);
		rs = ps.executeQuery();

		rs.next();
		// ctor :Photo(long idPhoto, String urlPhoto, String nomPhoto)
		photo = new Photo(rs.getLong(1), rs.getString(2), rs.getString(3));


		return photo;

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

}//end class
