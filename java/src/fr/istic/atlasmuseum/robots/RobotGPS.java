package fr.istic.atlasmuseum.robots;

import java.util.ArrayList;
import java.util.HashMap;

import fr.istic.atlasmuseum.fichierxml.ListeOeuvre;
import fr.istic.atlasmuseum.fichierxml.Parseur;
import fr.istic.atlasmuseum.parsers.ParserGPS;
import fr.istic.atlasmuseum.utils.Requestor;
import fr.istic.atlasmuseum.utils.Utils;

public class RobotGPS implements Robot{

	private static final String BASE_URL = "http://maps.googleapis.com/maps/api/geocode/xml";
	private ParserGPS parser;



	public RobotGPS() {

		this.parser = new ParserGPS();	

	}

	public String[] analyseResultats(ListeOeuvre oeuvre){

		String[] coordonnees = new String[3];


		String nomEtablissement = oeuvre.getNom_de_l_etablissement();
		String region = oeuvre.getRegion();
		String departement = oeuvre.getDepartement();
		String commune = oeuvre.getCommune();

		String valAddress= nomEtablissement+ ", "+commune+", "+departement+", "+region;

		HashMap<String,String> result = this.requestor(valAddress);

		//Zero resultat retourné
		if (result == null){

			String valNewAddress = commune+", "+departement;
			HashMap<String, String> newResult = this.requestor(valNewAddress);

			if (newResult == null){

				String valNewAddressDep = departement;
				HashMap<String, String> newResultDep = this.requestor(valNewAddressDep);

				if (newResultDep != null){
					//gecodage inversé pour récupérer la ville
					String latitude = newResultDep.get("latitude");
					String longitude = newResultDep.get("longitude");
					String valCordonnees = latitude+","+longitude;

					HashMap<String, String> res = this.reverseGeocode (valCordonnees); 
					String ville = res.get("ville");

					//calcul et stockage du rayon
					System.out.println("adresse "+newResultDep.get("adresse"));
					double lat1=  Utils.degToRad(Double.parseDouble(res.get("latitudeSud")));
					double lng1=  Utils.degToRad(Double.parseDouble(res.get("longitudeSud")));
					double lat2=  Utils.degToRad(Double.parseDouble(res.get("latitudeNord")));
					double lng2=  Utils.degToRad(Double.parseDouble(res.get("longitudeNord")));

					int rayon = 6371000;
					Float diametre = (float) (rayon * Math.acos(((Math.sin(lat1)*Math.sin(lat2)) + (Math.cos(lat1)*Math.cos(lat2)*Math.cos(lng1-lng2)))));
					System.out.println("rayon : "+(diametre/2));
					coordonnees[0] = latitude;
					coordonnees[1] = longitude;
					coordonnees[2] = String.valueOf(diametre/2);
				}
			}
			else{
				//gecodage inversé pour récupérer la ville
				String latitude = newResult.get("latitude");
				String longitude = newResult.get("longitude");
				String valCordonnees = latitude+","+longitude;

				HashMap<String, String> res = this.reverseGeocode (valCordonnees); 
				String ville = res.get("ville");

				//calcul et stockage du rayon
				System.out.println("adresse "+newResult.get("adresse"));
				double lat1=  Utils.degToRad(Double.parseDouble(res.get("latitudeSud")));
				double lng1=  Utils.degToRad(Double.parseDouble(res.get("longitudeSud")));
				double lat2=  Utils.degToRad(Double.parseDouble(res.get("latitudeNord")));
				double lng2=  Utils.degToRad(Double.parseDouble(res.get("longitudeNord")));

				int rayon = 6371000;
				Float diametre = (float) (rayon * Math.acos(((Math.sin(lat1)*Math.sin(lat2)) + (Math.cos(lat1)*Math.cos(lat2)*Math.cos(lng1-lng2)))));
				System.out.println("rayon : "+(diametre/2));
				coordonnees[0] = latitude;
				coordonnees[1] = longitude;
				coordonnees[2] = String.valueOf(diametre/2);
			}
		}

		// succès
		else{

			//Vérification fiabilité, gecodage inversé pour récupérer la ville
			String latitude = result.get("latitude");
			String longitude = result.get("longitude");
			String valCordonnees = latitude+","+longitude;

			HashMap<String, String> res1 = this.reverseGeocode (valCordonnees); 
			String ville = res1.get("ville");
			String dpt = res1.get("departement");

			if(commune.equalsIgnoreCase(ville) && departement.equalsIgnoreCase(dpt)) {

				System.out.println("adresse "+result.get("adresse"));	
				coordonnees[0] = latitude;
				coordonnees[1] = longitude;
				coordonnees[2] = "10";

			}


		}

		//timeout pour google api

		try {
			Thread.sleep(502);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("latitude = "+coordonnees[0]+" _ longitude = "+coordonnees[1]+" _ rayon = "+coordonnees[2]);
		return coordonnees;
	}

	public HashMap<String, String> requestor(String valAddress) {

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("address", valAddress);
		params.put("sensor", "false");
		String request = Requestor.generatGetRequest(BASE_URL, params);
		String resultRequest = Requestor.get(request);
		return parser.analyseAnswer(resultRequest);

	}

	public HashMap<String, String> reverseGeocode(String coordonnees) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("latlng", coordonnees);
		params.put("sensor", "false");
		String request = Requestor.generatGetRequest(BASE_URL, params);
		String resultRequest = Requestor.get(request);

		return parser.analyseAnswer(resultRequest);

	}

	public ParserGPS getParser() {
		return parser;
	}
}
