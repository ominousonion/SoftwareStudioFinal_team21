package client;

import java.util.ArrayList;
import java.util.Random;

public class GameMap {
	private MainApplet applet;
	//attributions
	final public int x, y, width, height, SquareX, SquareY, SquareWidth;
	//map components
	public ArrayList<MapComponent> components;
	//character
	public Character character;
	
	//constructor
	GameMap(MainApplet applet){
		this.applet=applet;
		this.x=300;
		this.y=0;
		this.width=900;
		this.height=670;
		this.SquareX=x+150;
		this.SquareY=30;
		this.SquareWidth=600;
		
	}

	
	public void display(){
		this.applet.noStroke();
		this.applet.fill(100, 100, 255);
		this.applet.rect(x, y, width, height);
		this.applet.stroke(0);
		this.applet.noFill();
		this.applet.rect(SquareX, SquareY, SquareWidth, SquareWidth);
		/*for(MapComponent mc: components){
			mc.display();
		}*/
	}
}
