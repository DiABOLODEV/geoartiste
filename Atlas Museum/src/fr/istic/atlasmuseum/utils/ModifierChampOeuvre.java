package fr.istic.atlasmuseum.utils;

import fr.istic.atlasmuseum.fichierxml.ListeOeuvre;

public class ModifierChampOeuvre {

	private ListeOeuvre oeuvre;

	public ModifierChampOeuvre(ListeOeuvre oeuvre){
		this.oeuvre = oeuvre;
	}

	public ListeOeuvre ajoutSimpleCote(){
		//Table artiste
		this.oeuvre.setNom_de_l_artiste(parcoursChaine(this.oeuvre.getNom_de_l_artiste()));
		this.oeuvre.setPrenom_de_l_artiste(parcoursChaine(this.oeuvre.getPrenom_de_l_artiste()));

		//Table adresse
		this.oeuvre.setPays(parcoursChaine(this.oeuvre.getPays()));
		this.oeuvre.setRegion(parcoursChaine(this.oeuvre.getRegion()));
		this.oeuvre.setDepartement(parcoursChaine(this.oeuvre.getDepartement()));
		this.oeuvre.setCommune(parcoursChaine(this.oeuvre.getCommune()));
		this.oeuvre.setNom_de_l_etablissement(parcoursChaine(this.oeuvre.getNom_de_l_etablissement()));

		//Table oeuvre
		this.oeuvre.setTitre_de_l_oeuvre(parcoursChaine(this.oeuvre.getTitre_de_l_oeuvre()));
		this.oeuvre.setDescriptif_de_l_oeuvre(parcoursChaine(this.oeuvre.getDescriptif_de_l_oeuvre()));
		this.oeuvre.setPeriode(parcoursChaine(this.oeuvre.getPeriode()));

		return this.oeuvre;
	}

	public String parcoursChaine(String chaine){
		int index = chaine.indexOf("'");
		String c = chaine;
		if(index != -1){
			c = chaine.substring(0, index+1)+"'";
			c = c + parcoursChaine(chaine.substring(index+1,chaine.length()));
		}
		return c;
	}
}
