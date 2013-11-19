package curling;

public class Stone implements Comparable<Stone> {
	private Team team;
	private int radius, angle;

	public Stone(Team team) {
		// FIXME: Set default starting positions for stones.
		// What should these be?
		super();
		this.team = team;
		radius = 0;
		angle = 0;
	}

	public Stone(Team team, int radius, int angle) {
		super();
		this.team = team;
		this.radius = radius;
		this.angle = angle;
	}

	public int compareTo(Stone other) {
		return radius - other.radius;
	}

	public String toString() {
		return "Team: " + team + " Radius: " + radius + " Angle: " + angle;
	}

	public Team getTeam() {
		return team;
	}
}
