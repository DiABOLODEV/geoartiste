package fr.istic.atlasmuseum.main;

import fr.istic.atlasmuseum.controller.Controller;
import fr.istic.atlasmuseum.ihm.IHM;
import fr.istic.atlasmuseum.robots.RobotApi;

public class Main {

	public static void main(String[] args) {
	
		//new RobotWikipedia();
		//RobotGPS r = new RobotGPS();
		new Controller(new IHM(),new RobotApi());
	}

}
