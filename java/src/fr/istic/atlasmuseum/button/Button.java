package fr.istic.atlasmuseum.button;

import javax.swing.JButton;

import fr.istic.atlasmuseum.command.Command;

public class Button extends JButton {
	
	private Command clickedCmd;
	
	public Button(String label){
		this.setText(label);
	}
	
	public Command getClickedCmd() {
		return clickedCmd;
	}

	public void setClickedCmd(Command cmd) {
		this.clickedCmd = cmd;		
	}
}
