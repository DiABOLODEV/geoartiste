package fr.istic.atlasmuseum.robots;

import fr.istic.atlasmuseum.utils.ParserGPS;
import fr.istic.atlasmuseum.utils.Requestor;

public class RobotGPS implements Robot{

	private static final String url = "http://maps.googleapis.com/maps/api/geocode/xml";
	public RobotGPS() {
		String[] result = ParserGPS.analyseAnswer(Requestor.get(url+"?address=Rennes&sensor=false"));
		System.out.println("Latitude : "+result[0]+ "\nlongitude : "+result[1]);
	}
}
