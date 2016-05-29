package client;

import processing.core.PApplet;
import processing.core.PImage;

public class Floor extends MapComponent{

	
	Floor(int x, int y,int num, PApplet applet) {
		super(x, y,num, applet);
		this.passable=true;
		this.occupiedStage=0;
		
	}
	
	public void display(){
		this.applet.image(img,x,y,width,width);
		this.applet.noStroke();
<<<<<<< HEAD
		this.applet.fill(200, 100, 100);

=======
>>>>>>> ca38e8c0861e88e2dac6f93cf94da9d462b834e1
	}

}
