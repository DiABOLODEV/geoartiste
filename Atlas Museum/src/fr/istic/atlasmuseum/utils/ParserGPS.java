package fr.istic.atlasmuseum.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

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
	
	
	
	public static String[] analyseAnswer(String xmlResult){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		String longitude = "";
		String latitude = "";
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
			System.out.println(status);	
		} 
		//get longitude
		try {
			nodes = (NodeList)xPath.evaluate("/GeocodeResponse/result/geometry/location/lat",
					edoc.getDocumentElement(), XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		String values ="0";

		if (nodes.getLength() > 0) {
			values = nodes.item(0).getTextContent();
			//System.out.println(values);
			latitude = values;
		} else {
			//System.out.println("latitude not found");
			latitude = "latitude not found";
		}

		try {
			nodes = (NodeList)xPath.evaluate("/GeocodeResponse/result/geometry/location/lng",
					edoc.getDocumentElement(), XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		if (nodes.getLength() > 0) {
			values = nodes.item(0).getTextContent();
			//System.out.println(values);
			longitude = values;
		} else {
			//System.out.println("Longitude not found");
			longitude = "Longitude not found";
		}
		String[] r =  {latitude, longitude};
		return r;
	}

}
