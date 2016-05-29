package client;

import processing.core.PApplet;

public abstract class MapComponent {
	//attributions
	final public int x, y;
	public boolean passable;
	public int width=40;
	public PApplet applet;
	
	//constructor
	MapComponent(int x,int y,PApplet applet){
		this.x=x;
		this.y=y;
		this.applet=applet;
	}
	

	public void display(){};

}
