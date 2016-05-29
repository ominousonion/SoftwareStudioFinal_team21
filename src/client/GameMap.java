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
	public Character opponent;

	private int ch_X, ch_Y, op_X, op_Y;
	private GameClient gc;

	//constructor
	GameMap(MainApplet applet, GameClient gc){
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
		this.gc = gc;
		op_X = SquareX+SquareWidth-SquareUnit;
		op_Y = SquareY;
		ch_X = SquareX;
		ch_Y = SquareY+SquareUnit*14;
		
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

		iniCharacter(gc.seq);

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
		this.opponent.display();

	}
	
	public void iniCharacter(int seq){
		System.out.println("seq: "+seq);
		if(seq==1){
			character= new Character(ch_X, ch_Y, SquareUnit, SquareUnit, applet);
			opponent = new Character(op_X, op_Y, SquareUnit, SquareUnit, applet);
		}
		else{
			character= new Character(op_X, op_Y, SquareUnit, SquareUnit, applet);
			opponent = new Character(ch_X, ch_Y, SquareUnit, SquareUnit, applet);
		}
	}
}

