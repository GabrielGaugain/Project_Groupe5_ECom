package com.intiformation.ECommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class ConnectionDB {
	
	// ----------------------------------propriétés-------------------------------------------------------------------------

		private static final String URL_BDD = "jdbc:mysql://localhost:3306/db_gestionECommerce";
		private static final String USER_BDD = "root";
		private static final String PWD_BDD = "root";

		private static Connection connexion;

		// ----------------------------------ctors-------------------------------------------------------------------------
		private ConnectionDB() {};

		// -------------------------------------méthodes--------------------------------------------------------------------

		public static Connection getInstance() {

			if (connexion == null) {

				try {
					Driver piloteJDBC = new Driver();
					DriverManager.registerDriver(piloteJDBC);

					connexion = DriverManager.getConnection(URL_BDD, USER_BDD, PWD_BDD);

				} catch (SQLException e) {
					System.out.println("---Erreur lors de la création d'une connexion vers la BDD---");
					e.printStackTrace();
				} // end catch

			} // end if

			return connexion;

		}// end getInstance()

}//end class
