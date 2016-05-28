package client;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class GameMap {
	private MainApplet applet;
	//attributions
	final public int x, y, width, height, SquareX, SquareY, SquareWidth, SquareHeight, SquareUnit;
	//map components
	public ArrayList<MapComponent> components;
	//character
	public Character character;
	private File mapFile;
	
	//constructor
	GameMap(int mapNumber,MainApplet applet){
		
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
		
		
		
		
		
		
		this.mapFile=new File("map/mapfile_"+mapNumber+".txt");
		try
		{
			Scanner sc=new Scanner(mapFile);
			int mapData;
			int i=0;
			
			while(sc.hasNext()&& i<225){
				mapData=sc.nextInt();
				if(mapData==0){
					components.add(new Floor(SquareX+(i%15)*SquareUnit,SquareY+(i/15)*SquareUnit,mapData,this.applet));
				}else if(mapData<=3){
					components.add(new OccupiedArea(SquareX+(i%15)*SquareUnit,SquareY+(i/15)*SquareUnit,mapData,this.applet));
				}else{
					components.add(new Floor(SquareX+(i%15)*SquareUnit,SquareY+(i/15)*SquareUnit,mapData,this.applet));
				}
				i++;
			}
			sc.close();
		}catch (IOException e) {System.out.println(e);}
		
		
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
