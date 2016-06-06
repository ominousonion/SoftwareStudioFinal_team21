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
	public storeLabel sl;
	//character
	public Character character;
	public Character opponent;
	
	public int ch_num;
	private int ch_X, ch_Y, op_X, op_Y;
	private int ch_index, op_index;
	private GameClient gc;
	public int seq;
	
	public eventPicture ep;

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
		this.SquareUnit=this.SquareWidth/15;//SquareUnit = 40
		this.gc = gc;
		this.ch_num=gc.seq;
		
		op_X = SquareX+SquareWidth-SquareUnit;
		op_Y = SquareY;
		ch_X = SquareX;
		ch_Y = SquareY+SquareUnit*14;
		ch_index = (ch_Y-SquareY)/SquareUnit*15+(ch_X-SquareX)/SquareUnit;
		op_index = (op_Y-SquareY)/SquareUnit*15+(op_X-SquareX)/SquareUnit;
		components = new ArrayList<MapComponent>();
		
		this.mapFile=new File("./src/map/mapfile_1.map");
		this.sl=new storeLabel(applet,this);

		try
		{
			Scanner sc=new Scanner(mapFile);
			int mapData;
			int i=0;
			
			while(sc.hasNext()&& i<225){
				mapData=sc.nextInt();
				components.add(new MapComponent(SquareX+(i%15)*SquareUnit,SquareY+(i/15)*SquareUnit,mapData,this.applet, this));
				i++;
			}
			sc.close();
		}catch (IOException e) {
			System.out.println(e);
		}

		iniCharacter(ch_num);
		this.ep=new eventPicture(300,162,this.applet);
	}

	
	public void reset(){
		components.clear();
		
		try
		{
			Scanner sc=new Scanner(mapFile);
			int mapData;
			int i=0;
			
			while(sc.hasNext()&& i<225){
				mapData=sc.nextInt();
				components.add(new MapComponent(SquareX+(i%15)*SquareUnit,SquareY+(i/15)*SquareUnit,mapData,this.applet, this));
				i++;
			}
			sc.close();
		}
		catch (IOException e) {
			System.out.println(e);
		}
			
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
		this.sl.display();
		if(ep.show){
			this.ep.display();
		}
	}
	
	public void iniCharacter(int seq){
		this.seq=seq;
		System.out.println("seq: "+seq);
		if(seq==1){
			character= new Character(ch_X, ch_Y, SquareUnit, SquareUnit, applet, this,1, ch_index);
			opponent = new Character(op_X, op_Y, SquareUnit, SquareUnit, applet, this,2, op_index);
		}
		else{
			character= new Character(op_X, op_Y, SquareUnit, SquareUnit, applet, this,2, op_index);
			opponent = new Character(ch_X, ch_Y, SquareUnit, SquareUnit, applet, this,1, ch_index);
		}
	}
	
	public void Event(int eventIndex){
		
		this.ep.LoadPicture(eventIndex);
		this.ep.show=true;
		
		if(eventIndex==0){ // 北韓大破壞
			for (int index=0;index<225;index++) {
				if(this.components.get(index).type>4){
					this.components.get(index).delWall(index);
				}
			}
		}else if(eventIndex==1){ //股市崩盤
			this.character.money=0;
			this.opponent.money=0;
		}else if(eventIndex==2){ //經濟大蕭條
			if(this.character.money>200) this.character.money-=200;
			else this.character.money=0;
			if(this.opponent.money>200) this.opponent.money-=200;
			else this.opponent.money=0;
		}else if(eventIndex==3){ //政府課重稅1
			if(this.character.money>this.opponent.money){
				if(this.character.money>200) this.character.money-=200;
				else this.character.money=0;
				this.opponent.money+=200;
			}else if(this.character.money<this.opponent.money){
				if(this.opponent.money>200) this.opponent.money-=200;
				else this.opponent.money=0;
				this.character.money+=200;
			}
		}else if(eventIndex==4){ //政府課重稅2(對調)
			int tmp;
			tmp=this.character.money;
			this.character.money=this.opponent.money;
			this.opponent.money=tmp;
		}else if(eventIndex==5){ //character.type=1
			
		}else if(eventIndex==6){
			
		}else if(eventIndex==7){ //character.type=2
			
		}else if(eventIndex==8){
			
		}else if(eventIndex==9){ //character.type=3
			
		}else if(eventIndex==10){
			
		}else if(eventIndex==11){ //character.type=4
			
		}else if(eventIndex==12){
			
		}else if(eventIndex==13){ //character.type=5
			
		}else if(eventIndex==14){
			
		}
	}
}

