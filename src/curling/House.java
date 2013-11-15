package curling;

import java.util.HashMap;
import java.util.HashSet;

public class House {
	private HashSet<Stone> stones;

	public House() {
		// The house starts with 0 stones, and stones are added as players
		// send them in.
		stones = new HashSet<Stone>();
	}

	public HashMap<Team, Integer> calcScore() {
		return null;
	}

	public void addStone(Stone stone) {

	}

	public Object getScore() {
		return null;
	}

	// These getters/setters are for use by unit tests only.
	public HashSet<Stone> getStones() {
		return stones;
	}
}
