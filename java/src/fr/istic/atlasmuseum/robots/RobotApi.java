package fr.istic.atlasmuseum.robots;

import java.util.ArrayList;
import java.util.List;

import fr.istic.atlasmuseum.utils.Requestor;

public class RobotApi implements Robot{

	private static final String BASE_URL = "http://geo-artiste.irisa.fr/api";
	
	public RobotApi(){
		
	}
	
	//GET
	public String selectArtisteByNomPrenom(String nom, String prenom){
		List<String> params = new ArrayList<String>();

		params.add(nom);
		params.add(prenom);
		String request = Requestor.generatGetRequestApi(BASE_URL+"/selectArtistesByNomPrenom", params);
		String resultRequest = Requestor.get(request);
		System.out.println(resultRequest);
		return resultRequest;
	}
	
	public String isExistAdresse(String pays, String region, String departement, String commune, String etablissement){
		List<String> params = new ArrayList<String>();
		params.add(pays);
		params.add(region);
		params.add(departement);
		params.add(commune);
		params.add(etablissement);
		String request = Requestor.generatGetRequestApi(BASE_URL+"/isExistAdresse", params);
		String resultRequest = Requestor.get(request);
		System.out.println(resultRequest);
		return resultRequest;
	}
	
	public String selectMot_clesByArtisteId(int idArtiste){
		List<String> params = new ArrayList<String>();
		params.add(Integer.toString(idArtiste));
		String request = Requestor.generatGetRequestApi(BASE_URL+"/selectMot_clesByArtisteId", params);
		String resultRequest = Requestor.get(request);
		System.out.println(resultRequest);
		return resultRequest;
	}
	
	public String selectOeuvresByArtisteId(int idArtiste){
		List<String> params = new ArrayList<String>();
		params.add(Integer.toString(idArtiste));
		String request = Requestor.generatGetRequestApi(BASE_URL+"/selectOeuvresByArtisteId", params);
		String resultRequest = Requestor.get(request);
		System.out.println(resultRequest);
		return resultRequest;
	}
	
	//POST
	public String createArtiste(String code,String nom, String prenom){
		List<String> params = new ArrayList<String>();
		params.add(code);
		params.add(nom);
		params.add(prenom);
		String request = Requestor.generatGetRequestApi(BASE_URL+"/createArtiste", params);
		String resultRequest = Requestor.post(request);
		System.out.println(resultRequest);
		return resultRequest;
		
	}
	
	public String createAdresse(String code,String pays, String region, String departement, String commune, String etablissement, float latitude, float longitude, float rayon){
		List<String> params = new ArrayList<String>();
		params.add(code);
		params.add(pays);
		params.add(region);
		params.add(departement);
		params.add(commune);
		params.add(etablissement);
		params.add(Float.toString(latitude));
		params.add(Float.toString(longitude));
		params.add(Float.toString(rayon));
		String request = Requestor.generatGetRequestApi(BASE_URL+"/createAdresse", params);
		String resultRequest = Requestor.post(request);
		System.out.println(resultRequest);
		return resultRequest;
	}
	
	public String createOeuvre(String code, int id_artiste, int id_adresse, String titre, String description, String periode, String autres){
		List<String> params = new ArrayList<String>();
		params.add(code);
		params.add(Integer.toString(id_artiste));
		params.add(Integer.toString(id_adresse));
		params.add(titre);
		params.add(description);
		params.add(periode);
		params.add(autres);
		String request = Requestor.generatGetRequestApi(BASE_URL+"/createOeuvre", params);
		String resultRequest = Requestor.post(request);
		System.out.println(resultRequest);
		return resultRequest;
	}
	
	public String createMot_Cle(String code, int id_artiste, String uri, String source){
		List<String> params = new ArrayList<String>();
		params.add(code);
		params.add(Integer.toString(id_artiste));
		params.add(uri);
		params.add(source);
		String request = Requestor.generatGetRequestApi(BASE_URL+"/createMotCle", params);
		String resultRequest = Requestor.post(request);
		System.out.println(resultRequest);
		return resultRequest;
	}
	
	public String deleteAll(String code){
		List<String> params = new ArrayList<String>();
		params.add(code);
		String request = Requestor.generatGetRequestApi(BASE_URL+"/deleteAll", params);
		String resultRequest = Requestor.post(request);
		System.out.println(resultRequest);
		return resultRequest;
	}
}
