package fr.istic.atlasmuseum.controller;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fr.istic.atlasmuseum.api.IHM;
import fr.istic.atlasmuseum.bd.ConnexionBD;
import fr.istic.atlasmuseum.command.ChargerMinistere;
import fr.istic.atlasmuseum.command.ChargerSkos;
import fr.istic.atlasmuseum.command.ClearBD;
import fr.istic.atlasmuseum.command.RemplirBD;
import fr.istic.atlasmuseum.fichierxml.ListeOeuvre;
import fr.istic.atlasmuseum.fichierxml.Parseur;
import fr.istic.atlasmuseum.robots.RobotGPS;
import fr.istic.atlasmuseum.skos.Skos;
import fr.istic.atlasmuseum.utils.ModifierChampOeuvre;

public class Controller {

	private IHM ihm;
	private ArrayList<ListeOeuvre> listOeuvre;
	
	public Controller(IHM ihm){
		this.ihm = ihm;
		this.ihm.getXmlSkos().setClickedCmd(new ChargerSkos(this));
		this.ihm.getXmlMinistere().setClickedCmd(new ChargerMinistere(this));
		this.ihm.getCreerArtistes().setClickedCmd(new RemplirBD(this));
		this.ihm.getClearBD().setClickedCmd(new ClearBD(this));
	}
	
	public void chargerSkos(){
		new Skos("files/skos");
	}
	
	public void chargerMinistere(){
		System.out.println("charger ministere");
		Parseur parseur = new Parseur("files/original");
		this.listOeuvre = parseur.getOeuvre();
		System.out.println(this.listOeuvre.size());
	}
	
	public void remplirBD(){
		ConnexionBD connexion = new ConnexionBD();
		Statement st;
		PreparedStatement statement;
		ResultSet generateKey;
		ResultSet rs;
		try {
			for(int i=0; i<this.listOeuvre.size();i++){
				//Info artiste
				ModifierChampOeuvre modif = new ModifierChampOeuvre(this.listOeuvre.get(i));
				ListeOeuvre oeuvre = modif.ajoutSimpleCote();
				String nom = oeuvre.getNom_de_l_artiste();
				String prenom = oeuvre.getPrenom_de_l_artiste();
				//Info oeuvre
				String titre = oeuvre.getTitre_de_l_oeuvre();
				String description = oeuvre.getDescriptif_de_l_oeuvre();
				String periode = oeuvre.getPeriode();
				//Info adresse
				String pays = oeuvre.getPays();
				String region = oeuvre.getRegion();
				String departement = oeuvre.getDepartement();
				String commune = oeuvre.getCommune();
				String etablissement = oeuvre.getNom_de_l_etablissement();
				
				//Vérification existence de l'artiste
				st = connexion.getConnexion().createStatement();
				rs = st.executeQuery("SELECT DISTINCT id, count(*) as nbLignes FROM artiste WHERE nom='"+nom+"' and prenom='"+prenom+"'");
				rs.next();
				int nbLignes = rs.getInt("nbLignes");
				int idArtiste;
				//Insertion artiste
				if (nbLignes==0){
					statement = connexion.getConnexion().prepareStatement("INSERT INTO artiste (nom, prenom) VALUES ('"+nom+"','"+prenom+"')",Statement.RETURN_GENERATED_KEYS);
					statement.executeUpdate();
					generateKey = statement.getGeneratedKeys();
					generateKey.next();
					idArtiste = generateKey.getInt(1);
				}
				else{
					idArtiste = rs.getInt("id");
					rs.close();
				}
				//calcul coordonnees
				RobotGPS gps = new RobotGPS();
				String[] coordonnees = gps.analyseResultats(oeuvre);
				//Insertion adresse
				if (coordonnees[0]!=null && coordonnees[1]!=null){
					System.out.println("latitude = "+Float.parseFloat(coordonnees[0])+" _ longitude = "+Float.parseFloat(coordonnees[1]));
					statement = connexion.getConnexion().prepareStatement("INSERT INTO adresse (pays, region, departement, commune, etablissement, latitude, longitude) VALUES ('"+pays+"','"+region+"','"+departement+"','"+commune+"','"+etablissement+"',"+Float.parseFloat(coordonnees[0])+","+Float.parseFloat(coordonnees[1])+")",Statement.RETURN_GENERATED_KEYS);
				}else{
					statement = connexion.getConnexion().prepareStatement("INSERT INTO adresse (pays, region, departement, commune, etablissement) VALUES ('"+pays+"','"+region+"','"+departement+"','"+commune+"','"+etablissement+"')",Statement.RETURN_GENERATED_KEYS);
				}
				statement.executeUpdate();
				generateKey = statement.getGeneratedKeys();
				generateKey.next();
				int idAdresse = generateKey.getInt(1);
				
				//Insertion oeuvre
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				statement = connexion.getConnexion().prepareStatement("INSERT INTO oeuvre (id_artiste, id_adresse, titre, description, periode, date_maj) VALUES ('"+idArtiste+"','"+idAdresse+"','"+titre+"','"+description+"','"+periode+"','"+dateFormat.format(date)+"')",Statement.RETURN_GENERATED_KEYS);
				statement.executeUpdate();
		
				//int resultat = statement.executeUpdate("INSERT INTO artiste (nom, prenom) VALUES ('+nom+','+prenom+')");
				//statement.executeUpdate("DELETE FROM artiste");
			}
			connexion.getConnexion().close();
			/*Statement statement = connexion.getConnexion().createStatement();
			ResultSet resultat = statement.executeQuery( "SELECT * FROM adresse;" );
		    while ( resultat.next() ) {

		        System.out.println(resultat.getString("commune"));
		    }*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void clearBD(){
		ConnexionBD connexion = new ConnexionBD();
		try {
			Statement statement = connexion.getConnexion().createStatement();
			statement.executeUpdate("DELETE FROM oeuvre");
			statement.executeUpdate("DELETE FROM mot_cle");
			statement.executeUpdate("DELETE FROM artiste");
			statement.executeUpdate("DELETE FROM adresse");
			connexion.getConnexion().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
