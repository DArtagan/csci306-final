package curling;

import java.util.LinkedList;

public class CurlingMatch {
	private int turn;
	private Player currentPlayer;
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	private LinkedList<Player> homeTeam, awayTeam;
	private LinkedList<Integer> score;
	private boolean gameOver;
	

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public CurlingMatch() {
		homeTeam = new LinkedList<Player>();
		awayTeam = new LinkedList<Player>();
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

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	
}
