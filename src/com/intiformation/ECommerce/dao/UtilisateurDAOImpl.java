package com.intiformation.ECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.ECommerce.modele.Utilisateur;

public class UtilisateurDAOImpl implements IUtilisateurDAO {

	
	@Override
	public boolean add(Utilisateur t) {
		PreparedStatement ps = null;

		try {

			// 1. definition du contenu de la requete SQL avec ? pour prepared statement
			String requeteAjout = "INSERT INTO utilisateurs (nom_utilisateur, mdp_utilisateur,id_role, active ) "
					+ "VALUES (?, ?, ?, ?)";

			// 2. creation preparedStatement
			ps = this.connection.prepareStatement(requeteAjout);

			//ps.setInt(1, t.getIdClient());
			ps.setString(1, t.getNomUtilisateur());
			ps.setString(2, t.getMdpUtilisateur());
			ps.setLong(3, t.getIdRole());
			ps.setBoolean(4, t.isActive());



			// 3. exe de la requete et recup resultat (update car modif)
			int verifAjout = ps.executeUpdate();

			return verifAjout == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de l'ajout d'un objet utilisateur de la bdd ...");
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
	}

	@Override
	public boolean update(Utilisateur t) {
		PreparedStatement ps = null;

		try {

			// 1. definition du contenu de la requete SQL avec ? pour prepared statement
			String requeteUp = "UPDATE utilisateurs SET nom_utilisateur=?, mdp_utilisateur=?, id_role=?, active=? "
					+ " WHERE id_utilisateur=? ";

			// 2. creation preparedStatement
			ps = this.connection.prepareStatement(requeteUp);

			ps.setString(1, t.getNomUtilisateur());
			ps.setString(2, t.getMdpUtilisateur());
			ps.setLong(3, t.getIdRole());
			ps.setBoolean(4, t.isActive());
			
			ps.setLong(4, t.getIdUtilisateur());

			// 3. exe de la requete et recup resultat (update car modif)
			int verifUp = ps.executeUpdate();

			return verifUp == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la modif d'un objet utilisateur de la bdd ...");
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
	}

	@Override
	public boolean delete(long id) {
		PreparedStatement ps = null;

		try {

			
			//String requeteDropFk = "ALTER TABLE utilisateurs DROP CONSTRAINT fk_utilisateurs_roles ";
			//String requeteAddFk = "ALTER TABLE utilisateurs ADD constraint fk_utilisateurs_roles "+
			//				  "FOREIGN KEY (id_role) REFERENCES roles(id_role)";
		
			// Drop la foreign key pour pouvoir supprimer 
			//ps = this.connection.prepareStatement(requeteDropFk);
			//ps.executeUpdate();
			//ps.close();
			
			// 1. definition du contenu de la requete SQL avec ? pour prepared statement
			String requeteDel = "DELETE FROM utilisateurs WHERE id_utilisateur=? ";
			
			// 2. creation preparedStatement
			ps = this.connection.prepareStatement(requeteDel);

			ps.setLong(1, id);
			
			// 3. exe de la requete et recup resultat (update car modif)
			int verifDel = ps.executeUpdate();
			
			
			// Rajout de la foreign key pour après avoir supprimer 
			//ps = this.connection.prepareStatement(requeteAddFk);
			//ps.executeUpdate();
			//ps.close();
			
			
			return verifDel == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la suppression d'un objet utilisateur de la bdd ...");
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
	}

	@Override
	public Utilisateur getById(long id) {
		
		PreparedStatement ps =null;
		ResultSet rs =null;
		
		try {
			// 1. definition du contenue de la requette
			String requeteById = " SELECT * FROM utilisateurs WHERE id_utilisateur =?";
			
			// 2. statement		
			ps = this.connection.prepareStatement(requeteById);
			ps.setLong(1, id);
			// 3. result set -> envoie requete
			rs = ps.executeQuery();
			
			// 4. extraction des données
			
			// 4.1.  init tete de lecture avec next()
			rs.next();
			
			// 4.2. recup des données
			Utilisateur user = new Utilisateur(rs.getLong(1),
											   rs.getString(2),
											   rs.getString(3),
											   rs.getLong(4),
											   rs.getBoolean(5)
											   );
			
			return user;
			
		} catch(SQLException e ) {
			System.out.println("... Erreur lors de la recup de l'utilisateur via son id dans UtilisateurDAOImpl...");
			e.printStackTrace();
		
		}finally {
			// 6. fermeture des ressources (connexion, ResultSet, Statement)
			try {
				if(rs != null ) {rs.close();}
				if(ps != null ) {ps.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}// end catch
			
		}// end finally

		return null;
	}

	@Override
	public List<Utilisateur> getAll() {
		PreparedStatement ps = null;
		ResultSet resultatRequete = null;

		try {
			// 1. définition du contenu de la requete SQL
			String requetGetAll = "SELECT * FROM utilisateurs";

			// 2. def de l'objet "Statement" qui permet d'envoyer les requetes vers la bdd
			ps = this.connection.prepareStatement(requetGetAll);

			// 3. Envoi et exe de la requette
			resultatRequete = ps.executeQuery();

			// 4. extraction des données du ResultSet

			Utilisateur utilisateur = null;
			List<Utilisateur> listeUsers = new ArrayList<>();

			while (resultatRequete.next()) {
				// 4.1 recup de l'id 
				long user_id = resultatRequete.getLong(1);

				// 4.2 recup du nom
				String nom = resultatRequete.getString(2);

				// 4.3 recup du mdp
				String mdp = resultatRequete.getString(3);
				
				// 4.4 recup id_role
				long role = resultatRequete.getLong(4);
				
				boolean active = resultatRequete.getBoolean(5);

				// 4.5 ajout des données à une instance TypeChambre
				utilisateur = new Utilisateur(user_id, nom, mdp, role, active);

				// 4.6 ajout du type de chambre à la liste
				listeUsers.add(utilisateur);

			} // end while

			// 5. renvoie de la liste
			return listeUsers;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la lecture et de la collect de la table  ...");
			e.printStackTrace();

		} finally {

			// 6. fermeture des ressources (connexion, ResultSet, Statement)
			try {
				if (resultatRequete != null) {
					resultatRequete.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} // end finally

		return null;
	}

	@Override
	public boolean isUtilisateurExists(String pIdentifiant, String pMotDePasse) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			// 1. contenu requete
			String requeteExists = "SELECT COUNT(id_utilisateur) FROM utilisateurs "
								 + "WHERE nom_utilisateur = ? AND mdp_utilisateur = ?";
			
			//2. def de la requete JDBC
			ps = this.connection.prepareStatement(requeteExists);
			
			// 3. passage de param a la requete
			ps.setString(1, pIdentifiant);
			ps.setString(2, pMotDePasse);
			
			// 4. exe requete
			rs = ps.executeQuery();
			
			// 5. extraction des données du rs
	
			// 5.1. init de la tete de lectue
			rs.next();
			
			// 5.2. extraction
			int verif = rs.getInt(1);
			
			// 6 renvoi du resultat
			return (verif == 1);
			
			
		} catch (SQLException e ) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) { rs.close();}
				if(ps!=null) { ps.close();}
			}catch (SQLException e) {
				e.printStackTrace();
			}// end catch
			
		} //  end finally
		return false;	}	
	
	
}
