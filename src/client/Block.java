package client;


import processing.core.PApplet;

public class Block extends MapComponent{

	private int d=2; 
	
	Block(int x, int y, PApplet applet) {
		super(x, y, applet);
	}
	
	public void display(){
		this.applet.noStroke();
		this.applet.fill(200, 100, 100);
		this.applet.rect(x+d, y+d, width-2*d, width-2*d);
	}

}
