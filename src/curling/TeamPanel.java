package curling;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class TeamPanel extends JPanel{
	private String team;
	private JTextField score;
	private JLabel teamLabel;
	private JButton drawButton, takeButton, guardButton;
	private Purpose intention;

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
		drawButton = new JButton("Draw");
		takeButton = new JButton("Take");
		guardButton = new JButton("Guard");
		drawButton.addActionListener(new ButtonListener());
		takeButton.addActionListener(new ButtonListener());
		guardButton.addActionListener(new ButtonListener());
		buttonPanel.add(drawButton);
		buttonPanel.add(takeButton);
		buttonPanel.add(guardButton);

		JPanel scorePanel = new JPanel();
		scorePanel.setBorder(new TitledBorder (new EtchedBorder(), team + " score:"));
		score = new JTextField(5);
		teamLabel = new JLabel(team + ": ");
		scorePanel.add(teamLabel);
		scorePanel.add(score);

		add(buttonPanel);
		add(scorePanel);
	}

	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == drawButton) {
				intention = Purpose.DRAW;
			}
			if(e.getSource() == drawButton) {
				intention = Purpose.TAKEOUT;
			}
			if(e.getSource() == drawButton) {
				intention = Purpose.GUARD;
			}
		}
	}

	public Purpose getIntention() {
		return intention;
	}

	public void setIntention(Purpose purpose) {
		intention = purpose;
	}
}
