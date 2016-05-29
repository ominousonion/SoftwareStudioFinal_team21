package client;

import processing.core.PApplet;

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
		this.applet.noStroke();
		this.applet.fill(200, 100, 100);
		this.applet.rect(x, y, width, width);
	}

}