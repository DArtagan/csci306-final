package curling;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class TeamPanel extends JPanel{
	private String team;
	private JTextField score;
	private JLabel teamLabel;
	
	public TeamPanel(String Team){
		this.team = Team;
		setSize(400, 200);
		setBorder(new TitledBorder (new EtchedBorder(), team));
		setLayout(new GridLayout(2, 0));
		createLayout();
		setVisible(true);
	}
	
	public void createLayout(){
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 3));
		buttonPanel.setBorder(new TitledBorder (new EtchedBorder(), team + " Actions"));
		JButton drawButton = new JButton("Draw");
		JButton takeButton = new JButton("Take");
		JButton gaurdButton = new JButton("Guard");
		buttonPanel.add(drawButton);
		buttonPanel.add(takeButton);
		buttonPanel.add(gaurdButton);
		
		JPanel scorePanel = new JPanel();
		scorePanel.setBorder(new TitledBorder (new EtchedBorder(), team + " score:"));
		score = new JTextField(5);
		teamLabel = new JLabel(team + ": ");
		scorePanel.add(teamLabel);
		scorePanel.add(score);
		
		add(buttonPanel);
		add(scorePanel);
	}
}