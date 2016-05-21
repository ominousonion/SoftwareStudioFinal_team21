package client;

<<<<<<< HEAD
import processing.core.PApplet;

public class Block extends MapComponent{

	Block(int x, int y, PApplet applet) {
		super(x, y, applet);
	}
	
	public void display(){
		this.applet.noStroke();
		this.applet.fill(200, 100, 100);
		this.applet.rect(x, y, width, width);
	}

=======
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
>>>>>>> 2302129ff8510d15a93f56e97b698699cd881a60
}
