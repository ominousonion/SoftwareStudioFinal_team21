package client;

import java.util.ArrayList;

public class GameMap {
	private MainApplet applet;
	//attributions
	final public int x, y, width, height;
	//map components
	public ArrayList<MapComponent> components;
	//character
	public Character character;
	
	//constructor
	GameMap(MainApplet applet,int x,int y, int width, int height){
		this.applet=applet;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	
	public void display(){
		
	}
}
