package client;

import processing.core.PApplet;
import processing.core.PImage;


public abstract class MapComponent extends PApplet {
	//attributions
	final public int x, y;
	public boolean passable;
	public int occupiedStage;
	public int width=40;
	public PApplet applet;

	
	//constructor
	MapComponent(int x,int y,int num,PApplet applet){
		this.x=x;
		this.y=y;
		this.applet=applet;
	}

	public void display(){};

}
