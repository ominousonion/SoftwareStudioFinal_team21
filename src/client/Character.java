package client;

import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PImage;

public class Character {
	//attributions

	final public int iniX, iniY, width, height;
	private MainApplet parent;
	private PImage chImg;
	private String chImgAddress, itemImgAddress;
	private String group;
	public int oneStep;
	private int preX, preY;
	public int x, y;
	public String name;
	public int number;
	
	public int money;
	
	public boolean out_of_place_up, out_of_place_down, out_of_place_left, out_of_place_right ;
	public boolean move[]=new boolean[4];
	public String face;
	public int index=0;
	public CreateBlock skillCreateBlock;
	public DeleteBlock skillDeleteBlock;
	public OccupipeBlock skillOccupipeBlock;
	private GameMap gm;
	public int ocpy;


	//constructor
	Character(int x,int y, int width, int height, MainApplet parent, GameMap gm, int seq,int index){

		this.iniX = x;
		this.iniY = y;
		this.x = iniX;
		this.y = iniY;
		this.width = width;
		this.height = height;
		this.parent = parent;
		this.oneStep = 5;  //move speed
		for(int i=0;i<4;i++) move[i]=false;
		this.index = index;
		this.number = seq;
		this.chImgAddress="./src/img/character"+seq+"_";
		this.out_of_place_up=false;
		this.out_of_place_down=false;
		this.out_of_place_left=false;
		this.out_of_place_right=false;
		
		this.money=500;
		this.gm=gm;
		this.face="down";
		loadData();
		this.skillCreateBlock=new CreateBlock(gm,this);
		this.skillDeleteBlock=new DeleteBlock(gm,this);
		this.skillOccupipeBlock=new OccupipeBlock(gm,this);
		this.ocpy=0;
	}

	public void setName(String name){
		this.name=name;
	}
	
	
	public void reset(){
		this.x = iniX;
		this.y = iniY;
		for(int i=0;i<4;i++) move[i]=false;

		this.index=(this.y-this.parent.map.SquareY)/this.parent.map.SquareUnit*15+(this.x-this.parent.map.SquareX)/this.parent.map.SquareUnit;
		this.out_of_place_up=false;
		this.out_of_place_down=false;
		this.out_of_place_left=false;
		this.out_of_place_right=false;
		this.money=500;
		this.ocpy=0;
		this.face="down";
	}
	
	public void display(){
		// Displays the image at point (0, height/2) at half of its size
		//parent.image(chImg, 0, height/2, chImg.width/2, chImg.height/2);
		this.loadData();
		this.parent.image(chImg,x,y,width,width);
		this.parent.noStroke();
	}

	public void loadData(){
		chImg = parent.loadImage(chImgAddress+this.face+".png");
		chImg.resize(width,height);
	}
	
	
	
	public void move(String dir){   //undo:check for boundary
		MapComponent com=this.parent.map.components.get(this.index);
		MapComponent com_next;
		int row_pre;
		int row_after;

		if(dir.equals("up")){
			preY = y;
			this.face="up";
			this.y = this.y - oneStep;
			this.out_of_place_up=true;
			this.out_of_place_down=true;
			this.out_of_place_left=false;
			this.out_of_place_right=false;			
			if(this.index-15 >= 0){
				com_next=this.parent.map.components.get(this.index-15);
				this.x=com_next.x;
				if(this.y == com_next.y){
					this.index-=15;
					this.out_of_place_up=false;
					this.out_of_place_down=false;		
					this.out_of_place_left=false;
					this.out_of_place_right=false;
				}
			}
		}
		else if(dir.equals("down")){
			this.face="down";
			preY = y;
			this.y = this.y + oneStep;
			this.out_of_place_up=true;
			this.out_of_place_down=true;
			this.out_of_place_left=false;
			this.out_of_place_right=false;			
			if(this.index+15 < 225){
				com_next=this.parent.map.components.get(this.index+15);
				this.x=com_next.x;
				if(this.y == com_next.y){
					this.index+=15;
					this.out_of_place_up=false;
					this.out_of_place_down=false;		
					this.out_of_place_left=false;
					this.out_of_place_right=false;
				}				
			}

		}
		else if(dir.equals("left")){
			this.face="left";
			preX = x;
			this.x = this.x - oneStep;					
			this.out_of_place_up=false;
			this.out_of_place_down=false;
			this.out_of_place_left=true;
			this.out_of_place_right=true;
			if(this.index-1 >= 0){
				row_pre=this.index/15;
				row_after=(this.index-1)/15;
				com_next=this.parent.map.components.get(this.index-1);
				if(row_after==row_pre){
					this.y=com_next.y;
				}
				if(this.x == com_next.x){
					this.index-=1;
					this.out_of_place_up=false;
					this.out_of_place_down=false;		
					this.out_of_place_left=false;
					this.out_of_place_right=false;
				}							
			}

		}
		else if(dir.equals("right")){
			this.face="right";
			preX = x;
			this.x = this.x + oneStep;					
			this.out_of_place_left=true;
			this.out_of_place_right=true;					
			this.out_of_place_up=false;
			this.out_of_place_down=false;
			if(this.index+1 < 225){
				row_pre=this.index/15;
				row_after=(this.index+1)/15;
				com_next=this.parent.map.components.get(this.index+1);
				if(row_after==row_pre){
					this.y=com_next.y;
				}
				if(this.x == com_next.x){
					this.index+=1;
					this.out_of_place_up=false;
					this.out_of_place_down=false;		
					this.out_of_place_left=false;
					this.out_of_place_right=false;
				}						
			}

		}
	}
	
	public void plusMoney()
	{
		this.money+=((this.ocpy+1)*2);//*ocpi
	}

	public void setGroup(String group){
		this.group = group;
	}

	public String getGroup(){
		return group;
	}
}
