package client;

public class Character {
	//attributions
	final public int x, y, width, height;
	private MainApplet applet;
	private int d=1;
	//constructor
	Character(MainApplet applet,int x,int y, int width, int height){
		this.applet=applet;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	
	public void display(){
		this.applet.noStroke();
		this.applet.fill(255, 0, 0);
		this.applet.rect(x+d, y+d, width-d*2, height-d*2);
	}
}
