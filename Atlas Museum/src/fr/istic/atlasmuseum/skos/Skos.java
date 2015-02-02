package fr.istic.atlasmuseum.skos;

import java.awt.FileDialog;
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
	
	private String skosPath;
	private Document skosTree = null;
	private GlobalEntriesList allEntries = null;

	public Skos(){
		allEntries = new GlobalEntriesList();
		this.skosPath = this.selectFile("Sélectionner le fichier skos xml");
		//this.skosPath = "skos.xml";
		System.out.println(this.skosPath);
		this.skosTree = this.buildXMLTree(skosPath);
		this.builConcept();
		List<indexedEntry> l = this.allEntries.getGlobalist();
		Iterator<indexedEntry> it = l.iterator();
		while (it.hasNext()){
			it.next().display();
		}
	}

	public String selectFile(String title){
		//FileDialog fd = new FileDialog(fra, "Choose a file", FileDialog.LOAD);
		/*if (dir) {
			System.setProperty("apple.awt.fileDialogForDirectories", "true");
		} else {
			System.setProperty("apple.awt.fileDialogForDirectories", "false");
		}*/
		FileDialog fd = new FileDialog(this, title, FileDialog.LOAD);
		//fd.setTitle("Choose a file ");
		//fd.setFilenameFilter(new FolderFilter());
		//fd.setDirectory("/Users/bodin/Desktop/");
		//fd.setFile("*.pdf");
		fd.setVisible(true);
		String filename = fd.getDirectory() + fd.getFile();
		System.out.println("You chose " + filename);
		return filename;
	}
	
	public Document buildXMLTree(String filepathsmane){
		System.out.println("builXMLTree"+filepathsmane);
		Document atree = null;
		InputStream sinput = null;
		File f = new File(filepathsmane);
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
