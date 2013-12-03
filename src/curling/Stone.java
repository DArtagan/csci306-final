package curling;

import java.awt.Graphics;

public class Stone implements Comparable<Stone> {
	private Team team;
	private int radius, angle;
	private final static int stoneSizeRadius = 5;
	private final static int circleCenterX = HouseLayout.circleCenterX;
	private final static int halfRinkWidth = HouseLayout.halfRinkWidth;

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
	
	public void draw(Graphics g){
		int x = (circleCenterX-stoneSizeRadius/2) + (int) (radius * Math.cos(Math.toRadians(angle)));
		int y = (halfRinkWidth-stoneSizeRadius/2) + (int) (radius * Math.sin(Math.toRadians(angle)));
		if(team.equals("Home")){
			g.setColor(java.awt.Color.ORANGE);
			g.fillOval(x, y, stoneSizeRadius, stoneSizeRadius);
		} else {
			g.setColor(java.awt.Color.GREEN);
			g.fillOval(x, y, stoneSizeRadius, stoneSizeRadius);
		}
	}
}
