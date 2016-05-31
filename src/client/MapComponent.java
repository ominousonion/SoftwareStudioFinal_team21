package client;

import processing.core.PApplet;
import processing.core.PImage;





public class MapComponent extends PApplet {
	//attributions
	final public int x, y;
	public boolean passable;
	public int occupiedStage;
	public int width=40;
	public PApplet applet;
	public PImage img;
	public int type;
	
	//constructor
	MapComponent(int x,int y,int num,PApplet applet){
		this.x=x;
		this.y=y;
		this.applet=applet;
		this.type=num;
		
		String filename =("./src/img/map_"+num+".png");
		img = loadImage(filename);
		
		if(num==0){
			this.passable=true;
			this.occupiedStage=0;
		}else if(num<=3){
			this.passable=true;
			this.occupiedStage=1;
		}else{
			this.passable=false;
			this.occupiedStage=0;
		}

	}

	public void display(){
		this.applet.image(img,x,y,width,width);
		this.applet.noStroke();
	};
	
	
	
	public void occupipe(int ch){
		if(this.type>=1 && this.type<=3)
		{
			this.occupiedStage=2;
			if(ch==0){
				this.type=2;
				String filename =("./src/img/map2.png");
				img = loadImage(filename);
			}
			else if(ch==1){
				this.type=3;
				String filename=("./src/img/map3.png");
				img = loadImage(filename);
			}
			
		}
	}
	
	public void createWall(){
		if(this.type==0)
		{
			this.type=99;
			this.passable=false;
			this.occupiedStage=0;
			String filename =("./src/img/map_18.png");
			img = loadImage(filename);
		}
	}
	public void delWall(){
		if(this.type>=4)
		{
			this.type=0;
			this.passable=true;
			this.occupiedStage=0;
			String filename =("./src/img/map_0.png");
			img = loadImage(filename);
		}
	}

}
