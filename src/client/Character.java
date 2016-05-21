package client;

import processing.core.PApplet;
import processing.core.PImage;

public class Character {
	//attributions
	final public int iniX, iniY, width, height;
	private MainApplet parent;
	private PImage chImg, itemImg;
	private String chImgAddress, itemImgAddress;
	private String group;
	private int oneStep;
	private int preX, preY, x, y;
	
	//constructor
	Character(int x,int y, int width, int height, MainApplet parent){
		this.iniX = x;
		this.iniY= y;
		this.x = iniX;
		this.y = iniY;
		this.width = width;
		this.height = height;
		this.parent = parent;
	}
	
	public void display(){
		// Displays the image at point (0, height/2) at half of its size
		parent.image(chImg, 0, height/2, chImg.width/2, chImg.height/2);
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
