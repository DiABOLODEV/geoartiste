package fr.istic.atlasmuseum.fichierxml;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Parseur  extends DefaultHandler{

	private InputStream FIS;
	private SAXParser saxParser;
	private StringBuilder buffer;

	private ArrayList<ListeOeuvre> oeuvre= new ArrayList<ListeOeuvre>();

	//	private HashMap<String,String> dico;
	private String original;

	public Parseur (String original){
		this.original = original;
		try {
			// Creation d'un parser SAX
			saxParser = SAXParserFactory.newInstance().newSAXParser();
			// Ouverture du fichier XML
			FIS= new FileInputStream(original+".xml");

			//FIS = getClass().getResourceAsStream(original+".xml");
			// Lecture du fichier XML
			saxParser.parse(FIS, this);
		} catch (Exception e) {
			e.printStackTrace();
		} 			
	}

	public Parseur (){
		//	liArtistes = new HashMap<String,String>();
		try {
			// Creation d'un parser SAX
			saxParser = SAXParserFactory.newInstance().newSAXParser();
		} catch (Exception e) {
			e.printStackTrace();
		} 			
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (buffer != null)
			buffer.append(new String(ch,start,length));
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		int indice=oeuvre.size();
		if(indice>0)
			indice--;

		if (qName.equals("Cote_du_dossier_aux_archives_du_MCC")){
			oeuvre.get(indice).setCote_du_dossier_aux_archives_du_MCC(buffer.toString());
			if(!buffer.toString().equals("")){
				oeuvre.get(indice).concatAutres("cote_"+buffer.toString());
			}
		}
		else if(qName.equals("Pays"))
			oeuvre.get(indice).setPays(buffer.toString());

		else if (qName.equals("Region"))
			oeuvre.get(indice).setRegion(buffer.toString());

		else if (qName.equals("Departement"))
			oeuvre.get(indice).setDepartement(buffer.toString());

		else if (qName.equals("Commune"))
			oeuvre.get(indice).setCommune(buffer.toString());

		else if (qName.equals("Nom_de_l_etablissement"))
			oeuvre.get(indice).setNom_de_l_etablissement(buffer.toString());

		else if (qName.equals("Maitre_d_ouvrage_actuel")){
			oeuvre.get(indice).setMaitre_d_ouvrage_actuel(buffer.toString());
			if(!buffer.toString().equals("")){
				oeuvre.get(indice).concatAutres("maitreOuvrage_"+buffer.toString());
			}
		}

		else if (qName.equals("Nom_de_l_artiste"))
			oeuvre.get(indice).setNom_de_l_artiste(buffer.toString());

		else if (qName.equals("Prenom_de_l_artiste"))
			oeuvre.get(indice).setPrenom_de_l_artiste(buffer.toString());

		else if(qName.equals("Type_de_commission")){
			oeuvre.get(indice).setType_de_commission(buffer.toString());
			if(!buffer.toString().equals("")){
				oeuvre.get(indice).concatAutres("typeCommission_"+buffer.toString());
			}
		}

		else if(qName.equals("Date_de_presentation_devant_la_commission")){
			oeuvre.get(indice).setDate_de_presentation_devant_la_commission((buffer.toString()));
			if(!buffer.toString().equals("")){		
				oeuvre.get(indice).concatAutres("dateCommission_"+buffer.toString());
			}
		}

		else if(qName.equals("Periode"))
			oeuvre.get(indice).setPeriode(buffer.toString());	

		else if(qName.equals("Date_de_signature_de_l_arret_d_agrement")){
			oeuvre.get(indice).setDate_de_signature_de_l_arret_d_agrement(buffer.toString());
			if(!buffer.toString().equals("")){
				oeuvre.get(indice).concatAutres("dateSignature_"+buffer.toString());
			}
		}

		else if(qName.equals("Annee_installation_de_l_oeuvre")){
			oeuvre.get(indice).setAnnee_installation_de_l_oeuvre(buffer.toString());
			if(!buffer.toString().equals("")){
				oeuvre.get(indice).concatAutres("anneeOeuvre_"+buffer.toString());
			}
		}

		else if(qName.equals("Observations")){	
			oeuvre.get(indice).setObservations(buffer.toString());
			if(!buffer.toString().equals("")){
				oeuvre.get(indice).concatAutres("observations_"+buffer.toString());
			}
		}

		else if(qName.equals("Realise")){
			oeuvre.get(indice).setRealise(buffer.toString());
			if(!buffer.toString().equals("") && !buffer.toString().equals("?")){
				oeuvre.get(indice).concatAutres("realise_"+buffer.toString());
			}
		}

		else if(qName.equals("Source_s")){	
			oeuvre.get(indice).setSource_s(buffer.toString());
			if(!buffer.toString().equals("")){
				oeuvre.get(indice).concatAutres("source_"+buffer.toString());
			}
		}

		else if(qName.equals("Titre_de_l_oeuvre"))
			oeuvre.get(indice).setTitre_de_l_oeuvre(buffer.toString());

		else if(qName.equals("Descriptif_de_l_oeuvre"))
			oeuvre.get(indice).setDescriptif_de_l_oeuvre(buffer.toString());

		else if(qName.equals("Photographie_de_l_oeuvre_oui")){	
			oeuvre.get(indice).setPhotographie_de_l_oeuvre_oui(buffer.toString());
			if(!buffer.toString().equals("")){
				oeuvre.get(indice).concatAutres("photographie_"+buffer.toString());
			}
		}

		else if(qName.equals("Nom_de_l_architecte")){
			oeuvre.get(indice).setNom_de_l_architecte(buffer.toString());
			if(!buffer.toString().equals("")){
				oeuvre.get(indice).concatAutres("nomArchitecte_"+buffer.toString());
			}
		}


		else if(qName.equals("Prenom_de_l_architecte")){
			oeuvre.get(indice).setPrenom_de_l_architecte(buffer.toString());
			if(!buffer.toString().equals("")){
				oeuvre.get(indice).concatAutres("prenomArchitecte_"+buffer.toString());
			}
		}

		else if(qName.equals("Universite_Enseignement_sup_rieur")){
			oeuvre.get(indice).setUniversite_Enseignement_sup_rieur(buffer.toString());
			if(!buffer.toString().equals("")){
				oeuvre.get(indice).concatAutres("univEnsSup_"+buffer.toString());
			}
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (qName.equals("Maliste") )
			oeuvre.add(new ListeOeuvre());

		else 
			if (!qName.equals("Liste_Artiste"))
				buffer = new StringBuilder();

	}	

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		if (original == this.original)
			return;
		try {
			// Ouverture du nouveau fichier XML
			FIS = getClass().getResourceAsStream(original+".xml");
			// Vidage des resultats de l'ancienne langue
			oeuvre.clear();
			// Lecture du nouveau fichier XML
			saxParser.parse(FIS, this);
			this.original = original;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	public ArrayList<ListeOeuvre> getOeuvre() {
		return oeuvre;
	}
	public void setOeuvre(ArrayList<ListeOeuvre> oeuvre) {
		this.oeuvre = oeuvre;
	}

}
