package com.intiformation.ECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.intiformation.ECommerce.modele.Role;

public class RoleDAOImpl implements IRoleDAO {

	@Override
	public boolean add(Role pRole) {
		PreparedStatement ps = null;
		
		try {
			
			String Req ="INSERT INTO roles (nom_role) VALUES (?)";
			
			ps = ICategorieDAO.connection.prepareStatement(Req);
			
			ps.setString(1, pRole.getNomRole());

			
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
	public boolean update(Role pRole) {
		PreparedStatement ps = null;

	try {
		
		String Req ="UPDATE roles SET nom_role=? WHERE id_role=?";
		
		ps = ICategorieDAO.connection.prepareStatement(Req);
		
		ps.setString(1, pRole.getNomRole());
		ps.setLong(2, pRole.getIdRole());

		
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
	public boolean delete(long pIdRole) {
		PreparedStatement ps = null;

		try {
			
			String Req ="DELETE FROM roles WHERE id_role=?";
			
			ps = ICategorieDAO.connection.prepareStatement(Req);
			
			ps.setLong(1, pIdRole);
			
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
	public List<Role> getAll() {
		ResultSet rs = null;
		Role role = null;
		List<Role> listeRoles = new ArrayList<>();

		try {

			rs = ICategorieDAO.connection.prepareStatement("SELECT * FROM roles").executeQuery();
			
			while (rs.next()) {

				// ctor : Role(long idCategorie, String nomCategorie)
				role = new Role(rs.getLong(1), rs.getString(2));

				listeRoles.add(role);

			} // end while

			return listeRoles;

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
	public Role getById(long pIdRole) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Role role = null;

		try {
		
		String Req ="SELECT * FROM roles WHERE id_role=?";	
		ps = ICategorieDAO.connection.prepareStatement(Req);
		ps.setLong(1, pIdRole);
		rs = ps.executeQuery();

		rs.next();
		// ctor : Role(long idCategorie, String nomCategorie)
		role = new Role(rs.getLong(1), rs.getString(2));

		return role;

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
