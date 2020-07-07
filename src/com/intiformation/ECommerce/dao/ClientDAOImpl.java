package com.intiformation.ECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.ECommerce.modele.Client;

public class ClientDAOImpl implements IClientDAO {

	@Override
	public boolean add(Client t) {

		PreparedStatement ps = null;

		try {

			// 1. definition du contenu de la requete SQL avec ? pour prepared statement
			String requeteAjout = "INSERT INTO clients (nom_client, adresse_client, email_client, telephone_client) "
					+ "VALUES (?, ?, ?, ?)";

			// 2. creation preparedStatement
			ps = this.connection.prepareStatement(requeteAjout);

			//ps.setInt(1, t.getIdClient());
			ps.setString(1, t.getNomClient());
			ps.setString(2, t.getAdresseClient());
			ps.setString(3, t.getEmailClient());
			ps.setString(4, t.getTelephoneClient());
			
			ps.setLong(5, t.getIdClient());

			// 3. exe de la requete et recup resultat (update car modif)
			int verifAjout = ps.executeUpdate();

			return verifAjout == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de l'ajout d'un objet Client de la bdd ...");
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

	/**
	 * -----------------------------------------------------------------------------------------------
	 */
	@Override
	public boolean update(Client t) {
		PreparedStatement ps = null;

		try {

			// 1. definition du contenu de la requete SQL avec ? pour prepared statement
			String requeteUp = "UPDATE clients SET nom_client=?, adresse_client=?, email_client=?, codePostal=?, telephone_client=?"
					+ " WHERE id_client=? ";

			// 2. creation preparedStatement
			ps = this.connection.prepareStatement(requeteUp);

			ps.setString(1, t.getNomClient());
			ps.setString(2, t.getAdresseClient());
			ps.setString(3, t.getEmailClient());
			ps.setString(4, t.getTelephoneClient());

			ps.setLong(5, t.getIdClient());

			// 3. exe de la requete et recup resultat (update car modif)
			int verifUp = ps.executeUpdate();

			return verifUp == 1;

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la modif d'un objet Client de la bdd ...");
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

	/**
	 * -----------------------------------------------------------------------------------------------
	 */
	@Override
	public boolean delete(long id) {
		PreparedStatement ps = null;

		try {

			// 1. definition du contenu de la requete SQL avec ? pour prepared statement
			String requeteDel  = "DELETE FROM clients WHERE id_client=?" ;

			// 2. creation preparedStatement
			ps = this.connection.prepareStatement(requeteDel);

			ps.setLong(1, id);


			// 3. exe de la requete et recup resultat (update car modif)
			int verifDel = ps.executeUpdate();

			return (verifDel == 1);

		} catch (SQLException e) {
			System.out.println("...Erreur lors de la suppression d'un objet Client de la bdd ...");
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

	/**
	 * -----------------------------------------------------------------------------------------------
	 */
	@Override
	public List<Client> getAll() {
		PreparedStatement ps = null;
		ResultSet resultatRequete = null;

		try {
			// 1. définition du contenu de la requete SQL
			String requetGetAll = "SELECT * FROM clients";

			// 2. def de l'objet "Statement" qui permet d'envoyer les requetes vers la bdd
			ps = this.connection.prepareStatement(requetGetAll);

			// 3. Envoi et exe de la requette
			resultatRequete = ps.executeQuery();

			// 4. extraction des données du ResultSet

			Client Client = null;
			List<Client> listeClients = new ArrayList<>();

			while (resultatRequete.next()) {
				// 4.1 recup de l'id du type de chambre
				long ClientId = resultatRequete.getLong(1);

				// 4.2 recup du nombre de lit
				String nom = resultatRequete.getString(2);

				// 4.3 recup du type de lit
				String adresse = resultatRequete.getString(3);

				// 4.5 recup de la description de la chambre
				String mail = resultatRequete.getString(4);
				String tel = resultatRequete.getString(5);


				// 4.5 ajout des données à une instance TypeChambre
				Client = new Client(ClientId, nom, adresse, mail, tel);

				// 4.6 ajout du type de chambre à la liste
				listeClients.add(Client);

			} // end while

			// 5. renvoie de la liste
			return listeClients;

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
	}// end getAll()

	/**
	 * -----------------------------------------------------------------------------------------------
	 */
	@Override
	public Client getById(long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 1. definition du contenue de la requette
			String requeteById = " SELECT * FROM Clients WHERE id_client =?";

			// 2. statement
			ps = this.connection.prepareStatement(requeteById);
			ps.setLong(1, id);
			// 3. result set -> envoie requete
			rs = ps.executeQuery();

			// 4. extraction des données

			// 4.1. init tete de lecture avec next()
			rs.next();

			// 4.2. recup des données
			Client Client = new Client(rs.getLong(1), rs.getString(2),
							  		   rs.getString(3), rs.getString(4),
							  		   rs.getString(5)
							  		   );

			return Client;

		} catch (SQLException e) {
			System.out.println("... Erreur lors de la recup du Client via son id dans ClientDAOImpl...");
			e.printStackTrace();

		} finally {
			// 6. fermeture des ressources (connexion, ResultSet, Statement)
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} // end catch

		} // end finally

		return null;
	}//end getById

	
	
}//end classe
