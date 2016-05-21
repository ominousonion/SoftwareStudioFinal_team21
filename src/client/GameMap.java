package client;

import java.util.ArrayList;
import java.util.Random;

public class GameMap {
	private MainApplet applet;
	//attributions
	final public int x, y, width, height;
	//map components
	public ArrayList<MapComponent> components;
	//character
	public Character character;
	
	//constructor
	GameMap(MainApplet applet,int x,int y, int width, int height){
		this.applet=applet;
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
	}
}
