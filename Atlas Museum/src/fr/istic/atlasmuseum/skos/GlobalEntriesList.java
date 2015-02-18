package fr.istic.atlasmuseum.skos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GlobalEntriesList {
	private List<indexedEntry> globalist = null;

	GlobalEntriesList(){
		System.out.println("Création globalEntriesList");
		globalist = new ArrayList<indexedEntry>();
	}
	void add(indexedEntry e){
		if (e == null) return;
		//e.display();
		globalist.add(e);
	}
	int size(){
		return globalist.size();
	}
	/*indexedEntry getEntry(int i){
		if (i < 0) return null;
		if (i >= size()) return null;
		return globalist.get(i);
	}
	indexedEntry getEntry(int i, indexedEntry.typeOfEntries t){
		if (i < 0) return null;
		if (i >= size()) return null;
		int j = 0;
		 for(indexedEntry temp: mykoba.getAllEntries().getGlobalist()){
			 if (temp.getEntryType() == t){
				 if (temp.getURI() != null){
					 if (j == i) return temp;
					 j++;
				 }
			 }
		 }
		return null;
	}*/

	void display(){
		Collections.sort(globalist,indexedEntry.entryNameComparator);
		for(indexedEntry temp: globalist){
			temp.display();
		}
	}
	
	/*void unsetFoundFlag(){
		for(indexedEntry temp:getGlobalist()){
			temp.setFound(false);
		}
	}
	indexedEntry searchConcept(String uri){
		if (uri == null) return null;
		for(indexedEntry temp2: getGlobalist()){
			if (temp2.getEntryType() == indexedEntry.typeOfEntries.concept){
				if (temp2.getURI().equals(uri)){
					return temp2;
				}
			}
		}
		return null;
	}
	indexedEntry searchURI(String uri){
		if (uri == null) return null;
		for(indexedEntry temp2: getGlobalist()){
			if (temp2.getURI().equals(uri)){
				return temp2;
			}
		}
		return null;
	}
	indexedEntry searchURIPart(String uri){
		if (uri == null) return null;
		for(indexedEntry temp2: getGlobalist()){
			if (temp2.getURI().toLowerCase().contains(uri.toLowerCase())){
				return temp2;
			}
		}
		return null;
	}
	indexedEntry searchLabel(String lab){
		if (lab == null) return null;
		String lowerlab = lab.toLowerCase();
		for(indexedEntry temp2: getGlobalist()){
			if (temp2.getPreflabel() != null){
				if (temp2.getPreflabel().toLowerCase().contains(lowerlab)){
					return temp2;
				}
			}
		}
		return null;
	}
	String check(){
		int count = 0;
		String result = "";
		// check for reference
		for(indexedEntry temp1: globalist){
			switch(temp1.getEntryType()){
			case data:
				Boolean found = false;
				if (searchConcept(temp1.getConceptOfIndex()) != null) found = true;
				if (found==false){
					System.out.println("CONCEPT NOT FOUND FOR ");
					result = result +  temp1.getURI() + "\n";
					temp1.display();
				}
				//check children
				NodeList list = temp1.getNode().getChildNodes();	
				int i = list.getLength()-1;
				while (i >= 0) {
					Node n = list.item(i);
					if (n.getAttributes() != null){
						Attr attrType = (Attr) n.getAttributes().getNamedItem("uri");
						if ( attrType != null){
							found=false;
							if (searchURI(attrType.getValue()) != null) found = true;
							if (found==false){
								result = result + "CONCEPT " + attrType.getValue() + " NOT FOUND FOR \n";
								result = result +  temp1.getURI() + "\n";
								//System.out.println("CONCEPT " + attrType.getValue() + " NOT FOUND FOR ");
								//temp1.display();
							}
						} 
					}
					i--;
				}
				break;
			}
		}
		//check for double entry (XXXX are palcehorders not to check)
		for(indexedEntry temp1: globalist){
			Boolean found = false;
			count++;
			if (!temp1.getURI().contains("XXXX")){
				for(indexedEntry temp2: globalist){
					if (temp2 != temp1){
						if (temp2.getURI().equals(temp1.getURI())){
							found = true;
							break;
						}
					}
				}
				if (found==true){
					result = result + "CONCEPT FOUND TWICE \n";
					result = result +  temp1.getURI() + "\n";
					//System.out.println("CONCEPT FOUND TWICE ");
					//temp1.display();

				}
			}
		}
		System.out.println("Found " + count + " uri");
		result = result + "Found " + count + " uri\n";
		return result;
	}*/
	public List<indexedEntry> getGlobalist() {
		return globalist;
	}
	public void setGlobalist(List<indexedEntry> globalist) {
		this.globalist = globalist;
	}
}
