package fr.istic.atlasmuseum.skos;

import java.util.ArrayList;
import java.util.Comparator;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class indexedEntry implements Comparable<indexedEntry> {
	// the last number is to have the action in setFieldValueAsString (must be added with the entry here)
	static final String nodeFieldDescription[][] = {
		{"data","choice","data concept","","att","skos:Concept","0"}, // entry format description
		{"data","text","data uri","","att","uri","1"},
		{"all","text","data label","","tag","skos:prefLabel","2"},
		{"all","text","alt1 label","","tag","skos:altLabel","3"},
		{"all","text","alt2 label","","tag","skos:altLabel","4"},
		{"all","text","alt3 label","","tag","skos:altLabel","5"},
		{"data","choice","country label","#country","tag","place","6"}, //#country is a filter on the entry concept (work for data only)
		{"data","choice","organization label","#organization","tag","organization","7"},
		{"both","choice","prop1 label","","tag","skos:related","8"},
		{"both","choice","prop2 label","","tag","skos:related","9"},
		{"both","choice","related","","tag","skos:related","10"},
		{"concept","choice","narrower","","tag","skos:narrower","11"},
		{"concept","choice","broader","","tag","skos:broader","12"},
		{"all","text","description","","tag","skos:definition","12"}
	}; 
//	conceptTextField 
//	 uriTextField 
//	 labelTextField 
//	 alt1TextField 
//	 alt2TextField 
//	 alt3TextField 
//	 organizationTextField 
//	 countryTextField 
//	 placeTextField 
//	 linkValueTextField 
//	 linkConceptTextField 
//	 prop1TopicTextField 
//	 prop2TopicTextField 
//	 prop3TopicTextField 
//	 prop4TopicTextField 
//	 fieldTextField 
//	 peopleTextField 
//	 measurementTextField 
//	 documentTextField 
//	 technology1TextField 
//	 technology2TextField 
//	 challengeTextField 
//	 fundingTextField 
//	 activityTextField 
//	 mykoba1TextField 
//	 mykoba2TextField 
	private String[] fieldValueAsString;
	private String URI;
	private String preflabel;
	private String[] preflabelWords;
	private Boolean found;
	public enum typeOfEntries {
		conceptscheme, concept, data, unknown
	};
	public enum typeOfConcept {
		topic, fieldconcept, organization, people, measurement, document, technology, 
		when, challenge, place, link, funding, activity, mykoba, unknown
	};
	private String uriConceptScheme = "";
	private typeOfEntries entryType;
	private typeOfConcept entryConcept;
	private String conceptOfIndex = null;
	private Node node;
	ArrayList children = null;
	
	indexedEntry(String uri, Node n){
		super();
		setURI(uri.trim());
		setNode(n);
		fieldValueAsString = new String[nodeFieldDescription.length];
		entryType = typeOfEntries.unknown;
		children = new ArrayList();
		if (n!= null){
			int j;
			Attr attrType = null;
			String tagname =  n.getNodeName();
			NodeList list = getNode().getChildNodes();
			//Look the content
			for(j=0; j<list.getLength(); j++){
				Node c= list.item(j);
				if (c.getNodeType() == Node.ELEMENT_NODE){
					String cname =  c.getNodeName();
				}
			}
			
			if ("skos:Concept".equals(tagname)){
				entryType = typeOfEntries.concept;
				//look for the label
				setPreflabel("");
				for(j=0; j<list.getLength(); j++){
					switch(list.item(j).getNodeType()) {
					case Node.ELEMENT_NODE:
						String childname =  list.item(j).getNodeName();
						if ("skos:prefLabel".equals(childname)){
							setPreflabel(list.item(j).getTextContent().trim());
							setPreflabelWords(getPreflabel().toLowerCase().split(" "));
							//System.out.println(" ++++ "+ list.item(j).getTextContent());
						} else if ("skos:inScheme".equals(childname)){
							attrType = (Attr) list.item(j).getAttributes().getNamedItem("rdf:resource");
							if ( attrType != null){
								//System.out.println("Concept being set to : " + attrType.getValue());
								setUriConceptScheme(attrType.getValue());
							}
						}
						break;
					}
				}
			} else if ("skos:ConceptScheme".equals(tagname)){
				entryType = typeOfEntries.conceptscheme;
				
			} else if ("data".equals(tagname)){
				entryType = typeOfEntries.data;
				attrType = (Attr) n.getAttributes().getNamedItem("skos:Concept");
				if ( attrType != null){
					setConceptOfIndex(attrType.getValue());
				}
			}
		}
	}
	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
	}
	
	void display(){
		switch (entryType) {
        case conceptscheme:
        	System.out.println("Concept scheme : " + URI);
            break;
        case concept:
        	System.out.println("Concept : " + URI + "  ("+ getPreflabel() +")");
            break;
        case data:
        	if (getConceptOfIndex()  == null)
        		System.out.println("data : " + URI);
        	else
        		System.out.println("data : " + URI + " IS A " + getConceptOfIndex() );
            break;
        default:
        	System.out.println("???? : " + URI);
            break;
		}
		
	}
	
	public int compareTo(indexedEntry e) {
		return this.getURI().compareTo(e.getURI());
	}
	
	public static Comparator<indexedEntry> entryNameComparator = new Comparator<indexedEntry>() {

		public int compare(indexedEntry e1, indexedEntry e2) {

			String se1 = e1.getURI().toUpperCase();
			String se2 = e2.getURI().toUpperCase();

			//ascending order
			return se1.compareTo(se2);
			//descending order
			//return se1.compareTo(se2);
		}
	};

	class MyErrorListener implements ErrorListener {
	    public void warning(TransformerException e)
	                throws TransformerException {
	        show("Warning",e);
	        throw(e);
	    }
	    public void error(TransformerException e)
	                throws TransformerException {
	        show("Error",e);
	        throw(e);
	    }
	    public void fatalError(TransformerException e)
	                throws TransformerException {
	        show("Fatal Error",e);
	        throw(e);
	    }
	    private void show(String type,TransformerException e) {
	        System.out.println(type + ": " + e.getMessage());
	        if(e.getLocationAsString() != null)
	            System.out.println(e.getLocationAsString());
	    }
	}
	
	private String nodeToString(Node node, String outs) {
		NodeList list = getNode().getChildNodes();
		for(int j=0; j<list.getLength(); j++){
			switch(list.item(j).getNodeType()) {
			case Node.ELEMENT_NODE:
				String childname =  list.item(j).getNodeName();
				outs = outs + childname + "   ";
				outs = outs + list.item(j).getTextContent();
				Attr attrType = (Attr) list.item(j).getAttributes().getNamedItem("uri");
				if ( attrType != null){
					outs = outs +  attrType.getValue()+ "\n";
				}
				attrType = (Attr) list.item(j).getAttributes().getNamedItem("rdf:resource");
				if ( attrType != null){
					outs = outs +  attrType.getValue()+ "\n";
				}
				attrType = (Attr) list.item(j).getAttributes().getNamedItem("rdf:about");
				if ( attrType != null){
					outs = outs +  attrType.getValue()+ "\n";
				}
				outs = outs + "\n";
			}
		}
		//outs.concat(node.getTextContent());
		//outs.concat("\n");
		return outs;
	}
	//Concept start with #xxx 
	Boolean isEntryTaggedWithConcept(String tc){
		if (getUriConceptScheme().length()>0){
			if(getUriConceptScheme().contains(tc)) return true;
		}
		Attr attrType = (Attr) getNode().getAttributes().getNamedItem("skos:Concept");
		if ( attrType != null){
			if(attrType.getValue().contains(tc)) return true;
		}
		return false;
	}
	String getNodeText(){
		String outs = this.getURI() + "\n";	
		if (getUriConceptScheme().length() > 0) outs = outs + this.getUriConceptScheme() + "\n";
		if (getNode()== null) return null;
		return nodeToString(getNode(),outs);
	}
	/*void addConcept(indexedEntry nc){
		Element ncn = mykoba.getStorageTree().createElement("property");
		ncn.setAttribute("uri", nc.getURI());
		getNode().appendChild(ncn);
	}
	void addData(indexedEntry nc){
		Element ncn = mykoba.getStorageTree().createElement("data");
		ncn.setAttribute("uri", nc.getURI());
		getNode().appendChild(ncn);
	}*/
	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	public String getConceptOfIndex() {
		return conceptOfIndex;
	}
	public void setConceptOfIndex(String conceptOfIndex) {
		this.conceptOfIndex = conceptOfIndex;
	}
	public typeOfEntries getEntryType() {
		return entryType;
	}
	public void setEntryType(typeOfEntries entryType) {
		this.entryType = entryType;
	}
	public String getPreflabel() {
		return preflabel;
	}
	public void setPreflabel(String preflabel) {
		this.preflabel = preflabel;
	}
	public Boolean getFound() {
		return found;
	}
	public void setFound(Boolean found) {
		this.found = found;
	}
	public typeOfConcept getEntryConcept() {
		return entryConcept;
	}
	public void setEntryConcept(typeOfConcept entryConcept) {
		this.entryConcept = entryConcept;
	}
	public String[] getPreflabelWords() {
		return preflabelWords;
	}
	public void setPreflabelWords(String[] preflabelWords) {
		this.preflabelWords = preflabelWords;
	}
	public static String[][] getNodeFieldDescription() {
		return nodeFieldDescription;
	}
	public String getUriConceptScheme() {
		return uriConceptScheme;
	}
	public void setUriConceptScheme(String uriConceptScheme) {
		this.uriConceptScheme = uriConceptScheme;
	}
	/*public void setFieldValueAsString(int index, String value) {
		Element ncn = null;
		fieldValueAsString[index] = value;
		// need also to update the node...
		System.out.println("Updating node " + index + "  " + value);
		switch (index){
		case 0:
			//TODO
			break;
		case 2: //skos:prefLabel
			// need to remove the node if already exist... TODO
			if (getEntryType() == typeOfEntries.data){
				ncn = mykoba.getStorageTree().createElement("skos:prefLabel");
			} else {
				ncn = mykoba.getSkosTree().createElement("skos:prefLabel");
			}
			ncn.setTextContent(value);
			getNode().appendChild(ncn);
			break;
		case 3: //skos:altLabel
		case 4:
		case 5:
			if (getEntryType() == typeOfEntries.data){
				ncn = mykoba.getStorageTree().createElement("skos:altLabel");
			} else {
				ncn = mykoba.getSkosTree().createElement("skos:altLabel");
			}
			ncn.setTextContent(value);
			getNode().appendChild(ncn);
			break;
		case 6: //#country - place for data only
			if (getEntryType() == typeOfEntries.data){
				System.out.println("Updating node -2- " + index + "  " + value);
				ncn = mykoba.getStorageTree().createElement("place");
				ncn.setAttribute("uri", value);
				getNode().appendChild(ncn);
			} else {
				System.out.println("Entry is not a data");
			}
			break;
		case 7:
			//#organization
			if (getEntryType() == typeOfEntries.data){
				System.out.println("Updating node -2- " + index + "  " + value);
				ncn = mykoba.getStorageTree().createElement("organization");
				ncn.setAttribute("uri", value);
				getNode().appendChild(ncn);
			} else {
				System.out.println("Entry is not a data");
			}
			break;
		case 8: //
		case 9:
		case 10: //skos:related (let's use this instead of property) works for data and concept
			if (getEntryType() == typeOfEntries.data){
				ncn = mykoba.getStorageTree().createElement("skos:related");
			} else {
				ncn = mykoba.getSkosTree().createElement("skos:related");
			}
			ncn.setAttribute("rdf:resource", value);
			getNode().appendChild(ncn);
			break;
		case 11: // skos:narrower
			if (getEntryType() == typeOfEntries.concept){
				ncn = mykoba.getSkosTree().createElement("skos:narrower");
				ncn.setAttribute("rdf:resource", value);
				getNode().appendChild(ncn);
			} else {
				System.out.println("Entry is not a concept");
			}
			break;
		case 12:
			//skos:broader
			if (getEntryType() == typeOfEntries.concept){
				ncn = mykoba.getSkosTree().createElement("skos:broader");
				ncn.setAttribute("rdf:resource", value);
				getNode().appendChild(ncn);
			} else {
				System.out.println("Entry is not a concept");
			}
			break;
		case 13:
			if (getEntryType() == typeOfEntries.data){
				ncn = mykoba.getStorageTree().createElement("skos:definition");
			} else {
				ncn = mykoba.getSkosTree().createElement("skos:definition");
			}
			ncn.setTextContent(value);
			getNode().appendChild(ncn);
			break;
		default:
			System.out.println("Unknow index in setFieldValueAsString");
		}
		
	}*/
	public String getFieldValueAsString(int index) {
		return fieldValueAsString[index];
	}
}
