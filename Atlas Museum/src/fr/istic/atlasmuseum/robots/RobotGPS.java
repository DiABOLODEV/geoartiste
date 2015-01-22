package fr.istic.atlasmuseum.robots;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import fr.istic.atlasmuseum.fichierxml.ListeOeuvre;
import fr.istic.atlasmuseum.fichierxml.Parseur;
import fr.istic.atlasmuseum.parsers.ParserGPS;
import fr.istic.atlasmuseum.utils.EncoderAddress;
import fr.istic.atlasmuseum.utils.Requestor;

public class RobotGPS implements Robot{

	private static final String BASE_URL = "http://maps.googleapis.com/maps/api/geocode/xml";
	private ParserGPS parser;
	
	
	
	public RobotGPS() {
		
		this.parser = new ParserGPS();
		analyseResultats();
//		String[]tabAdr= {"Groupe scoaire Giraud, Montigny-les-Metz, Moselle, Alsace",
//		"Coll�ge d'enseignement secondaire, Altkirch, Haut-Rhin, Alsace",
//		"Groupe scolaire, Ammerschwirr, Haut-Rhin, Alsace",
//		"Groupe scolaire rue Mulot, Epinay, Ain, Rh�ne-Alpes",
//		"Lyc�e du Val de Sa�ne, Tr�voux,Ain, Rh�ne-AlpesAin",
//		"Coll�ge d'enseignement secondaire, St Anastaise, Besse-et-Saint-Anastaise, Puy-de-D�me,Auvergne",
//		"Ecole maternelle des g�raniums, Cernay, Haut-Rhin, Alsace",
//		"Lyc�e Davier, Joigny, Yonne, Bourgogne",
//		"Cit� scolaire mixte, Montceau-les-Mines, Sa�ne-et-Loire, Bourgogne"};
//        
//		
//		for (int i=0; i<tabAdr.length; i++){
//			System.out.println("*********************ADRESSE n�"+(i+1)+"*****************************");
//			System.out.println(tabAdr[i]);
//			result = ParserGPS.analyseAnswer(Requestor.get(url+"?address=" +EncoderAddress.encodeStringForHTTPGET(tabAdr[i]) +"&sensor=false"));
//			System.out.println(
//					"Adresse: "+result.get("adresse")+
//					"\nLatitude : "+result.get("latitude")+
//					"\nlongitude : "+result.get("longitude"));
//			
//		}
//		
		
		
		
	}
	
	public void analyseResultats(){
		
		Parseur p = new Parseur("files/original");
		ArrayList<ListeOeuvre> oeuvres = p.getOeuvre();
		int coordonneesTrouvees = 0;
		
	
		for(int i=0; i< 1000 ;i++){
			String nomEtablissement = p.getOeuvre().get(i).getNom_de_l_etablissement();
			String commune = p.getOeuvre().get(i).getCommune();
			String departement =p.getOeuvre().get(i).getDepartement() ;
			String region = p.getOeuvre().get(i).getRegion();
			//Utiliser le parser pour récupérer les noms et prénoms
			//Pour tout les artistes (nom, prénom)
			HashMap<String, String> params = new HashMap<String, String>();
            
			String valAddress= nomEtablissement+ ", "+commune+", "+departement+", "+region;
			//url+"?address=" +EncoderAddress.encodeStringForHTTPGET(tabAdr[i]) +"&sensor=false
			params.put("address", valAddress);
			params.put("sensor", "false");
			String request = Requestor.generatGetRequest(BASE_URL, params);
			String resultRequest = Requestor.get(request);
			
			
			
			HashMap<String,String> result = parser.analyseAnswer(resultRequest); 
			
			
			if (result == null){
				System.out.println("<tr class=\"danger\"><td>"+valAddress+"</td><td></td><td></td><td></td></tr>");
			}
			else{
				
				System.out.println("<tr class=\"success\"><td>"+valAddress+"</td><td>"+result.get("adresse")+"</td><td>"+result.get("latitude")+"</td><td>"+result.get("longitude")+"</td></tr>");
				coordonneesTrouvees++;
			}
			
			//timeout pour google api
			
			try {
				Thread.sleep(502);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		System.out.println("Nombre de coordonnées trouvées : "+coordonneesTrouvees+"/"+oeuvres.size());
	}
}
