package fr.istic.atlasmuseum.parsers;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ParserWikipedia{
	private String xml;

	public ParserWikipedia() {
		
	}
	/**
	 * Récupére la description de l'artiste à partir du fichier xml, information qui permettra d'en tirer des choses
	 * @return la description de l'artiste
	 */
	
	public String getDescription(){
		
		String description = "";
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
			NodeList nodeList = document.getDocumentElement().getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if(node.getNodeName() == "Section"){
					Element element = (Element) node;
					description = element.getElementsByTagName("Description").item(0).getChildNodes().item(0).getNodeValue();
				}
			
			}

			
		} catch (ParserConfigurationException | SAXException | IOException | NullPointerException e) {
			//pas de résultats;
			description = "";
			
		}
		
		return description;

	}
	public String sanitize(String str){
		//System.out.println("regex:"+str.replaceAll ( "\\.|\\,|\\-|\\(|\\)|\\_"," " ).toLowerCase());
		return str.replaceAll ( "\\.|\\,|\\-|\\(|\\)|\\_"," " ).toLowerCase();
		
	}
	/**
	 * Récupére la liste de mots qui compose la description
	 * 
	 * @param description la description de l'artiste
	 * @return la liste de mot qui compose cette description
	 */
	public String[] getDescriptionWords(String description){
		return sanitize(description).split(" ");
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}
	
	
}
