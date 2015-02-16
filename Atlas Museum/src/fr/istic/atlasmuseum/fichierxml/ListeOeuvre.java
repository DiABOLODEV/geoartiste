package fr.istic.atlasmuseum.fichierxml;
import java.util.Date;


public class ListeOeuvre {

	private String Cote_du_dossier_aux_archives_du_MCC ;
	private String Pays; 
	private String Region;
	private String Departement;
	private String Commune;
	private String Nom_de_l_etablissement;
	private String Maitre_d_ouvrage_actuel;
	private String Nom_de_l_artiste;
	private String Prenom_de_l_artiste;
	private String Type_de_commission;
	private String   Date_de_presentation_devant_la_commission; 
	private String Periode;
	private String   Date_de_signature_de_l_arret_d_agrement;
	private String Annee_installation_de_l_oeuvre;
	private String Observations;
	private String Realise;
	private String Source_s;
	private String Titre_de_l_oeuvre;
	private String Descriptif_de_l_oeuvre;
	private String Photographie_de_l_oeuvre_oui;
	private String Nom_de_l_architecte;
	private String Prenom_de_l_architecte;
	private String Universite_Enseignement_sup_rieur;
	private String autres = "";
	
	public String getAutres() {
		return autres;
	}
	
	public void concatAutres(String autres) {
		if (this.autres.equals("")){
			this.autres = autres;
		}else{
			this.autres =this.autres+"__"+autres;
		}
	}
	
	public void setAutres(String autres){
		this.autres = autres;
	}
	
	public String getCote_du_dossier_aux_archives_du_MCC() {
		return Cote_du_dossier_aux_archives_du_MCC;
	}
	public void setCote_du_dossier_aux_archives_du_MCC(
			String cote_du_dossier_aux_archives_du_MCC) {
		Cote_du_dossier_aux_archives_du_MCC = cote_du_dossier_aux_archives_du_MCC;
	}
	public String getPays() {
		return Pays;
	}
	public void setPays(String pays) {
		Pays = pays;
	}
	public String getRegion() {
		return Region;
	}
	public void setRegion(String region) {
		Region = region;
	}
	public String getDepartement() {
		return Departement;
	}
	public void setDepartement(String departement) {
		Departement = departement;
	}
	public String getCommune() {
		return Commune;
	}
	public void setCommune(String commune) {
		Commune = commune;
	}
	public String getNom_de_l_etablissement() {
		return Nom_de_l_etablissement;
	}
	public void setNom_de_l_etablissement(String nom_de_l_etablissement) {
		Nom_de_l_etablissement = nom_de_l_etablissement;
	}
	public String getMaitre_d_ouvrage_actuel() {
		return Maitre_d_ouvrage_actuel;
	}
	public void setMaitre_d_ouvrage_actuel(String maitre_d_ouvrage_actuel) {
		Maitre_d_ouvrage_actuel = maitre_d_ouvrage_actuel;
	}
	public String getNom_de_l_artiste() {
		return Nom_de_l_artiste;
	}
	public void setNom_de_l_artiste(String nom_de_l_artiste) {
		Nom_de_l_artiste = nom_de_l_artiste;
	}
	public String getPrenom_de_l_artiste() {
		return Prenom_de_l_artiste;
	}
	public void setPrenom_de_l_artiste(String prenom_de_l_artiste) {
		Prenom_de_l_artiste = prenom_de_l_artiste;
	}
	public String getType_de_commission() {
		return Type_de_commission;
	}
	public void setType_de_commission(String type_de_commission) {
		Type_de_commission = type_de_commission;
	}
	
	public String getPeriode() {
		return Periode;
	}
	public void setPeriode(String periode) {
		Periode = periode;
	}
	
	public String getAnnee_installation_de_l_oeuvre() {
		return Annee_installation_de_l_oeuvre;
	}
	public void setAnnee_installation_de_l_oeuvre(
			String annee_installation_de_l_oeuvre) {
		Annee_installation_de_l_oeuvre = annee_installation_de_l_oeuvre;
	}
	public String getObservations() {
		return Observations;
	}
	public void setObservations(String observations) {
		Observations = observations;
	}
	public String getRealise() {
		return Realise;
	}
	public void setRealise(String realise) {
		Realise = realise;
	}
	public String getSource_s() {
		return Source_s;
	}
	public void setSource_s(String source_s) {
		Source_s = source_s;
	}
	public String getTitre_de_l_oeuvre() {
		return Titre_de_l_oeuvre;
	}
	public void setTitre_de_l_oeuvre(String titre_de_l_oeuvre) {
		Titre_de_l_oeuvre = titre_de_l_oeuvre;
	}
	public String getDescriptif_de_l_oeuvre() {
		return Descriptif_de_l_oeuvre;
	}
	public void setDescriptif_de_l_oeuvre(String descriptif_de_l_oeuvre) {
		Descriptif_de_l_oeuvre = descriptif_de_l_oeuvre;
	}
	public String getPhotographie_de_l_oeuvre_oui() {
		return Photographie_de_l_oeuvre_oui;
	}
	public void setPhotographie_de_l_oeuvre_oui(String photographie_de_l_oeuvre_oui) {
		Photographie_de_l_oeuvre_oui = photographie_de_l_oeuvre_oui;
	}
	public String getNom_de_l_architecte() {
		return Nom_de_l_architecte;
	}
	public void setNom_de_l_architecte(String nom_de_l_architecte) {
		Nom_de_l_architecte = nom_de_l_architecte;
	}
	public String getPrenom_de_l_architecte() {
		return Prenom_de_l_architecte;
	}
	public void setPrenom_de_l_architecte(String prenom_de_l_architecte) {
		Prenom_de_l_architecte = prenom_de_l_architecte;
	}
	public String getUniversite_Enseignement_sup_rieur() {
		return Universite_Enseignement_sup_rieur;
	}
	public void setUniversite_Enseignement_sup_rieur(
			String universite_Enseignement_sup_rieur) {
		Universite_Enseignement_sup_rieur = universite_Enseignement_sup_rieur;
	}
	
	public String getDate_de_presentation_devant_la_commission() {
		return Date_de_presentation_devant_la_commission;
	}
	public void setDate_de_presentation_devant_la_commission(
			String date_de_presentation_devant_la_commission) {
		Date_de_presentation_devant_la_commission = date_de_presentation_devant_la_commission;
	}
	public String getDate_de_signature_de_l_arret_d_agrement() {
		return Date_de_signature_de_l_arret_d_agrement;
	}
	public void setDate_de_signature_de_l_arret_d_agrement(
			String date_de_signature_de_l_arret_d_agrement) {
		Date_de_signature_de_l_arret_d_agrement = date_de_signature_de_l_arret_d_agrement;
	}
}
