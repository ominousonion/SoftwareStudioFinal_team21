package client;

import java.util.ArrayList;
import java.util.Random;


public class GameMap {
	private MainApplet applet;
	//attributions
	final public int x, y, width, height, SquareX, SquareY, SquareWidth, SquareHeight, SquareUnit;
	//map components
	public ArrayList<MapComponent> components;
	//character
	public Character character;
	
	//constructor
	GameMap(MainApplet applet){
		this.applet=applet;
		this.x=300;
		this.y=0;
		this.width=900;
		this.height=670;
		this.SquareX=x+150;
		this.SquareY=30;
		this.SquareWidth=600;
		this.SquareHeight=600;
		this.SquareUnit=this.SquareWidth/15;
		
		components = new ArrayList<MapComponent>();
		Random r = new Random();
		int ran;
		
		for(int i=0;i<225;i++){
			ran = r.nextInt(3);
			if(i==13||i==14||i==29||i==195||i==210||i==211){
				components.add(new Floor(SquareX+(i%15)*SquareUnit,SquareY+(i/15)*SquareUnit,this.applet));
			}else if(ran==0){
				components.add(new Block(SquareX+(i%15)*SquareUnit,SquareY+(i/15)*SquareUnit,this.applet));
			}else{
				components.add(new Floor(SquareX+(i%15)*SquareUnit,SquareY+(i/15)*SquareUnit,this.applet));
			}
			
		}
		
		character=new Character(SquareX, SquareY+SquareUnit*14, SquareUnit, SquareUnit, applet);
		
	}

	
	public void display(){
		this.applet.noStroke();
		this.applet.fill(100, 100, 255);
		this.applet.rect(x, y, width, height);
		this.applet.stroke(0);
		this.applet.noFill();
		this.applet.rect(SquareX, SquareY, SquareWidth, SquareWidth);
		for(MapComponent mc: components){
			mc.display();
		}
		this.character.display();
	}
}
