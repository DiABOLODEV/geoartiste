package fr.istic.atlasmuseum.command;

import fr.istic.atlasmuseum.controller.Controller;

public class VerifOk implements Command{
	
	private Controller ctl;
	
	public VerifOk(Controller ctl){
		this.ctl = ctl;
	}

	@Override
	public void execute() {
		this.ctl.verifOk();
	}
}
