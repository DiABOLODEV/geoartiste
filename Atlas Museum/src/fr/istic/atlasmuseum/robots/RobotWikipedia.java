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
			System.out.println("Artiste: "+artiste);
			
			parser.setXml(resultRequest);
			
			String description = parser.getDescription();
			
			if(description!= ""){
				System.out.println("Description de l'artiste: "+description);
				System.out.println("Mots clés correspondant à l'artiste: ");
				String[] motscles = parser.getDescriptionWords(description);
				for(int i = 0; i < motscles.length; i++){
					System.out.println("Mot "+i+": "+motscles[i]);
				}
			}
			else{
				System.out.println("Aucun informations n'est présente sur la base Wikipedia pour cet artiste");
			}
			
			
			
		}
		
		
	}
	
	

}
