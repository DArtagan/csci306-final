package curling;

import java.util.HashMap;
import java.util.LinkedList;

public class CurlingMatch {
	private int turn;
	private LinkedList<Player> homeTeam, awayTeam;
	private HashMap<Team, LinkedList<Integer>> score;

	public CurlingMatch() {
		homeTeam = new LinkedList<Player>();
		awayTeam = new LinkedList<Player>();
		score.put(Team.HOME, new LinkedList<Integer>());
		score.put(Team.AWAY, new LinkedList<Integer>());
	}

	public void formTeams() {

	}

	public void advanceTurn() {

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
}
