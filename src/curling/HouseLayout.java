package curling;

import java.awt.Graphics;

import javax.swing.JPanel;


public class HouseLayout{//All parameters are in 1/20 feet, sorry if that's confusing, ex blueRadius = 60 half feet = 6 feet
	public static int blueRadius = 120;
	public static int whiteRadius = 80;
	public static int redRadius = 40;
	public static int insideRadius = 10;
	public static int rinkLength = 660;
	public static int rinkWidth = 300;
	public static int circleCenterX = 240;
	public static int halfRinkWidth = 150;
	
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
