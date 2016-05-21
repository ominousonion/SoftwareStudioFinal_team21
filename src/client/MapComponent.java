package client;

import processing.core.PImage;

public abstract class MapComponent {
	//attributions
	protected MainApplet applet;
	final public int x, y, width, height;
	public PImage img;
	//constructor
	MapComponent(MainApplet applet,int x,int y, int width, int height){
		this.applet=applet;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	
	abstract public void display();
}
