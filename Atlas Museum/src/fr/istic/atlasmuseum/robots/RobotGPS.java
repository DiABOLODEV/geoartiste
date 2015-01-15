package fr.istic.atlasmuseum.robots;

import java.util.HashMap;

import fr.istic.atlasmuseum.utils.ParserGPS;
import fr.istic.atlasmuseum.utils.Requestor;

public class RobotGPS implements Robot{

	private static final String url = "http://maps.googleapis.com/maps/api/geocode/xml";
	public RobotGPS() {
		HashMap<String,String> result = ParserGPS.analyseAnswer(Requestor.get(url+"?address=Rennes&sensor=false"));
		System.out.println(
				"Adresse: "+result.get("adresse")+
				"\nLatitude : "+result.get("latitude")+
				"\nlongitude : "+result.get("longitude"));
	}
}
