package curling;

import java.util.HashMap;
import java.util.TreeSet;

public class House {
	private TreeSet<Stone> stones;

	public House() {
		// The house starts with 0 stones, and stones are added as players
		// send them in.
		stones = new TreeSet<Stone>();
	}

	public void addStone(Stone stone) {
		stones.add(stone);
	}

	public HashMap<Team, Integer> calcScore() {
		HashMap<Team, Integer> result = new HashMap<Team, Integer>();
		if (stones.size() == 0) {
			result.put(null, 0);
			return result;
		}
		Team team = stones.first().getTeam();
		Integer score = 0;
		for (Stone stone : stones) {
			if (stone.getTeam() != team) {
				break;
			} else {
				++score;
			}
		}

		result.put(team, score);
		return result;
	}

	// These getters/setters are for use by unit tests only.
	public TreeSet<Stone> getStones() {
		return stones;
	}

	public String toString() {
		String output = "";
		for (Stone stone : stones) {
			output += stone + ", ";
		}
		return output;
	}

	public void reset() {
		stones.clear();
	}
}
