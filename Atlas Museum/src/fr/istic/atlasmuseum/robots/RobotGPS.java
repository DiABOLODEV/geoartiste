package fr.istic.atlasmuseum.robots;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import fr.istic.atlasmuseum.utils.EncoderAddress;
import fr.istic.atlasmuseum.utils.ParserGPS;
import fr.istic.atlasmuseum.utils.Requestor;

public class RobotGPS implements Robot{

	private static final String url = "http://maps.googleapis.com/maps/api/geocode/xml";
	HashMap<String,String> result;
	
	public RobotGPS() {
		String[]tabAdr= {"Groupe scoaire Giraud, Montigny-les-Metz, Moselle, Alsace",
		"Collège d'enseignement secondaire, Altkirch, Haut-Rhin, Alsace",
		"Groupe scolaire, Ammerschwirr, Haut-Rhin, Alsace",
		"Groupe scolaire rue Mulot, Epinay, Ain, Rhône-Alpes",
		"Lycée du Val de Saône, Trévoux,Ain, Rhône-AlpesAin",
		"Collège d'enseignement secondaire, St Anastaise, Besse-et-Saint-Anastaise, Puy-de-Dôme,Auvergne",
		"Ecole maternelle des géraniums, Cernay, Haut-Rhin, Alsace",
		"Lycée Davier, Joigny, Yonne, Bourgogne",
		"Cité scolaire mixte, Montceau-les-Mines, Saône-et-Loire, Bourgogne"};
        
		
		for (int i=0; i<tabAdr.length; i++){
			System.out.println("*********************ADRESSE n°"+(i+1)+"*****************************");
			System.out.println(tabAdr[i]);
			result = ParserGPS.analyseAnswer(Requestor.get(url+"?address=" +EncoderAddress.encodeStringForHTTPGET(tabAdr[i]) +"&sensor=false"));
			System.out.println(
					"Adresse: "+result.get("adresse")+
					"\nLatitude : "+result.get("latitude")+
					"\nlongitude : "+result.get("longitude"));
			
		}
		
	}
}
