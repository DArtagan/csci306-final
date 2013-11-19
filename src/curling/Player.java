package curling;

import java.util.ArrayList;

public class Player {
	private int order;  // What is an order?
	private Team team;
	private Role role;
	ArrayList<Stone> stones;

	public Player(Team team, Role role) {
		super();
		this.team = team;
		this.role = role;

		// All players begin with 2 stones.
		stones = new ArrayList<Stone>();
		stones.add(new Stone(team));
		stones.add(new Stone(team));
	}

	public Stone sendStone() throws RuntimeException {
		Stone sentStone = null;
		try {
			sentStone = stones.get(0);
			stones.remove(0);
			stones.trimToSize();
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException("Player.sendStone() was called, but Player has no stones.");
		}
		return sentStone;
	}

	// These getters/setters are for use by unit tests only.
	public Role getRole() {
		return role;
	}

	public Team getTeam() {
		return team;
	}

	public ArrayList<Stone> getStones() {
		return stones;
	}
}
