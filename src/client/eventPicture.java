package client;

import processing.core.PApplet;
import processing.core.PImage;

public class eventPicture  extends PApplet {

	final public int x, y;
	public PImage img;
	private PApplet applet;
	public boolean show;
	
	eventPicture(int x,int y,PApplet applet){
		this.x=x;
		this.y=y;
		this.show=false;
		this.applet=applet;
	}
	
	public void LoadPicture(int index){
		String filename =("./src/img/event"+index+".jpg");
		img = loadImage(filename);
	}
	
	public void display(){
		this.applet.image(img,x,y,600,325);
		this.applet.noStroke();
		
	}
}
