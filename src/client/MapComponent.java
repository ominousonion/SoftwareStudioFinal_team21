package client;

<<<<<<< HEAD
import processing.core.PImage;

public abstract class MapComponent {
	//attributions
	protected MainApplet applet;
	final public int x, y, width, height;
	public PImage img;
	//constructor
	MapComponent(MainApplet applet,int x,int y, int width, int height){
		this.applet=applet;
=======
import processing.core.PApplet;

public abstract class MapComponent {
	//attributions
	final public int x, y;
	public int width=40;
	public PApplet applet;
	
	//constructor
	MapComponent(int x,int y,PApplet applet){
>>>>>>> 8845caa578f0589e6923a3bd5416be0bb59f8720
		this.x=x;
		this.y=y;
		this.applet=applet;
	}
	
<<<<<<< HEAD
	abstract public void display();
=======
	public void display(){};
>>>>>>> 8845caa578f0589e6923a3bd5416be0bb59f8720
}
