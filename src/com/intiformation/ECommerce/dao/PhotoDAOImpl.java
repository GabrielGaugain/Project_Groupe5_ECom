package com.intiformation.ECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			
			String Req ="UPDATE photos SET url_photo=?,nom_photo=? WHERE url_photo=?";
			
			ps = IPhotoDAO.connection.prepareStatement(Req);
			
			ps.setString(1, pPhoto.getUrlPhoto());
			ps.setString(2,pPhoto.getNomPhoto());
			ps.setString(3, pPhoto.getUrlPhoto());

			
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
	public boolean delete(String pUrlPhoto) {
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;

		try {
			
			
			ps = IPhotoDAO.connection.prepareStatement("DELETE FROM photos WHERE url_photo=? ; ");
			ps.setString(1, pUrlPhoto);
			
			ps1 = ICategorieDAO.connection.prepareStatement("UPDATE produits SET url_photo=null WHERE url_photo=? ;");
			ps1.setString(1, pUrlPhoto);
			
			ps2 = ICategorieDAO.connection.prepareStatement("UPDATE categories SET url_photo=null WHERE url_photo=? ;");
			ps2.setString(1, pUrlPhoto);
			
			
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

				// ctor :Photo( String urlPhoto, String nomPhoto)
				photo = new Photo(rs.getString(1), rs.getString(2));

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
		
		String Req ="SELECT * FROM photos WHERE url_photo=?";	
		ps = ICategorieDAO.connection.prepareStatement(Req);
		ps.setLong(1, pIdPhoto);
		rs = ps.executeQuery();

		rs.next();
		// ctor :Photo(String urlPhoto, String nomPhoto)
		photo = new Photo(rs.getString(1), rs.getString(2));


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

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

}//end class
