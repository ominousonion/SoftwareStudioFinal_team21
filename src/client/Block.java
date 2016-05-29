package client;


import processing.core.PApplet;
import processing.core.PImage;

public class Block extends MapComponent{
	
	Block(int x, int y,int num, PApplet applet) {
		super(x, y,num, applet);
		this.passable=false;
		this.occupiedStage=0;
		
	}
	
	public void display(){
		this.applet.image(img,x,y,width,width);
		this.applet.noStroke();
	}

}
