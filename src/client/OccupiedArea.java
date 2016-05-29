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
<<<<<<< HEAD
		/*this.applet.noStroke();
		this.applet.fill(200, 100, 100);
		this.applet.rect(x, y, width, width);*/
		this.applet.image(this.img, this.x, this.y);
=======
		this.applet.image(img,x,y);
		this.applet.noStroke();
		this.applet.fill(200, 100, 100);
>>>>>>> 17b8dd3980a7e32b3cbadf287225b05f77791d3b
	}

}