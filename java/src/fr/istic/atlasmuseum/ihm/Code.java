package fr.istic.atlasmuseum.ihm;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.istic.atlasmuseum.button.Button;

public class Code extends JFrame implements ActionListener, ChangeListener{
	
	private JPanel panel;
	private Button verifOk;
	private JTextField code;
	private boolean push = false;
	
	public Code(){
		panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Saisie du code
		//Vérification code ok
		this.code = new JTextField("truc truc truc");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.insets = new Insets(3, 3, 3, 3);
		panel.add((Component) this.code,gbc);
		
		//Vérification code ok
		verifOk = new Button("OK");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.insets = new Insets(3, 3, 3, 3);
		verifOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)
			{
				verifOk.push();
				verifOk.getClickedCmd().execute();
			}
		});   
		panel.add((Component) verifOk,gbc);
		
		setContentPane(panel);
		this.setResizable(false);
		this.setTitle("Vérification du code");
		this.setSize(550,120);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JTextField getCode() {
		return code;
	}

	public Button getVerifOk() {
		return verifOk;
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
