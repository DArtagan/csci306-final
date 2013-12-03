package curling;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Stone implements Comparable<Stone> {
	private Team team;
	private Purpose purpose;
	private int radius;
	private double angle;

	public Stone(Team team) {
		// FIXME: Set default starting positions for stones.
		// What should these be?
		super();
		this.team = team;
		radius = 0;
		angle = 0;
	}

	/* Unsure if we need this... how are we putting stones on the rink?
	public Stone(Team team, Purpose purpose, int x, int y) {
		super();
		this.team = team;
		this.purpose = purpose;
		setPosition(x, y);
	}
	*/

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

	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}

	public void setPosition(Point point) {
		// Normalize (prevent stones draw offscreen due to rounding errors).
		point.x = Math.max(point.x, 0);
		point.y = Math.max(point.y, 0);
		// Translate to rink 0,0.
		point.x -= HouseLayout.circleCenterX;
		point.y -= HouseLayout.halfRinkWidth;
		// Convert from rectangular to polar.

		radius = (int) Math.sqrt(Math.pow(point.x,  2) + Math.pow(point.y,  2));
		angle = Math.acos((double)point.x/radius);
	}

	public void setPosition(int x, int y) {
		// Overloaded for convenience.
		setPosition(new Point(x, y));
	}

	public Point getPoint() {
		// Convert from polar to rectangular.
		Point point = new Point();
		point.x = (int) (radius*Math.cos(angle));
		point.y = (int) (radius*Math.sin(angle));
		// Translate to JFrame 0,0.
		point.x += HouseLayout.circleCenterX;
		point.y = -point.y + HouseLayout.halfRinkWidth;
		// Prevent stones being drawn offscreen due to rounding errors.
		point.x = Math.max(point.x, 0);
		point.y = Math.max(point.y, 0);

		return point;
	}

	public int getX() {
		return getPoint().x;
	}

	public int getY() {
		return getPoint().y;
	}

	public void draw(Graphics g) {
		// Define colors.
		Color colorHome = java.awt.Color.GREEN;
		Color colorAway = java.awt.Color.BLACK;
		// Define shape sizes.
		int stoneRadius = 8;
		int x = Math.max(getX() - stoneRadius, stoneRadius);
		int y = Math.max(getY() - stoneRadius, stoneRadius);
		int height = stoneRadius*2;
		int width = height;

		// Set the color.
		if (team == Team.HOME) {
			g.setColor(colorHome);
		} else {
			g.setColor(colorAway);
		}

		// Draw the shape.
		if (purpose == Purpose.DRAW) {
			g.drawOval(x, y, width, height);
		} else if (purpose == Purpose.TAKEOUT) {
			g.drawRect(x, y, width, height);
		} else if (purpose == Purpose.GUARD) {
			Polygon triangle = new Polygon();
			triangle.addPoint(x+stoneRadius, y);
			triangle.addPoint(x, y+height);
			triangle.addPoint(x+width, y+height);
			g.drawPolygon(triangle);
		} else {
			// for debug purposes only!
			g.drawString("You didn't set my purpose!", x, y);
		}
	}
}
