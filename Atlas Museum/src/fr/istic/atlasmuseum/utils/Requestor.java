package fr.istic.atlasmuseum.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Cette classe permet de faire des requêtes HTTP pour accéder aux différentes API 
 */
public class Requestor {
	
	private static final String USER_AGENT = "Mozilla/5.0";
	
	/**
	 * Requête une url avec la méthode GET
	 * 
	 * @param path url avec les paramètres en GET à requêter
	 * @return la réponse en String
	 */
	public static String get(String url){
		
		URL obj;
		try {
			obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);

			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
		
		
		

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
			String utf8Param;
			try {
				utf8Param= URLEncoder.encode(params.get(cle), "UTF-8");
				retour += cle+"="+ utf8Param;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return retour;
	}

}
