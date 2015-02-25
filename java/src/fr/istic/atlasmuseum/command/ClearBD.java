package fr.istic.atlasmuseum.command;

import fr.istic.atlasmuseum.controller.Controller;

public class ClearBD implements Command{

	private Controller ctl;
	
	public ClearBD(Controller ctl){
		this.ctl = ctl;
	}

	@Override
	public void execute() {
		ctl.clearBD();
	}
	
	
}
