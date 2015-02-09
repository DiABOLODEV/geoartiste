package fr.istic.atlasmuseum.api;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.istic.atlasmuseum.button.Button;
import fr.istic.atlasmuseum.fichierxml.Parseur;

public class IHM extends JFrame implements ActionListener, ChangeListener{

	private JPanel panel;
	private Button xmlSkos;
	private Button xmlMinistere;
	private Button remplirBD;
	private Button clearBD;
	private Button analyseWikiSkos;

	public IHM(){

		panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		//Chargement fichier skos
		xmlSkos = new Button("Charger Skos");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.insets = new Insets(3, 3, 3, 3);
		xmlSkos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)
			{
				xmlSkos.getClickedCmd().execute();
			}
		});   
		panel.add((Component) xmlSkos,gbc);

		//Chargement fichier ministere
		xmlMinistere = new Button("Charger ministère");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.insets = new Insets(3, 3, 3, 3);
		xmlMinistere.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)
			{
				xmlMinistere.getClickedCmd().execute();
			}
		});   
		panel.add((Component) xmlMinistere,gbc);

		//Création artiste
		remplirBD = new Button("Créer artistes");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx=2;
		gbc.gridy=0;
		gbc.insets = new Insets(3, 3, 3, 3);
		remplirBD.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)
			{
				remplirBD.getClickedCmd().execute();
			}
		});   
		panel.add((Component) remplirBD,gbc);

		//Vider la bd
		clearBD = new Button("Vider BD");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.insets = new Insets(3, 3, 3, 3);
		clearBD.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)
			{
				clearBD.getClickedCmd().execute();
			}
		});   
		panel.add((Component) clearBD,gbc);

		//Croisé fichier skos et wiki et ajout dans BD
		analyseWikiSkos = new Button("Analyse Wiki Skos");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx=2;
		gbc.gridy=1;
		gbc.insets = new Insets(3, 3, 3, 3);
		analyseWikiSkos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)
			{
				analyseWikiSkos.getClickedCmd().execute();
			}
		});   
		panel.add((Component) analyseWikiSkos,gbc);

		setContentPane(panel);
		this.setResizable(false);
		this.setTitle("Metronome");
		this.setSize(550,120);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public Button getXmlSkos() {
		return xmlSkos;
	}

	public Button getXmlMinistere() {
		return xmlMinistere;
	}

	public Button getCreerArtistes() {
		return remplirBD;
	}

	public Button getClearBD() {
		return clearBD;
	}

	public Button getAnalyseWikiSkos() {
		return analyseWikiSkos;
	}
}
