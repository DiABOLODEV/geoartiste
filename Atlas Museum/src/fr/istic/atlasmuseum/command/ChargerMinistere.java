package fr.istic.atlasmuseum.command;

import fr.istic.atlasmuseum.controller.Controller;

public class ChargerMinistere implements Command {

	private Controller ctl;
	
	public ChargerMinistere(Controller ctl){
		this.ctl = ctl;
	}
	
	@Override
	public void execute() {
		this.ctl.chargerMinistere();
	}

}
