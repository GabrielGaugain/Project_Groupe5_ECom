package com.intiformation.ECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.ECommerce.modele.Panier;

public class PanierDAOImpl implements IPanierDAO {

	@Override
	public boolean add(Panier pPanier) {
   Statement st = null;
		
		try {
			
			String Req ="INSERT INTO paniers () values ()";
			
			st = ICategorieDAO.connection.prepareStatement(Req);
			
			
			int verif = st.executeUpdate(Req);

			return verif == 0 ? false : true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) st.close();
				
			} catch (Exception e) {
				System.out.println("erreur -dao");
				e.printStackTrace();
			}
		}
		return false;
	}//end add

	@Override
	public boolean update(Panier t) {
		//vide en fait !
		return false;
	}//end update 

	@Override
	public boolean delete(long pIdPanier) {
		
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;

		try {
			
			String Req ="DELETE FROM paniers WHERE id_panier=?";	
			ps = IPanierDAO.connection.prepareStatement(Req);	
			ps.setLong(1, pIdPanier);
			
			ps1 = IPanierDAO.connection.prepareStatement("DELETE FROM lignescommandes WHERE id_panier=? ; ");
			ps1.setLong(1, pIdPanier);
			
			int verif1 = ps1.executeUpdate();
			int verif = ps.executeUpdate();
			System.out.println("requete supp lignes de commande : " +verif1 +"\n\t requete supp panier : "+verif);
			return (verif == 1);

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
	public List<Panier> getAll() {
		ResultSet rs = null;
		Panier panier = null;
		List<Panier> listePaniers = new ArrayList<>();

		try {

			rs = ICategorieDAO.connection.prepareStatement("SELECT * FROM paniers").executeQuery();
			
			while (rs.next()) {

				// ctor : Panier (long idPanier)
				panier = new Panier(rs.getLong(1));

				listePaniers.add(panier);

			} // end while

			return listePaniers;

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
	public Panier getById(long pIdPanier) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Panier panier = null;
		

		try {
		
		String Req ="SELECT * FROM paniers WHERE id_panier=?";	
		ps = ICategorieDAO.connection.prepareStatement(Req);
		ps.setLong(1, pIdPanier);
		rs = ps.executeQuery();

		rs.next();
		// ctor : Panier (long idPanier)
		panier = new Panier(rs.getLong(1));

		return panier;

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
	public Panier getLastBasket() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Panier panier = null;
		

		try {
		
		String Req ="SELECT * FROM paniers ORDER BY id_panier DESC LIMIT 1";	
		ps = ICategorieDAO.connection.prepareStatement(Req);
		
		rs = ps.executeQuery();

		rs.next();
		// ctor : Panier (long idPanier)
		panier = new Panier(rs.getLong(1));

		return panier;

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
	}//end getLastBasket

}// end class
