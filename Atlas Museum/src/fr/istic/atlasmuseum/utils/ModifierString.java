package fr.istic.atlasmuseum.utils;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;

import fr.istic.atlasmuseum.fichierxml.ListeOeuvre;
import fr.istic.atlasmuseum.skos.indexedEntry;

public class ModifierString {

	public ModifierString(){

	}

	public ListeOeuvre normaliseOeuvre(ListeOeuvre oeuvre){
		//Table artiste
		oeuvre.setNom_de_l_artiste(mettreEnMinuscule(removeCaracteresSpeciaux(oeuvre.getNom_de_l_artiste())));
		oeuvre.setPrenom_de_l_artiste(mettreEnMinuscule(removeCaracteresSpeciaux(oeuvre.getPrenom_de_l_artiste())));

		//Table adresse
		oeuvre.setPays(mettreEnMinuscule(removeCaracteresSpeciaux(oeuvre.getPays())));
		oeuvre.setRegion(mettreEnMinuscule(removeCaracteresSpeciaux(oeuvre.getRegion())));
		oeuvre.setDepartement(mettreEnMinuscule(removeCaracteresSpeciaux(oeuvre.getDepartement())));
		oeuvre.setCommune(mettreEnMinuscule(removeCaracteresSpeciaux(oeuvre.getCommune())));
		oeuvre.setNom_de_l_etablissement(mettreEnMinuscule(removeCaracteresSpeciaux(oeuvre.getNom_de_l_etablissement())));

		//Table oeuvre
		oeuvre.setTitre_de_l_oeuvre(mettreEnMinuscule(removeCaracteresSpeciaux(oeuvre.getTitre_de_l_oeuvre())));
		oeuvre.setDescriptif_de_l_oeuvre(mettreEnMinuscule(removeCaracteresSpeciaux(oeuvre.getDescriptif_de_l_oeuvre())));
		oeuvre.setPeriode(mettreEnMinuscule(removeCaracteresSpeciaux(oeuvre.getPeriode())));
		oeuvre.setAutres(mettreEnMinuscule(removeCaracteresSpeciaux(oeuvre.getAutres())));
		
		return oeuvre;
	}
	
	public List<indexedEntry> normaliseMotCle(List<indexedEntry> listE){
		//Table artiste
		for(int i=0;i<listE.size();i++){
			String[] prefLabels = listE.get(i).getPreflabelWords();
			for(int j=0;j<prefLabels.length;j++){
				prefLabels[j]=mettreEnMinuscule(removeCaracteresSpeciaux(prefLabels[j]));
			}
			listE.get(i).setPreflabelWords(prefLabels);
			listE.get(i).setURI(mettreEnMinuscule(removeCaracteresSpeciaux(listE.get(i).getURI())));
		}
		return listE;
	}

	/*public String parcoursChaine(String chaine){
		int index = chaine.indexOf("'");
		String c = chaine;
		if(index != -1){
			c = chaine.substring(0, index+1)+"'";
			c = c + parcoursChaine(chaine.substring(index+1,chaine.length()));
		}
		return c;
	}*/
	
	public static String removeCaracteresSpeciaux(String source) {
		return Normalizer.normalize(source, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replaceAll("['-/#:]", "_").replaceAll(" ", "_");
	}
	
	public String splitResultatApi(String result){
		String[] tab = result.split("<");
		return tab[0];
	}
	
	public String mettreEnMinuscule(String result){
		return result.toLowerCase();
	}
	
	public HashMap<String,String> mappingAutres(String autres){
		HashMap<String,String> mapAutres = new HashMap<String, String>();
		String[] tabAutres;
		String[] tabChampAutres;
		String value="";
			tabAutres = autres.split("__");
			for(int j=0;j<tabAutres.length;j++){
				tabChampAutres = tabAutres[j].split("_");
				for (int k=1;k<tabChampAutres.length;k++){
					if(value.equals("")){
						value = tabChampAutres[k];
					}else{
						value += " "+tabChampAutres[k];
					}
				}
				System.out.println("##### "+ tabChampAutres[0]+ " => "+value);
				mapAutres.put(tabChampAutres[0], value);
				value="";
			}
		return mapAutres;
	}
}
