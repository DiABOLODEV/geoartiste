package fr.istic.atlasmuseum.command;

import fr.istic.atlasmuseum.controller.Controller;

public class RemplirBD implements Command{

	private Controller ctl;
	
	public RemplirBD(Controller ctl){
		this.ctl = ctl;
	}

	@Override
	public void execute() {
		this.ctl.remplirBD();
	}
}
