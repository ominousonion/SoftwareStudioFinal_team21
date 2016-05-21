package client;

import java.util.ArrayList;
import java.util.Random;

public class GameMap {
	private MainApplet applet;
	//attributions
	final public int x, y, width, height, SquareX, SquareY, SquareWidth;
	//map components
	public ArrayList<MapComponent> components;
	//character
	public Character character;
	
	//constructor
	GameMap(MainApplet applet){
		this.applet=applet;
<<<<<<< HEAD
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		
		//create components
		components=new ArrayList<MapComponent>();
		Random rand=new Random();
		for(int i=0;i<(height/50);i++){
			for(int j=0;j<(width/50);j++){
				if(rand.nextInt(2)==1){
					MapComponent com=new Road(this.applet,j*50,i*50,50,50);		
					this.components.add(com);
				}
				else{
					MapComponent com=new Block(this.applet,j*50,i*50,50,50);
					this.components.add(com);
				}
			}

		}
		
	}
	
	public void display(){
		for(MapComponent com:this.components){
			//this.applet.image(com.img, com.x, com.y, com.width, com.height);
			com.display();
		}
=======
		this.x=300;
		this.y=0;
		this.width=900;
		this.height=670;
		this.SquareX=x+150;
		this.SquareY=35;
		this.SquareWidth=600;
		//MapComponent block = new Block(SquareX, SquareY, applet);
		//this.components.add(block);
	}
	
	public void display(){
		this.applet.noStroke();
		this.applet.fill(100, 100, 255);
		this.applet.rect(x, y, width, height);
		this.applet.stroke(0);
		this.applet.noFill();
		this.applet.rect(SquareX, SquareY, SquareWidth, SquareWidth);
		/*for(MapComponent mc: components){
			mc.display();
		}*/
>>>>>>> 8845caa578f0589e6923a3bd5416be0bb59f8720
	}
}
