package curling;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import curling.TeamPanel.ButtonListener;

public class StatusPanel extends JPanel{
	JTextField player, team;
	JButton undoButton;

	public StatusPanel() {
		setBorder(new TitledBorder (new EtchedBorder(), "Status"));
		setLayout(new GridLayout(3, 0));
		createLayout();
	}
	
	public void createLayout()
	{
		JPanel playerStatusPanel = new JPanel();
		playerStatusPanel.setBorder(new TitledBorder (new EtchedBorder(), "Player"));
		player =  new JTextField(10);
		player.setEditable(false);
		playerStatusPanel.add(player);
		
		JPanel teamStatusPanel = new JPanel();
		teamStatusPanel.setBorder(new TitledBorder (new EtchedBorder(), "Team"));
		team =  new JTextField(10);
		team.setEditable(false);
		teamStatusPanel.add(team);
		
		undoButton =  new JButton("UNDO");
		
		add(teamStatusPanel);
		add(playerStatusPanel);
		add(undoButton);
	}
	
	

}
