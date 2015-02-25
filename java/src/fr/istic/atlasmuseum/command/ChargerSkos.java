package fr.istic.atlasmuseum.command;

import fr.istic.atlasmuseum.controller.Controller;

public class ChargerSkos implements Command{
	
	private Controller ctl;
	
	public ChargerSkos(Controller ctl){
		this.ctl = ctl;
	}

	@Override
	public void execute() {
		ctl.chargerSkos();
	}

}
