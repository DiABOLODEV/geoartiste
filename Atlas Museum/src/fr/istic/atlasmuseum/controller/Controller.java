package fr.istic.atlasmuseum.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.istic.atlasmuseum.bd.ConnexionBD;
import fr.istic.atlasmuseum.command.AnalyseWikiSkos;
import fr.istic.atlasmuseum.command.ChargerMinistere;
import fr.istic.atlasmuseum.command.ChargerSkos;
import fr.istic.atlasmuseum.command.ClearBD;
import fr.istic.atlasmuseum.command.RemplirBD;
import fr.istic.atlasmuseum.fichierxml.ListeOeuvre;
import fr.istic.atlasmuseum.fichierxml.Parseur;
import fr.istic.atlasmuseum.ihm.IHM;
import fr.istic.atlasmuseum.robots.RobotApi;
import fr.istic.atlasmuseum.robots.RobotGPS;
import fr.istic.atlasmuseum.robots.RobotWikipedia;
import fr.istic.atlasmuseum.skos.GlobalEntriesList;
import fr.istic.atlasmuseum.skos.Skos;
import fr.istic.atlasmuseum.skos.indexedEntry;
import fr.istic.atlasmuseum.utils.ModifierString;

public class Controller {

	private IHM ihm;
	private ArrayList<ListeOeuvre> listOeuvre;
	private GlobalEntriesList listEntries;
	private RobotApi api;

	public Controller(IHM ihm, RobotApi api){
		this.ihm = ihm;
		this.api = api;
		this.ihm.getXmlSkos().setClickedCmd(new ChargerSkos(this));
		this.ihm.getXmlMinistere().setClickedCmd(new ChargerMinistere(this));
		this.ihm.getCreerArtistes().setClickedCmd(new RemplirBD(this));
		this.ihm.getClearBD().setClickedCmd(new ClearBD(this));
		this.ihm.getAnalyseWikiSkos().setClickedCmd(new AnalyseWikiSkos(this));
	}

	public void chargerSkos(){
		Skos skos = new Skos("files/skos");
		this.listEntries = skos.getAllEntries();
	}

	public void chargerMinistere(){
		System.out.println("charger ministere");
		Parseur parseur = new Parseur("files/original");
		this.listOeuvre = parseur.getOeuvre();
		System.out.println(this.listOeuvre.size());
	}

