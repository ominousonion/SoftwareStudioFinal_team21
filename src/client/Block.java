package client;

import processing.core.PImage;

public class Block extends MapComponent{
	Block(MainApplet applet,int x,int y, int width, int height){
		super(applet,x,y,width,height);
		this.img= this.applet.loadImage("block.png");
	}
	public void display(){
		this.applet.image(this.img, this.x, this.y, this.width, this.height);
		/*this.applet.fill(255,255,0);
		this.applet.rect(x, y, 5, 5);*/
		
	}
}
