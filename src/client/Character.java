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

	public boolean move[]=new boolean[4];
	public String face;
	public int index=0;
	public CreateBlock skillCreateBlock;
	public DeleteBlock skillDeleteBlock;
	private GameMap gm;


	//constructor
	Character(int x,int y, int width, int height, MainApplet parent, GameMap gm){
		this.iniX = x;
		this.iniY= y;
		this.x = iniX;
		this.y = iniY;
		this.width = width;
		this.height = height;
		this.parent = parent;

		this.oneStep = 40;  //move speed
		this.gm=gm;
		for(int i=0;i<4;i++) move[i]=false;
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
		if(dir.equals("up")){
			preY = y;
			this.y = this.y - oneStep;
		}
		else if(dir.equals("down")){
			preY = y;
			this.y = this.y + oneStep;
		}
		else if(dir.equals("left")){
			preX = x;
			this.x = this.x - oneStep;
		}
		else if(dir.equals("right")){
			preX = x;
			this.x = this.x + oneStep;
		}
	}

	public void setGroup(String group){
		this.group = group;
	}

	public String getGroup(){
		return group;
	}
}
