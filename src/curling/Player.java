package curling;

import java.util.HashSet;

public class Player {
	private int order;  // What is an order?
	private Team team;
	private Role role;
	HashSet<Stone> stones;

	public Player(Team team, Role role) {
		super();
		this.team = team;
		this.role = role;

		// All players begin with 2 stones.
		stones = new HashSet<Stone>();
		stones.add(new Stone(team));
		stones.add(new Stone(team));
	}

	public void sendStone() {

	}

	// These getters/setters are for use by unit tests only.
	public Role getRole() {
		return role;
	}

	public Team getTeam() {
		return team;
	}
}
