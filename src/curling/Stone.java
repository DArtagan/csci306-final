package curling;

public class Stone {
	private int radius, angle;

	public Stone(int radius, int angle) {
		super();
		this.radius = radius;
		this.angle = angle;
	}

	public int compareTo(Stone other) {
		// return -1 if this<other, 0 if this==other,
		// or 1 if this>other.
		return 0;
	}
}
