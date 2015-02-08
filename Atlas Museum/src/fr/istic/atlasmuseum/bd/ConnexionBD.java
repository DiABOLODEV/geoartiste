package fr.istic.atlasmuseum.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionBD {

	private Connection connexion = null;
	
	public ConnexionBD(){
		String url = "jdbc:mysql://localhost:3306/geoartiste";

		String utilisateur = "root";

		String motDePasse = "";

		try {
		    this.connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		    /*Statement statement = connexion.createStatement();
		    ResultSet resultat = statement.executeQuery( "SELECT * FROM adresse;" );
		    while ( resultat.next() ) {

		        System.out.println(resultat.getString("commune"));
		    }*/

		    /* Ici, nous placerons nos requêtes vers la BDD */

		    /* ... */

		} catch ( SQLException e ) {
			System.out.println(e.getMessage());
		    /* Gérer les éventuelles erreurs ici */

		}
	}

	public Connection getConnexion() {
		return connexion;
	}
}
