package curling;

public class Stone {
	private int radius, angle;
	public static enum Compare {CLOSER, EQUAL, FARTHER};

	public Stone(int radius, int angle) {
		super();
		this.radius = radius;
		this.angle = angle;
	}

	public Compare compareTo(Stone other) {
		return null;
	}
}
