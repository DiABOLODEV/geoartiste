package fr.istic.atlasmuseum.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Cette classe permet de faire des requêtes HTTP pour accéder aux différentes API 
 */
public class Requestor {
	
	/**
	 * Requête une url avec la méthode GET
	 * 
	 * @param path url avec les paramètres en GET à requêter
	 * @return la réponse en String
	 */
	public static String get(String path){
		URL url;
		String r = "";
		try {
			url = new URL(path);
			URLConnection conn = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				r += line;
			} 
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return r;

	}
	
	/**
	 * Génére une requête GET à partir d'un URL et de paramètres
	 * 
	 * @param url l'url de base de la requête GET
	 * @param params les paramètres de la requête GET sous la forme d'une association clée-valeur
	 * @return la requête prête à être éxécutée
	 */
	public static String generatGetRequest(String url, HashMap<String, String> params){
		String retour = url;
		Set<String> cles = params.keySet();
		Iterator<String> it = cles.iterator();
		boolean first = true;
		while (it.hasNext()){
			retour += (first) ? "?" : "&";
			first = false;
			String cle = (String) it.next();
			retour += cle+"="+params.get(cle);
		}
		return retour;
	}

}
