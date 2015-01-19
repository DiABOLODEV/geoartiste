package fr.istic.atlasmuseum.robots;

import java.util.ArrayList;
import java.util.HashMap;

import fr.istic.atlasmuseum.parsers.ParserWikipedia;
import fr.istic.atlasmuseum.utils.Requestor;

public class RobotWikipedia implements Robot{
	private ParserWikipedia parser;
	
	private static final String BASE_URL = "http://fr.wikipedia.org/w/api.php";
	
	public RobotWikipedia(){
		this.parser = new ParserWikipedia();
		analyseResultats();
	}
	
	//Analyse les résultat sur les différents artistes
	public void analyseResultats(){
		
		ArrayList<String> artistes = new ArrayList<String>();
		artistes.add("Claude GOUTIN");
		artistes.add("André FORFERT");
		artistes.add("Pierre GESSIER");
		artistes.add("Léon RAFFIN");


		
		for (String artiste : artistes){
			//Utiliser le parser pour récupérer les noms et prénoms
			//Pour tout les artistes (nom, prénom)
			HashMap<String, String> params = new HashMap<String, String>();

			params.put("action", "opensearch");
			params.put("search", artiste);
			params.put("format", "xml");
			String request = Requestor.generatGetRequest(BASE_URL, params);
			String resultRequest = Requestor.get(request);
			
			System.out.println("------------------------------------------");
			System.out.println("requête:"+request);
			
			parser.setXml(resultRequest);
			System.out.println("résultat après parsing: "+parser.getDescription());
			
		}
		
		
	}
	
	

}
