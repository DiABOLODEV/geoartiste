package fr.istic.atlasmuseum.parsers;

import java.io.ByteArrayInputStream;
import java.io.File;
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
	
	//Récupére la description de l'artiste, information qui permettra d'en tirer des choses
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
			description = "aucun résultat";
			
		}
		
		return description;

	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}
	
	
}
