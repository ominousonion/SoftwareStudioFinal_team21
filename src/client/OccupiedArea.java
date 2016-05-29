package client;

import processing.core.PApplet;
import processing.core.PImage;

public class OccupiedArea extends MapComponent{

	OccupiedArea(int x, int y,int num,PApplet applet) {
		super(x, y, num,applet);
		this.passable=true;
		this.occupiedStage=1;
	}
	public void changeStage()
	{
		this.occupiedStage++;
	}
	
	public void display(){
		this.applet.image(img,x,y,width,width);
		this.applet.noStroke();
	}

}