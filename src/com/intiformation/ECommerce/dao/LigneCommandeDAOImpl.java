package com.intiformation.ECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.intiformation.ECommerce.modele.LigneCommande;

public class LigneCommandeDAOImpl implements ILigneCommandeDAO{

	@Override
	public boolean add(LigneCommande pLigneCommande) {
		PreparedStatement ps = null;
		
		try {
			
			String Req ="INSERT INTO lignescommandes (quantite_commande, montant_commande, id_produit, id_commande, id_panier) VALUES (?,?,?,?,?)";
			
			ps = this.connection.prepareStatement(Req);
			
			ps.setInt(1, pLigneCommande.getQuantiteCommande());
			ps.setDouble(2, pLigneCommande.getMontantCommande());
			ps.setLong(3,pLigneCommande.getIdProduit());
			ps.setLong(4,pLigneCommande.getIdCommande());
			ps.setLong(5,pLigneCommande.getIdPanier());
			
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
	public boolean update(LigneCommande pLigneCommande) {
		PreparedStatement ps = null;

		try {
			
			String Req ="UPDATE lignescommandes SET quantite_commande=?,montant_commande=?,id_produit=?,id_commande=?, id_panier=? WHERE id_lignecommande=?";
			
			ps = this.connection.prepareStatement(Req);
			
			ps.setInt(1, pLigneCommande.getQuantiteCommande());
			ps.setDouble(2, pLigneCommande.getMontantCommande());
			ps.setLong(3,pLigneCommande.getIdProduit());
			ps.setLong(4,pLigneCommande.getIdCommande());
			ps.setLong(5,pLigneCommande.getIdPanier());
			ps.setLong(6, pLigneCommande.getIdLigneCommande());

			
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
	public boolean delete(long pIdLignecommande) {
		PreparedStatement ps = null;

		try {
			
			String Req ="DELETE FROM lignescommandes WHERE id_lignecommande=?";
			
			ps = this.connection.prepareStatement(Req);
			
			ps.setLong(1, pIdLignecommande);
			
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
	public List<LigneCommande> getAll() {
		ResultSet rs = null;
		LigneCommande ligneCommande = null;
		List<LigneCommande> listeLigneCommandes = new ArrayList<>();

		try {

			rs = this.connection.prepareStatement("SELECT * FROM lignescommandes").executeQuery();
			
			while (rs.next()) {

				// ctor : ligneCommande(long idLigneCommande, int quantiteCommande, double montantCommande, long idProduit, long idCommande, long idPanier)
				ligneCommande = new LigneCommande(rs.getLong(1), rs.getInt(2), rs.getDouble(3), rs.getLong(4),rs.getLong(5),rs.getLong(6));

				listeLigneCommandes.add(ligneCommande);

			} // end while

			return listeLigneCommandes;

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
	public LigneCommande getById(long pIdLigneCommande) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		LigneCommande ligneCommande = null;

		try {
		
		String Req ="SELECT * FROM lignescommandes WHERE id_lignecommande=?";	
		ps = this.connection.prepareStatement(Req);
		ps.setLong(1, pIdLigneCommande);
		rs = ps.executeQuery();

		rs.next();
		// ctor : ligneCommande(long idLigneCommande, int quantiteCommande, double montantCommande, long idProduit, long idCommande, long idPanier)
		ligneCommande = new LigneCommande(rs.getLong(1), rs.getInt(2), rs.getDouble(3), rs.getLong(4),rs.getLong(5),rs.getLong(6));
		
		
		return ligneCommande;

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
	public Collection<LigneCommande> getByPanierId(long pIdPanier) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		LigneCommande ligneCommande = null;
		List<LigneCommande> listeLigneCommandes = new ArrayList<>();

		try {
		
		String Req ="SELECT * FROM lignescommandes WHERE id_panier=?";	
		ps = this.connection.prepareStatement(Req);
		
		ps.setLong(1, pIdPanier);
		rs = ps.executeQuery();

		while (rs.next()) {

			// ctor : ligneCommande(long idLigneCommande, int quantiteCommande, double montantCommande, long idProduit, long idCommande, long idPanier)
			ligneCommande = new LigneCommande(rs.getLong(1), rs.getInt(2), rs.getDouble(3), rs.getLong(4),rs.getLong(5),rs.getLong(6));

			listeLigneCommandes.add(ligneCommande);

		} // end while		
		
		return listeLigneCommandes;

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

	}//end getByPanierId


}//end class
