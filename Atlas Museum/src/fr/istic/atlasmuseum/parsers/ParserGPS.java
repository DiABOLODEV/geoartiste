package fr.istic.atlasmuseum.parsers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ParserGPS {
	
	
	
	public static HashMap<String,String> analyseAnswer(String xmlResult){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		String longitude = "";
		String latitude = "";
		String adresse = "";
		String ville = "";
		String departement = "";
		String longitudeSouthwest = "";
		String latitudesouthwest = "";
		String longitudeNortheast = "";
		String latitudeNortheast = "";
		try {
			builder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList nodes = null;

		Document edoc = null;

		try {
			edoc = builder.parse(new InputSource(new ByteArrayInputStream(xmlResult.getBytes("utf-8"))));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//check status
		String status ="0";
		try {
			nodes = (NodeList)xPath.evaluate("/GeocodeResponse/status",
					edoc.getDocumentElement(), XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		if (nodes.getLength() > 0) {
			status = nodes.item(0).getTextContent();

		} 
		//get latitude
		try {
			nodes = (NodeList)xPath.evaluate("/GeocodeResponse/result/geometry/location/lat",
					edoc.getDocumentElement(), XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		String values ="0";

		if (nodes.getLength() > 0) {
			values = nodes.item(0).getTextContent();
			
			latitude = values;
		} else {
			
			return null;
		}

		//get longitude
		try {
			nodes = (NodeList)xPath.evaluate("/GeocodeResponse/result/geometry/location/lng",
					edoc.getDocumentElement(), XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		if (nodes.getLength() > 0) {
			values = nodes.item(0).getTextContent();
			
			longitude = values;
		} else {
			
			return null;
		}
		
		//get adress
		try {
			nodes = (NodeList)xPath.evaluate("/GeocodeResponse/result/formatted_address",
					edoc.getDocumentElement(), XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		
		if (nodes.getLength() > 0) {
			values = nodes.item(0).getTextContent();
			
			adresse = values;
		} else {
			
			return null;
		}
		
		//get commune
				try {
					nodes = (NodeList)xPath.evaluate("/GeocodeResponse/result/address_component[type/text()='locality']/long_name",
							edoc.getDocumentElement(), XPathConstants.NODESET);
				} catch (XPathExpressionException e) {
					e.printStackTrace();
				}

				
				if (nodes.getLength() > 0) {
					values = nodes.item(0).getTextContent();
					ville = values;
				} else {

					return null;
				}
				
			//get departement
				try {
					nodes = (NodeList)xPath.evaluate("/GeocodeResponse/result/address_component[type/text()='administrative_area_level_2']/long_name",
							edoc.getDocumentElement(), XPathConstants.NODESET);
				} catch (XPathExpressionException e) {
					e.printStackTrace();
				}

				
				if (nodes.getLength() > 0) {
					values = nodes.item(0).getTextContent();
					departement = values;
				} else {

					return null;
				}
				
				//get latitudeSouthwest
				
				try {
					nodes = (NodeList)xPath.evaluate("/GeocodeResponse/result/geometry/bounds/southwest/lat",
							edoc.getDocumentElement(), XPathConstants.NODESET);
				} catch (XPathExpressionException e) {
					e.printStackTrace();
				}

				
				if (nodes.getLength() > 0) {
					values = nodes.item(0).getTextContent();
					latitudesouthwest = values;
				} else {

					return null;
				}
				
				
			
				//get longitudeSouthwest
				try {
					nodes = (NodeList)xPath.evaluate("/GeocodeResponse/result/geometry/bounds/southwest/lng",
							edoc.getDocumentElement(), XPathConstants.NODESET);
				} catch (XPathExpressionException e) {
					e.printStackTrace();
				}

				if (nodes.getLength() > 0) {
					values = nodes.item(0).getTextContent();
					
					longitudeSouthwest = values;
				} else {
					
					return null;
				}
				
				
				//get latitudeNortheast
				try {
					nodes = (NodeList)xPath.evaluate("/GeocodeResponse/result/geometry/bounds/northeast/lat",
							edoc.getDocumentElement(), XPathConstants.NODESET);
				} catch (XPathExpressionException e) {
					e.printStackTrace();
				}
				//String valuesNord ="0";

				if (nodes.getLength() > 0) {
					values = nodes.item(0).getTextContent();
					
					latitudeNortheast = values;
				} else {
					
					return null;
				}
                
				
				//get longitudeNortheast
				try {
					nodes = (NodeList)xPath.evaluate("/GeocodeResponse/result/geometry/bounds/northeast/lng",
							edoc.getDocumentElement(), XPathConstants.NODESET);
				} catch (XPathExpressionException e) {
					e.printStackTrace();
				}

				if (nodes.getLength() > 0) {
					values = nodes.item(0).getTextContent();
					
					longitudeNortheast = values;
				} else {
					
					return null;
				}
		
		HashMap<String, String> r = new HashMap<String, String>();
		r.put("latitude", latitude);
		r.put("longitude", longitude);
		r.put("adresse", adresse);
		r.put("ville", ville);
		r.put("departement", departement);
		r.put("latitudeSud", latitudesouthwest);
		r.put("longitudeSud", longitudeSouthwest);
		r.put("longitudeNord", longitudeNortheast);
		r.put("latitudeNord", latitudeNortheast);
		return r;
	}

}
