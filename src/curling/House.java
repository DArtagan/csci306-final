package curling;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JPanel;

public class House extends JPanel{
	private ArrayList<Stone> stones;
	private HouseLayout layout;

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
		Collections.sort(stones);
		Team team = Team.HOME;
		Integer score = 0;
		for (Stone stone : stones) {
			if (stone.getTeam() == team) {
				++score;
			} else {
				--score;
			}
		}
		if(score >= 0){
			result.put(Team.HOME, score);
		} else {
			score = score * -1;
			result.put(Team.AWAY, score);
		}
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
	
	public void paintComponent(Graphics g){
		HouseLayout layout = new HouseLayout();
		layout.draw(g);
	}
}
