package client;

import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PImage;

public class Character {
	//attributions

	final public int iniX, iniY, width, height;
	private MainApplet parent;
	private PImage chImg, itemImg;
	private String chImgAddress, itemImgAddress;
	private String group;
	public int oneStep;
	private int preX, preY;
	public int x, y;
	public String name;
	
	public int ocpy;
	public int money;
	

	public boolean move[]=new boolean[4];
	public String face;
	public int index=0;
	public CreateBlock skillCreateBlock;
	public DeleteBlock skillDeleteBlock;
	private GameMap gm;


	//constructor
	Character(int x,int y, int width, int height, MainApplet parent, GameMap gm, int index){

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

		this.money=0;
		this.gm=gm;
		this.face="down";
		this.skillCreateBlock=new CreateBlock(gm);
		this.skillDeleteBlock=new DeleteBlock(gm);
	}

	public void setName(String name){
		this.name=name;
	}
	
	
	
	
	public void display(){
		// Displays the image at point (0, height/2) at half of its size
		//parent.image(chImg, 0, height/2, chImg.width/2, chImg.height/2);
		this.parent.noStroke();
		this.parent.fill(255,255,0);
		this.parent.ellipse(this.x+20, this.y+20, 40, 40);
		// animation : Ani.to

	}

	public void loadData(){
		chImg = parent.loadImage(chImgAddress);
		chImg.resize(width,height);
		/*
		itemImg = parent.loadImage(itemImgAddress);
		itemImg.resize(20,20);
		*/
	}

	public void move(String dir){   //undo:check for boundary
		MapComponent com=this.parent.map.components.get(this.index);
		MapComponent com_next;
		int row_pre;
		int row_after;
		if(dir.equals("up")){
			preY = y;
			this.y = this.y - oneStep;
			if(this.index-15 >= 0){
				com_next=this.parent.map.components.get(this.index-15);
				this.x=com_next.x;
				if(this.y == com_next.y){
					this.index-=15;
				}				
			}
		}
		else if(dir.equals("down")){
			preY = y;
			this.y = this.y + oneStep;
			if(this.index+15 < 225){
				com_next=this.parent.map.components.get(this.index+15);
				this.x=com_next.x;
				if(this.y == com_next.y){
					this.index+=15;
				}				
			}

		}
		else if(dir.equals("left")){
			preX = x;
			this.x = this.x - oneStep;
			if(this.index-1 >= 0){
				row_pre=this.index/15;
				row_after=(this.index-1)/15;
				com_next=this.parent.map.components.get(this.index-1);
				if(row_after==row_pre){
					this.y=com_next.y;
				}
				if(this.x == com_next.x){
					this.index-=1;
				}				
			}

		}
		else if(dir.equals("right")){
			preX = x;
			this.x = this.x + oneStep;
			if(this.index+1 < 225){
				row_pre=this.index/15;
				row_after=(this.index+1)/15;
				com_next=this.parent.map.components.get(this.index+1);
				if(row_after==row_pre){
					this.y=com_next.y;
				}
				if(this.x == com_next.x){
					this.index+=1;
				}				
			}

		}
	}
	
	public void plusMoney()
	{
		this.money+=1;//*ocpi
	}

	public void setGroup(String group){
		this.group = group;
	}

	public String getGroup(){
		return group;
	}
}
