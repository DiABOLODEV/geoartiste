package fr.istic.atlasmuseum.robots;

import fr.istic.atlasmuseum.utils.Requestor;

public class RobotWikidata implements Robot{
	
	public RobotWikidata(){
		String url = "https://www.wikidata.org/w/api.php?action=opensearch&search=Leonardo&limit=100&format=xml";
		System.out.println(Requestor.get(url));
	}

}
