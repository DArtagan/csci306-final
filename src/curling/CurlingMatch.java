package curling;

import java.util.HashMap;
import java.util.LinkedList;

public class CurlingMatch {
	private int turn;
	private Player currentPlayer;
	private boolean gameOver;
	private HashMap<Team, LinkedList<Integer>> score;
	private LinkedList<Player> homeTeam, awayTeam;
	private House house;

	public CurlingMatch() {
		homeTeam = new LinkedList<Player>();
		awayTeam = new LinkedList<Player>();
		score = new HashMap<Team, LinkedList<Integer>>();
		score.put(Team.HOME, new LinkedList<Integer>());
		score.put(Team.AWAY, new LinkedList<Integer>());
		score.get(Team.HOME).add(0);
		score.get(Team.AWAY).add(0);
		house = new House();
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public void formTeams() {

	}

	public void advanceTurn() {
		// Score the previous turn
		HashMap<Team, Integer> houseScore = house.calcScore();
		for (Team key : score.keySet()) {
			if (houseScore.keySet().contains(key)) {
				score.get(key).add(house.calcScore().get(key));
			} else {
				score.get(key).add(0);
			}
		}

		// Reset house
		house.reset();

		// Advance turn
		++turn;
	}

	public HashMap<Team, LinkedList<Integer>> getScore() {
		return score;
	}


	// These getters/setters are for use by unit tests only.
	public LinkedList<Player> getHomeTeam() {
		return homeTeam;
	}

	public LinkedList<Player> getAwayTeam() {
		return awayTeam;
	}

	public House getHouse(){
		return house;
	}

}