	public void remplirBD(){
		
		for(int i=0; i<10;i++){//this.listOeuvre.size();i++){
			//Info artiste
			ModifierString modif = new ModifierString();
			ListeOeuvre oeuvre = modif.normaliseOeuvre(this.listOeuvre.get(i));
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
			int idArtiste = 0;
			String artistesExistants = this.api.selectArtisteByNomPrenom(nom, prenom);//modif.splitResultatApi(api.selectArtisteByNomPrenom(nom, prenom));
			System.out.println("artistes Existant : "+ artistesExistants);
			JSONArray array = new JSONArray(artistesExistants);
			System.out.println("taille tableau = "+array.length());
			for(int j=0;j<array.length();j++){
				System.out.println("OKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
				idArtiste = array.getJSONObject(j).getInt("id");
				System.out.println("idArtiste : "+idArtiste);
			}
			
			//Insertion artiste
			if (idArtiste==0){
				idArtiste = Integer.parseInt(modif.splitResultatApi(this.api.createArtiste("geoartiste5",nom, prenom)));
				System.out.println(idArtiste);
			}
			//calcul coordonnees
			RobotGPS gps = new RobotGPS();
			String[] coordonnees = gps.analyseResultats(listOeuvre.get(i));
			//Vérification si l'adresse existe déjà
			int idAdresse = 0;
			String adressesExistantes = modif.splitResultatApi(this.api.isExistAdresse(pays, region, departement, commune, etablissement));
			System.out.println("adresses existantes :"+adressesExistantes);
			if (!adressesExistantes.equals("false")){
				JSONObject obj = new JSONObject(adressesExistantes);
				idAdresse = obj.getInt("id");
			}
			System.out.println("idadresse ="+idAdresse);
			//Insertion adresse
			if(idAdresse==0){
				if (coordonnees[0]!=null && coordonnees[1]!=null && coordonnees[2]!=null){
					idAdresse = Integer.parseInt(modif.splitResultatApi(this.api.createAdresse("geoartiste5",pays, region, departement, commune, etablissement, Float.parseFloat(coordonnees[0]), Float.parseFloat(coordonnees[1]), Float.parseFloat(coordonnees[2]))));
					System.out.println("latitude = "+Float.parseFloat(coordonnees[0])+" _ longitude = "+Float.parseFloat(coordonnees[1])+" _ rayon = "+Float.parseFloat(coordonnees[2]));
					//statement = connexion.getConnexion().prepareStatement("INSERT INTO adresse (pays, region, departement, commune, etablissement, latitude, longitude,rayon) VALUES ('"+pays+"','"+region+"','"+departement+"','"+commune+"','"+etablissement+"',"+Float.parseFloat(coordonnees[0])+","+Float.parseFloat(coordonnees[1])+","+Float.parseFloat(coordonnees[2])+")",Statement.RETURN_GENERATED_KEYS);
				}else{
					System.out.println("pays="+pays+" region="+region+" departement="+departement+" commune="+commune+" etablissement="+etablissement);
					idAdresse = Integer.parseInt(modif.splitResultatApi(this.api.createAdresse("geoartiste5",pays, region, departement, commune, etablissement, 0, 0, 0)));
					//statement = connexion.getConnexion().prepareStatement("INSERT INTO adresse (pays, region, departement, commune, etablissement) VALUES ('"+pays+"','"+region+"','"+departement+"','"+commune+"','"+etablissement+"')",Statement.RETURN_GENERATED_KEYS);
				}
			}
			//Vérification existence de l'oeuvre
			
			//Insertion oeuvre
			System.out.println(idArtiste+" _ "+idAdresse);
			int idOeuvre = Integer.parseInt(modif.splitResultatApi(this.api.createOeuvre("geoartiste5", idArtiste, idAdresse, titre, description, periode, "")));
		}
	}

	public void clearBD(){
		this.api.deleteAll("geoartiste5");
	}

	public void analyseWikiSkos(){
		int idArtiste = 0;
		int idMotCle = 0;
		for(int i=0;i<10;i++){//this.listOeuvre.size();i++){
			RobotWikipedia robot = new RobotWikipedia();
			HashMap<String, Integer> descriptionArtiste = robot.analyseResultats(this.listOeuvre.get(i));
			String artistesExistants = this.api.selectArtisteByNomPrenom(this.listOeuvre.get(i).getNom_de_l_artiste(), this.listOeuvre.get(i).getPrenom_de_l_artiste());
			JSONArray array = new JSONArray(artistesExistants);
			System.out.println("taille tableau = "+array.length());
			for(int j=0;j<array.length();j++){
				System.out.println("OKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
				idArtiste = array.getJSONObject(j).getInt("id");
				System.out.println("idArtiste : "+idArtiste);
			}
			boolean isExistMotCle;
			ModifierString modif = new ModifierString();
			List<indexedEntry> listE = modif.normaliseMotCle(this.listEntries.getGlobalist());
			for (int j=0;j<listE.size();j++){
				String[] prefLabels = listE.get(j).getPreflabelWords();
				for(int k=0;k<prefLabels.length;k++){
					if(descriptionArtiste.containsKey(prefLabels[k])){
						String motCleExistants = this.api.selectMot_clesByArtisteId(idArtiste);
						JSONArray array2 = new JSONArray(motCleExistants);
						System.out.println("taille tableau = "+array2.length());
						int l=0;
						isExistMotCle=false;
						while(l<array2.length() && isExistMotCle==false){
							System.out.println(array2.getJSONObject(l).getString("uri")+" == "+listE.get(j).getURI());
							if(array2.getJSONObject(l).getString("uri").equals(listE.get(j).getURI())){
								System.out.println("BOUCLE WHILE !!");
								isExistMotCle=true;
							}
							l++;
						}
						if(!isExistMotCle){
							System.out.println("IS EXIST MOT CLE");
							api.createMot_Cle("geoartiste5", idArtiste, listE.get(j).getURI(), "source");
						}
					}
				}
			}
		}
	}
}
