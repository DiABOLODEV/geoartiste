package fr.istic.atlasmuseum.main;

import fr.istic.atlasmuseum.controller.Controller;
import fr.istic.atlasmuseum.ihm.IHM;
import fr.istic.atlasmuseum.robots.RobotApi;
import fr.istic.atlasmuseum.robots.RobotGPS;
import fr.istic.atlasmuseum.robots.RobotWikipedia;
import fr.istic.atlasmuseum.skos.Skos;

public class Main {

	public static void main(String[] args) {
	
		//new RobotWikipedia();
		//new RobotGPS();
		//new Skos();
		new Controller(new IHM(),new RobotApi());
	}

}
