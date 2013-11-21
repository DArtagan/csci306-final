package curling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class House {
	private ArrayList<Stone> stones;

	public House() {
		// The house starts with 0 stones, and stones are added as players
		// send them in.
		stones = new ArrayList<Stone>();
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
		Team team = stones.get(0).getTeam();
		Integer score = 0;
		for (Stone stone : stones) {
			if (stone.getTeam() != team) {
				break;
			} else {
				++score;
			}
		}
<<<<<<< HEAD
		if(score >= 0){
			result.put(Team.HOME, score);
		} else {
			score = score * -1;
			result.put(Team.AWAY, score);
		}
		result.put(Team.HOME, 0);
		result.put(Team.AWAY, 0);

		if (stones.size() > 0) {
			Collections.sort(stones);
			Stone baseStone = stones.get(0);

			Team currentTeam = null;
			for (Stone stone : stones) {
				currentTeam = stone.getTeam();
				if (baseStone.compareTo(stone) == 0 && baseStone.getTeam() != currentTeam) {
					result.put(baseStone.getTeam(), 0);
					break;
				} else if (currentTeam != baseStone.getTeam()) {
					break;
				} else {
					result.put(currentTeam, result.get(currentTeam) + 1);
				}
			}
		}
=======
		result.put(team, score);
>>>>>>> parent of 2551e6a... Earlier unsynced changes
		return result;
	}

	// These getters/setters are for use by unit tests only.
	public ArrayList<Stone> getStones() {
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
