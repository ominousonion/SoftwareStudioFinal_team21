package client;

import processing.core.PApplet;
import processing.core.PImage;

public class OccupiedArea extends MapComponent{
	public PImage img;
	OccupiedArea(int x, int y,int num,PApplet applet) {
		super(x, y, num,applet);
		this.passable=true;
		this.occupiedStage=1;
		String filename =("./src/client/img/map_"+num);
		img = loadImage(filename+".png");
	}
	public void changeStage()
	{
		this.occupiedStage++;
	}
	
	public void display(){
		/*this.applet.noStroke();
		this.applet.fill(200, 100, 100);
		this.applet.rect(x, y, width, width);*/
		this.applet.image(this.img, this.x, this.y);
	}

}