package fr.istic.geoartiste.models;

import fr.istic.geoartiste.activities.GenericActivity;

/**
 * un element du menu
 *
 */
public class MenuItem {
	public String title;
	public GenericActivity activity;

	public MenuItem(String title, GenericActivity activity) {
		this.title = title;
		this.activity = activity;
	}

	
}