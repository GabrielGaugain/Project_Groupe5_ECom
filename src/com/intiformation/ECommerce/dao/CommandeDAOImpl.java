package com.intiformation.ECommerce.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.ECommerce.modele.Commande;

public class CommandeDAOImpl implements ICommandeDAO {

	@Override
	public boolean add(Commande pCommande) {
		PreparedStatement ps = null;
		
		try {
			
			String Req ="INSERT INTO commandes (date_commande,id_client) VALUES (?,?)";
			
			ps = ICategorieDAO.connection.prepareStatement(Req);
			
			ps.setDate(1, (Date) pCommande.getDateCommande());
			ps.setLong(2, pCommande.getIdClient());
			
			
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
	public boolean update(Commande pCommande) {
		PreparedStatement ps = null;

		try {
			
			String Req ="UPDATE categories SET date_commande=?,id_client=? WHERE id_commande=?";
			
			ps = ICategorieDAO.connection.prepareStatement(Req);
			
			ps.setDate(1, (Date) pCommande.getDateCommande());
			ps.setLong(2, pCommande.getIdClient());
			ps.setLong(3, pCommande.getIdCommande());

			
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
	public boolean delete(long pIdCommande) {
		PreparedStatement ps = null;

		try {
			
			String Req ="DELETE FROM categories WHERE id_commande=?";
			
			ps = ICategorieDAO.connection.prepareStatement(Req);
			
			ps.setLong(1, pIdCommande);
			
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
	public List<Commande> getAll() {
		ResultSet rs = null;
		Commande commande = null;
		List<Commande> listeCommandes = new ArrayList<>();

		try {

			rs = ICategorieDAO.connection.prepareStatement("SELECT * FROM commandes").executeQuery();
			
			while (rs.next()) {

				// ctor :Commande(long idCommande, Date dateCommande, long idClient)
				commande = new Commande(rs.getLong(1), rs.getDate(2), rs.getLong(3));

				listeCommandes.add(commande);

			} // end while

			return listeCommandes;

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
	}//end getAll

	@Override
	public Commande getById(long pIdCommande) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Commande commande = null;

		try {
		
		String Req ="SELECT * FROM commandes WHERE id_commande=?";	
		ps = ICategorieDAO.connection.prepareStatement(Req);
		ps.setLong(1, pIdCommande);
		rs = ps.executeQuery();

		rs.next();
		// ctor :Commande(long idCommande, Date dateCommande, long idClient)
		commande = new Commande(rs.getLong(1), rs.getDate(2), rs.getLong(3));
		return commande;

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

}// end class
