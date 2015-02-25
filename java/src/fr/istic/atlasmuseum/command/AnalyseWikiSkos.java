package fr.istic.atlasmuseum.command;

import fr.istic.atlasmuseum.controller.Controller;

public class AnalyseWikiSkos implements Command{

	private Controller ctl;
	
	public AnalyseWikiSkos(Controller ctl){
		this.ctl = ctl;
	}

	@Override
	public void execute() {
		ctl.analyseWikiSkos();
	}	
}
