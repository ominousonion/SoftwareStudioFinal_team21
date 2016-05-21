package client;

import processing.core.PImage;

public class Road extends MapComponent{	
	
	Road(MainApplet applet,int x,int y, int width, int height){
		super(applet,x,y,width,height);
		this.img= this.applet.loadImage("road.png");
	}
	
	public void display(){
		this.applet.image(this.img, this.x, this.y, this.width, this.height);
		/*this.applet.fill(0,0,255);
		this.applet.rect(x, y, 5, 5);*/
		
	}
	
}
