package fr.istic.atlasmuseum.skos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Skos extends JFrame{
	
	private Document skosTree = null;
	private GlobalEntriesList allEntries = null;

	public Skos(String fichierSkos){
		allEntries = new GlobalEntriesList();
		this.skosTree = this.buildXMLTree();
		this.builConcept();
		List<indexedEntry> l = this.allEntries.getGlobalist();
		Iterator<indexedEntry> it = l.iterator();
		while (it.hasNext()){
			it.next().display();
		}
	}
	
	public Document buildXMLTree(){
		Document atree = null;
		InputStream sinput = null;
		File f = new File("files/skos.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		try {
			sinput = new FileInputStream(f);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		try {
			atree = builder.parse(new InputSource(sinput));
			sinput.close();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return atree;
	}
	
	public void builConcept(){
		System.out.println("buildConceptandDataList");
		NodeList nodes = null;
		XPath xPath = XPathFactory.newInstance().newXPath();
		if (skosTree == null) return;
		try {
			nodes = (NodeList)xPath.evaluate("/RDF/Concept",
					skosTree.getDocumentElement(), XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		int i = nodes.getLength()-1;
		while (i >= 0) {
			Node n = nodes.item(i);
			Attr attrType = (Attr) n.getAttributes().getNamedItem("rdf:about");
			if ( attrType != null){
				System.out.println("skos "+attrType.getValue());
				getAllEntries().add(new indexedEntry(attrType.getValue(),n));
			}
			i--;
		}
		Collections.sort(getAllEntries().getGlobalist(),indexedEntry.entryNameComparator);
	}
	
	
	public GlobalEntriesList getAllEntries() {
		return allEntries;
	}
}
