package client;


import processing.core.PApplet;

public class Block extends MapComponent{
	
	Block(int x, int y,int num, PApplet applet) {
		super(x, y,num, applet);
		this.passable=false;
		this.occupiedStage=0;
		
	}
	
	public void display(){
		this.applet.noStroke();
		this.applet.fill(200, 100, 100);
		this.applet.rect(x, y, width, width);
	}

}
