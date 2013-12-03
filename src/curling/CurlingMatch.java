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
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class CurlingMatch extends JFrame {
	private int turn;
	protected static Player currentPlayer;
	private HashMap<Team, Integer> score;
	private LinkedList<Player> homeTeam, awayTeam;
	private House house;
	private boolean homeStart;
	protected static Purpose intention;
	TeamPanel homePanel, awayPanel;
	StatusPanel status;

	public CurlingMatch() {
		homeTeam = new LinkedList<Player>();
		awayTeam = new LinkedList<Player>();
		score = new HashMap<Team, Integer>();
		score.put(Team.HOME, null);
		score.put(Team.AWAY, null);
		score.put(Team.HOME, 0);
		score.put(Team.AWAY, 0);
		house = new House();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void GUISetup(){
		setSize(725, 650);
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
		status.undoButton.addActionListener(new UndoButtonListener());

		house.addPropertyChangeListener(new StonePlacedListener());
		add(house);
		add(lowerPanel);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		setVisible(true);

		String message = "Hello User, click yes for heads and no for tails.";//splash screen
		int reply = JOptionPane.showConfirmDialog(this, message, "Welcome to Curling Simulator", JOptionPane.YES_NO_OPTION);
		int coinFlip = (int) (Math.random() * 2);//coin toss if user wins team one starts else team two starts, 0 is heads, 1 is tails
		if(reply == JOptionPane.YES_OPTION){
			if(coinFlip == 0){
				JOptionPane.showMessageDialog(null, "You won the flip, Home team starts");//team one starts
				homeStart = true;
			} else {
				JOptionPane.showMessageDialog(null, "You lost the flip, Away team starts");//team two starts
				homeStart = false;
			}
		} else {
			if(coinFlip == 0){
				JOptionPane.showMessageDialog(null, "You lost the flip, Away team starts");//team two starts
				homeStart = false;
			} else {
				JOptionPane.showMessageDialog(null, "You won the flip, Home team starts");//team one starts
				homeStart = true;
			}
		}
	}

	class UndoButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			house.removeLastStone();
			if(turn > 1) {
				if (turn % 2 == 0) {
					currentPlayer = homeTeam.get(((turn-2) % 16) / 4);
				} else {
					currentPlayer = awayTeam.get(((turn-2) % 16) / 4);
				}
				currentPlayer.addStone(currentPlayer.getTeam());
				--turn;
			}
			HashMap<Team, Integer> houseScore = house.calcScore();
			for (Team key : score.keySet()) {
				if (houseScore.keySet().contains(key)) {
					score.put(key, house.calcScore().get(key));
				} else {
					score.put(key, 0);
				}
			}
			homePanel.setScore(score.get(Team.HOME));
			awayPanel.setScore(score.get(Team.AWAY));
			status.team.setText(currentPlayer.getTeam().toString());
			status.player.setText(currentPlayer.getRole().toString());
			repaint();
			System.out.println(turn);
		}
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
		homeTeam.add(new Player(Team.HOME, Role.SECOND));
		homeTeam.add(new Player(Team.HOME, Role.THIRD));
		homeTeam.add(new Player(Team.HOME, Role.SKIP));

		// Form away team.
		awayTeam.add(new Player(Team.AWAY, Role.LEAD));
		awayTeam.add(new Player(Team.AWAY, Role.SECOND));
		awayTeam.add(new Player(Team.AWAY, Role.THIRD));
		awayTeam.add(new Player(Team.AWAY, Role.SKIP));
	}

	public void advanceTurn() {
		if (turn % 16 == 0) {
			house.reset();
		}

		if (turn % 2 == 0 && homeStart) {
			currentPlayer = homeTeam.get((turn % 16) / 4);
		} else if (turn % 2 == 1 && homeStart){
			currentPlayer = awayTeam.get((turn % 16) / 4);
		} else if (turn % 2 == 0 && !homeStart){
			currentPlayer = awayTeam.get((turn % 16) / 4);
		} else if (turn % 2 == 1 && !homeStart){
			currentPlayer = homeTeam.get((turn % 16) / 4);
		}

		// Score the previous turn
		System.out.println(house.calcScore());

		HashMap<Team, Integer> houseScore = house.calcScore();
		for (Team key : score.keySet()) {
			if (houseScore.keySet().contains(key)) {
				score.put(key, house.calcScore().get(key));
			} else {
				score.put(key, 0);
			}
		}

		// Advance turn
		++turn;
		homePanel.setScore(score.get(Team.HOME));
		awayPanel.setScore(score.get(Team.AWAY));
		status.team.setText(currentPlayer.getTeam().toString());
		status.player.setText(currentPlayer.getRole().toString());
	}

	public HashMap<Team, Integer> getScore() {
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
