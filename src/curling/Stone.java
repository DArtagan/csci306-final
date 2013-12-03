package curling;

<<<<<<< HEAD
import java.awt.Graphics;

public class Stone implements Comparable<Stone> {
	private Team team;
	private int radius, angle;
	private final static int stoneSizeRadius = 5;
	private final static int circleCenterX = HouseLayout.circleCenterX;
	private final static int halfRinkWidth = HouseLayout.halfRinkWidth;
=======
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Stone implements Comparable<Stone> {
	private Team team;
	private Purpose purpose;
	private int radius;
	private double angle;
>>>>>>> 6e52185f843f3de9bc219b4bb6374f3f4679eeb8

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
<<<<<<< HEAD
	
	public void draw(Graphics g){
		int x = (circleCenterX-stoneSizeRadius/2) + (int) (radius * Math.cos(Math.toRadians(angle)));
		int y = (halfRinkWidth-stoneSizeRadius/2) + (int) (radius * Math.sin(Math.toRadians(angle)));
		if(team.equals("Home")){
			g.setColor(java.awt.Color.ORANGE);
			g.fillOval(x, y, stoneSizeRadius, stoneSizeRadius);
		} else {
			g.setColor(java.awt.Color.GREEN);
			g.fillOval(x, y, stoneSizeRadius, stoneSizeRadius);
=======

	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}

	public void setPosition(Point point) {
		// Prevent stones being drawn offscreen due to rounding errors.
		point.x = Math.max(point.x, 0);
		point.y = Math.max(point.y, 0);
		// Translate to rink 0,0.
		point.x -= HouseLayout.circleCenterX;
		point.y = HouseLayout.halfRinkWidth - point.y;
		// Convert from rectangular to polar.
		radius = (int) Math.sqrt(Math.pow(point.x,  2) + Math.pow(point.y,  2));
		// This must be atan2, in order to get the correct signed value.
		angle = Math.atan2(point.y, point.x);
	}

	public void setPosition(int x, int y) {
		// Overloaded for convenience.
		setPosition(new Point(x, y));
	}

	public Point getPoint() {
		// Convert from polar to rectangular and translate to JFrame 0,0.
		int x = HouseLayout.circleCenterX + (int) (radius * Math.cos(angle));
		int y = HouseLayout.halfRinkWidth - (int) (radius * Math.sin(angle));

		// Prevent stones being drawn offscreen due to rounding errors.
		Point point = new Point();
		point.x = Math.max(x, 0);
		point.x = Math.min(point.x, HouseLayout.rinkLength);
		point.y = Math.max(y, 0);
		point.y = Math.min(point.y, HouseLayout.rinkWidth);

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
		Color colorHome = java.awt.Color.ORANGE;
		Color colorAway = java.awt.Color.GREEN;
		// Define shape sizes.
		int stoneRadius = 5;
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
>>>>>>> 6e52185f843f3de9bc219b4bb6374f3f4679eeb8
		}
	}
}
