package fr.istic.atlasmuseum.button;

import javax.swing.JButton;

import fr.istic.atlasmuseum.command.Command;

public class Button extends JButton {
	
	private Command clickedCmd;
	private boolean push;
	public Button(String label){
		this.setText(label);
		this.push = false;
	}
	
	public Command getClickedCmd() {
		return clickedCmd;
	}

	public void setClickedCmd(Command cmd) {
		this.clickedCmd = cmd;		
	}
	
	public void push(){
		this.push = !this.push;
	}

	public boolean isPush() {
		return push;
	}
}
