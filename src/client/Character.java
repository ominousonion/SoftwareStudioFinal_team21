package client;

public class Character {
	//attributions
	final public int x, y, width, height;
	private MainApplet applet;
	//constructor
	Character(MainApplet applet,int x,int y, int width, int height){
		this.applet=applet;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	
	public void display(){
		
	}
}
