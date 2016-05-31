package client;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class GameMap{
	private MainApplet applet;
	//attributions
	final public int x, y, width, height, SquareX, SquareY, SquareWidth, SquareHeight, SquareUnit;
	//map components
	public ArrayList<MapComponent> components;
	private File mapFile;
	//character
	public Character character;
	public Character opponent;

	private int ch_X, ch_Y, op_X, op_Y;
	private GameClient gc;

	//constructor
	GameMap(MainApplet applet,int mapNumber,GameClient gc){
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
		
		this.mapFile=new File("./src/map/mapfile_1.map");

		try
		{
			Scanner sc=new Scanner(mapFile);
			int mapData;
			int i=0;
			
			while(sc.hasNext()&& i<225){
				mapData=sc.nextInt();
				components.add(new MapComponent(SquareX+(i%15)*SquareUnit,SquareY+(i/15)*SquareUnit,mapData,this.applet));
				i++;
			}
			sc.close();
		}catch (IOException e) {System.out.println(e);}

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
			character= new Character(ch_X, ch_Y, SquareUnit, SquareUnit, applet, this);
			opponent = new Character(op_X, op_Y, SquareUnit, SquareUnit, applet, this);
		}
		else{
			character= new Character(op_X, op_Y, SquareUnit, SquareUnit, applet, this);
			opponent = new Character(ch_X, ch_Y, SquareUnit, SquareUnit, applet, this);
		}
		character.skillCreateBlock.toMakeBlock();
		character.skillDeleteBlock.toDeleteBlock();
	}
}

