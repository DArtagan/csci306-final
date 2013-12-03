package curling;

import java.awt.Graphics;


public class HouseLayout{//All parameters are in 1/10 feet, sorry if that's confusing, ex blueRadius = 120 1/10th feet =  feet
	public final static int blueRadius = 120;
	public final static int whiteRadius = 80;
	public final static int redRadius = 40;
	public final static int insideRadius = 10;
	public final static int rinkLength = 725;
	public final static int rinkWidth = 300;
	public final static int circleCenterX = 240;
	public final static int halfRinkWidth = 150;

	public HouseLayout(){}

	public void draw(Graphics g){
		g.setColor(java.awt.Color.WHITE);
		g.fillRect(0, 0, rinkLength, rinkWidth);//draw the ice

		g.setColor(java.awt.Color.BLUE);
		g.fillOval(circleCenterX-blueRadius/2, halfRinkWidth-blueRadius/2, blueRadius, blueRadius);

		g.setColor(java.awt.Color.WHITE);
		g.fillOval(circleCenterX-whiteRadius/2, halfRinkWidth-whiteRadius/2, whiteRadius, whiteRadius);

		g.setColor(java.awt.Color.RED);
		g.fillOval(circleCenterX-redRadius/2, halfRinkWidth-redRadius/2, redRadius, redRadius);

		g.setColor(java.awt.Color.BLACK);
		g.drawLine(0, halfRinkWidth, rinkLength, halfRinkWidth);
		g.drawLine(circleCenterX, 0, circleCenterX, rinkWidth);

		g.setColor(java.awt.Color.WHITE);
		g.fillOval(circleCenterX-insideRadius/2, halfRinkWidth-insideRadius/2, insideRadius, insideRadius);
	}
}
