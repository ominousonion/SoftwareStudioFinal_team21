package client;

import processing.core.PApplet;

public class CharacterState {
	
	int x,y,width,height;
	private PApplet applet;
	
	CharacterState(PApplet applet){
		this.x=0;
		this.y=0;
		this.width=300;
		this.height=670;
		this.applet=applet;
	}
	
	public void display(){
		this.applet.noStroke();
		this.applet.fill(100, 255, 100);
		this.applet.rect(x, y, width, height);
	}
}

