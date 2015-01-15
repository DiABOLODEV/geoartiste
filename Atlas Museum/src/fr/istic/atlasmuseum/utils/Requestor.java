package fr.istic.atlasmuseum.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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

}
