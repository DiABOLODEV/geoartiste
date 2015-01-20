package fr.istic.atlasmuseum.robots;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import fr.istic.atlasmuseum.fichierxml.ListeOeuvre;
import fr.istic.atlasmuseum.fichierxml.Parseur;
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
		
		Parseur p = new Parseur("files/original");
		ArrayList<ListeOeuvre> oeuvres = p.getOeuvre();
		int descriptionTrouvee = 0;
		
		HashMap<String, Integer> nomenclature = new HashMap<String, Integer>();
		
		
		for(int i=0; i< oeuvres.size() ;i++){
			String nom = p.getOeuvre().get(i).getNom_de_l_artiste();
			String prenom = p.getOeuvre().get(i).getPrenom_de_l_artiste();
			String artiste = prenom+" "+nom;
			//Utiliser le parser pour récupérer les noms et prénoms
			//Pour tout les artistes (nom, prénom)
			HashMap<String, String> params = new HashMap<String, String>();

			params.put("action", "opensearch");
			params.put("search", artiste);
			params.put("format", "xml");
			String request = Requestor.generatGetRequest(BASE_URL, params);
			String resultRequest = Requestor.get(request);
			
			//System.out.println("------------------------------------------");
			//System.out.println("Artiste: "+artiste);
			
			parser.setXml(resultRequest);
			//System.out.println(i+"/"+oeuvres.size());
			String description = parser.getDescription();
			
			if(description!= ""){
				descriptionTrouvee ++;
				//System.out.println("Description de l'artiste: "+description);
				//System.out.println("Mots clés correspondant à l'artiste: ");
				String[] motscles = parser.getDescriptionWords(description);
				for(int j = 0; j < motscles.length; j++){
					//System.out.println("Mot "+j+": "+motscles[j]);
					if(nomenclature.containsKey(motscles[j])){
						nomenclature.put(motscles[j], nomenclature.get(motscles[j])+1 );
					}
					else{
						nomenclature.put(motscles[j], 1);
					}
				}
			}
			else{
				//System.out.println("Aucun informations n'est présente sur la base Wikipedia pour cet artiste");
			}
			
			
		}
		
		// Ajout des entrées de la map à une liste
		 List<Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>(nomenclature.entrySet());
		 
		  // Tri de la liste sur la valeur de l'entrée
		  Collections.sort(entries, new Comparator<Entry<String, Integer>>() {
		    public int compare(final Entry<String, Integer> e1, final Entry<String, Integer> e2) {
		      return e2.getValue().compareTo(e1.getValue());
		    }
		  });
		 
		  // Affichage du résultat
		  for (final Entry<String, Integer> entry : entries) {
		    System.out.println("<tr><td>"+entry.getKey() + "</td><td> " + entry.getValue()+"</td></tr>");
		  }
	
		System.out.println("Nombre de descriptions trouvées : "+descriptionTrouvee+"/"+oeuvres.size());

	}
	
	

}
