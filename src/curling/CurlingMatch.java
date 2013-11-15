package curling;

import java.util.LinkedList;

public class CurlingMatch {
	private int turn;
	private LinkedList<Player> homeTeam, awayTeam;
	private LinkedList<Integer> score;
	private House house;

	public CurlingMatch() {
		homeTeam = new LinkedList<Player>();
		awayTeam = new LinkedList<Player>();
		house = new House();
	}

	public void formTeams() {

	}

	public void advanceTurn() {

	}


	// These getters/setters are for use by unit tests only.
	public LinkedList<Player> getHomeTeam() {
		return homeTeam;
	}

	public LinkedList<Player> getAwayTeam() {
		return awayTeam;
	}

	public House getHouse() {
		return house;
	}
}
