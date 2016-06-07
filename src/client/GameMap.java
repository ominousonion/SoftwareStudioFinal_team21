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
	public int seq;
	
	public eventPicture ep;
	private Random ran;

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
		this.ran=new Random();
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
		
		if(eventIndex>4){
			if(this.character.type>this.opponent.type){
				if(eventIndex==8){
					eventIndex=this.character.type*2+4;
				}else if(eventIndex==7){
					eventIndex=this.character.type*2+3;
				}else if(eventIndex==6){
					eventIndex=this.opponent.type*2+4;
				}else if(eventIndex==5){
					eventIndex=this.opponent.type*2+3;
				}
			}else{
				if(eventIndex==8){
					eventIndex=this.opponent.type*2+4;
				}else if(eventIndex==7){
					eventIndex=this.opponent.type*2+3;
				}else if(eventIndex==6){
					eventIndex=this.character.type*2+4;
				}else if(eventIndex==5){
					eventIndex=this.character.type*2+3;
				}
			}
		}
		
		String filename =("./src/img/map_1.png");
		
		if(eventIndex==0){ // �_���j�}�a
			for (int index=0;index<225;index++) {
				if(this.components.get(index).type>4){
					this.components.get(index).delWall(index);
				}else if(this.components.get(index).type==3){
					this.components.get(index).type=1;
					this.components.get(index).img = this.components.get(index).loadImage(filename);
					if(this.character.number==2) this.character.ocpy--;
					else if(this.opponent.number==2) this.opponent.ocpy--;
					
				}else if(this.components.get(index).type==2){
					this.components.get(index).type=1;
					this.components.get(index).img = this.components.get(index).loadImage(filename);	
					if(this.character.number==1) this.character.ocpy--;
					else if(this.opponent.number==1) this.opponent.ocpy--;
				}
			}
		}else if(eventIndex==1){ //�ѥ��Y�L
			this.character.money=0;
			this.opponent.money=0;
		}else if(eventIndex==2){ //�g�٤j����
			if(this.character.money>200) this.character.money-=200;
			else this.character.money=0;
			if(this.opponent.money>200) this.opponent.money-=200;
			else this.opponent.money=0;
		}else if(eventIndex==3){ //�F���ҭ��|1
			if(this.character.money>this.opponent.money){
				if(this.character.money>200) this.character.money-=200;
				else this.character.money=0;
				this.opponent.money+=200;
			}else if(this.character.money<this.opponent.money){
				if(this.opponent.money>200) this.opponent.money-=200;
				else this.opponent.money=0;
				this.character.money+=200;
			}
		}else if(eventIndex==4){ //�F���ҭ��|2(���)
			int tmp;
			tmp=this.character.money;
			this.character.money=this.opponent.money;
			this.opponent.money=tmp;
		}/*else if(eventIndex==5){ // �j�ǥͨC�P�Q���ѥ[���ո��] //character.type=1
			plus300(1);
		}else if(eventIndex==6){ // �U�j�ǳW�w�������c�W��			
			reduceTwoStore(1);
		}else if(eventIndex==7){ //��s�o�{�o�������q���鰷�d //character.type=2
			addTwoStore(2);
		}else if(eventIndex==8){ //�x�W�j�ǥͥ���BMI�W�L40�A�P�q�j��-300
			minus300(2);
		}else if(eventIndex==9){ // �^���s�o�{������Ĳ������q���鰷�d�B�������g //character.type=3
			addTwoStore(3);
		}else if(eventIndex==10){ //�x�W�j�ǥͤӲV�A�F���U�O�j�ǥ͸T�Τ���A���z����P�����
			minus300(3);
		}else if(eventIndex==11){ //�u�W�C���M�h�ڶ��ƴ߰����X���� //character.type=4
			plus300(4);
		}else if(eventIndex==12){ //�x�W�~�ǥ����~�֭���20���A���Ʃ��Q�j������
			reduceTwoStore(4);
		}else if(eventIndex==13){ //�j�ǥͥ����ίv�ɼƭ���3�p�ɡA�@�ػݨD�q�j�W�A��+300 //character.type=5
			plus300(5);
		}else if(eventIndex==14){ //�@�ظg�L��s�o�{���P������A�@�ة��˳�
			reduceTwoStore(5);
		}*/
	}

	private void addTwoStore(int type){
		int find=2;
		
		if(this.character.type==type){
			for (int index=ran.nextInt(225);index<225;index++) {
				if(index==225) index=0;
				if(this.character.type==1){
					this.components.get(index).type=this.character.number+1;
					this.components.get(index).img = this.components.get(index).loadImage("./src/img/map_"+this.components.get(index).type+".png");	
					this.character.ocpy++;
					find--;
				}		
				if(find==0 || this.character.ocpy==14) break;
			}
		}else if(this.opponent.type==type){
			for (int index=ran.nextInt(225);index<225;index++) {
				if(index==225) index=0;
				if(this.opponent.type==1){
					this.components.get(index).type=this.opponent.number+1;
					this.components.get(index).img = this.components.get(index).loadImage("./src/img/map_"+this.components.get(index).type+".png");	
					this.opponent.ocpy++;
					find--;
				}
				if(find==0 || this.opponent.ocpy==14) break;
			}
		}
	}
	
	private void reduceTwoStore(int type){
		int find=2;
		String filename =("./src/img/map_1.png");
		
		if(this.character.type==type){
			for (int index=ran.nextInt(225);index<225;index++) {
				if(index==225) index=0;
				if(this.character.number+1==this.components.get(index).type){
					this.components.get(index).type=1;
					this.components.get(index).img = this.components.get(index).loadImage(filename);	
					this.character.ocpy--;
					find--;
				}		
				if(find==0 || this.character.ocpy==0) break;
			}
		}else if(this.opponent.type==type){
			for (int index=ran.nextInt(225);index<225;index++) {
				if(index==225) index=0;
				if(this.opponent.number+1==this.components.get(index).type){
					this.components.get(index).type=1;
					this.components.get(index).img = this.components.get(index).loadImage(filename);	
					this.opponent.ocpy--;
					find--;
				}
				if(find==0 || this.opponent.ocpy==0) break;
			}
		}
	}
	
	private void plus300(int type){
		if(this.character.type==type){
			this.character.money+=300;
		}else if(this.opponent.type==type){
			this.opponent.money+=300;
		}
	}
	
	private void minus300(int type){
		if(this.character.type==type){
			if(this.character.money>300) this.character.money-=300;
			else this.character.money=0;
		}else if(this.opponent.type==type){
			if(this.opponent.money>300) this.opponent.money-=300;
			else this.opponent.money=0;
		}
	}
	
	
}

