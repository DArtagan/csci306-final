package curling;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class CurlingMatch extends JFrame {
	private int turn;
	public static Player currentPlayer;
	private HashMap<Team, LinkedList<Integer>> score;
	private LinkedList<Player> homeTeam, awayTeam;
	private House house;
	public static Purpose intention;
	TeamPanel homePanel, awayPanel;
	StatusPanel status;

	public CurlingMatch() {
		homeTeam = new LinkedList<Player>();
		awayTeam = new LinkedList<Player>();
		score = new HashMap<Team, LinkedList<Integer>>();
		score.put(Team.HOME, new LinkedList<Integer>());
		score.put(Team.AWAY, new LinkedList<Integer>());
		score.get(Team.HOME).add(0);
		score.get(Team.AWAY).add(0);
		house = new House();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void GUISetup(){
		setSize(725, 700);
		setLayout(new GridLayout(2, 0));
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new GridLayout(0, 3));
		homePanel = new TeamPanel("Home");
		awayPanel = new TeamPanel("Away");
		homePanel.addPropertyChangeListener(new StoneIntentionListener(Team.HOME));
		awayPanel.addPropertyChangeListener(new StoneIntentionListener(Team.AWAY));
		status =  new StatusPanel();
		lowerPanel.add(homePanel);
		lowerPanel.add(status);
		lowerPanel.add(awayPanel);

		house.addPropertyChangeListener(new StonePlacedListener());
		add(house);
		add(lowerPanel);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		setVisible(true);
	}

	private JMenu createFileMenu(){
		JMenu menu = new JMenu("File");
		menu.add(createFileExitItem());
		return menu;
	}

	private JMenuItem createFileExitItem(){
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}

	public class StoneIntentionListener implements PropertyChangeListener {
		private Team team;

		public StoneIntentionListener(Team team) {
			this.team = team;
		}

		public void propertyChange(PropertyChangeEvent event) {
			if (event.getPropertyName().equals("StoneIntention")) {
				if (currentPlayer.getTeam() == team) {
					intention = (Purpose) event.getNewValue();
				}
			}
		}
	}

	public class StonePlacedListener implements PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent event) {
			if (event.getPropertyName().equals("StonePlaced")) {
				advanceTurn();
				intention = null;
			}
		}
	}

	public void formTeams() {
		// Form home team.
		homeTeam.add(new Player(Team.HOME, Role.LEAD));
		homeTeam.add(new Player(Team.HOME, Role.SKIP));
		homeTeam.add(new Player(Team.HOME, Role.SECOND));
		homeTeam.add(new Player(Team.HOME, Role.THIRD));

		// Form away team.
		awayTeam.add(new Player(Team.AWAY, Role.LEAD));
		awayTeam.add(new Player(Team.AWAY, Role.SKIP));
		awayTeam.add(new Player(Team.AWAY, Role.SECOND));
		awayTeam.add(new Player(Team.AWAY, Role.THIRD));
	}

	public void advanceTurn() {
		if (turn % 2 == 0) {
			currentPlayer = homeTeam.get(turn / 4);
		} else {
			currentPlayer = awayTeam.get(turn / 4);
		}

		// Score the previous turn
		HashMap<Team, Integer> houseScore = house.calcScore();
		for (Team key : score.keySet()) {
			if (houseScore.keySet().contains(key)) {
				score.get(key).add(house.calcScore().get(key));
			} else {
				score.get(key).add(0);
			}
		}

		// Advance turn
		++turn;
		status.team.setText(currentPlayer.getTeam().toString());
		status.player.setText(currentPlayer.getRole().toString());
	}

	public HashMap<Team, LinkedList<Integer>> getScore() {
		return score;
	}


	// These getters/setters are for use by unit tests only.
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public LinkedList<Player> getHomeTeam() {
		return homeTeam;
	}

	public LinkedList<Player> getAwayTeam() {
		return awayTeam;
	}

	public House getHouse(){
		return house;
	}

	public static void main(String[] args) {
		CurlingMatch game = new CurlingMatch();
		game.GUISetup();
		game.formTeams();
		game.advanceTurn();
	}
}
