package client;

import processing.core.PApplet;

public class CharacterState {
	
	int x,y,width,height;
	private MainApplet applet;
	private int Max_occupies = 5;
	
	CharacterState(MainApplet applet){
		this.x=0;
		this.y=0;
		this.width=300;
		this.height=670;
		this.applet=applet;
	}
	
	public void display(){
		this.applet.noStroke();
		
		this.applet.fill(105, 105, 105);
		this.applet.rect(x, y, width, height);
		this.applet.fill( 0, 0, 0);
		this.applet.text("Player1", 0, 20);
		this.applet.fill(255, 255, 255);
		this.applet.rect(10, this.height/2-50, width-20, 20);
		this.applet.fill(131 ,139 ,131);
		this.applet.rect(10, this.height/2-50, (width-20)/**applet.map.character.ocpy/Max_occupies*/, 20);
		
		this.applet.fill(100,149,237);
		this.applet.rect(x, height/2, width, height/2);
		this.applet.fill( 0, 0, 0);
		this.applet.text("Player2", 0, height/2+20);
		
		
	}
}