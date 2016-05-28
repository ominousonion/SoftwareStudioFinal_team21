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
	public PImage img;
	
	//constructor
	MapComponent(int x,int y,int num,PApplet applet){
		this.x=x;
		this.y=y;
		this.applet=applet;
		String filename =("img/map_"+num);
		img = loadImage(filename,"png");
	}

	public void display(){};

}
