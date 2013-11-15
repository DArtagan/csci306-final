package curling;

import java.util.HashMap;
import java.util.LinkedList;

public class CurlingMatch {
	private int turn;
	private Player currentPlayer;
	private boolean gameOver;
	private HashMap<Team, LinkedList<Integer>> score;
	private House house;
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	private LinkedList<Player> homeTeam, awayTeam;
	

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}


	public CurlingMatch() {
		homeTeam = new LinkedList<Player>();
		awayTeam = new LinkedList<Player>();
		score.put(Team.HOME, new LinkedList<Integer>());
		score.put(Team.AWAY, new LinkedList<Integer>());
		house = new House();
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

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	

	public House getHouse(){
		return house;
	}

}
