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
	private GameMap map;
	
	//constructor
	MapComponent(int x,int y,int num,PApplet applet, GameMap map){
		this.x=x;
		this.y=y;
		this.applet=applet;
		this.type=num;
		this.map=map;
		
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
			if(ch==1){
				this.type=2;
				String filename =("./src/img/map_2.png");
				img = loadImage(filename);
			}
			else if(ch==2){
				this.type=3;
				String filename=("./src/img/map_3.png");
				img = loadImage(filename);
			}
			
		}
	}
	
	public boolean createWall(int index){
		if(this.type==0)
		{
			int id=90000;
			
			if(index-15<0){
				id+=1000;
			}else if(map.components.get(index-15).type<=4){ //up block
				id+=1000;
			}
			
			if(index+15>=225){ //down block
				id+=100;
			}else if(map.components.get(index+15).type<=4){
				id+=100;
			}
			
			if(index%15==0){ //left block
				id+=10;
			}else if(map.components.get(index-1).type<=4){
				id+=10;
			}else if(index%15!=0 && (map.components.get(index-1).type%1000)>=100 && id%1000<100){ //left block has no block beneath it
				id+=20;
			}
			
			
			if(index%15==14){ //right block
				id+=1;
			}else if(map.components.get(index+1).type<=4){ //right block
				id+=1;
			}else if(index%15!=14 && (map.components.get(index+1).type%1000)>=100 && id%1000<100){ //right block has no block beneath it
				id+=2;
			}	

			this.type=id;
			this.passable=false;
			this.occupiedStage=0;
			String filename =("./src/img/map_"+id+".png");
			img = loadImage(filename);
			
			if(index-15>=0){
				if(map.components.get(index-15).type>89999){ //up
					map.components.get(index-15).type-=100;
				
					if(index-15-1%15!=14 && index!=15){ //up left
						if(map.components.get(index-15-1).type%1000>=100 || index-15==0){
							map.components.get(index-15).type+=20;
						}else if(map.components.get(index-15-1).type%10==2){
							map.components.get(index-15-1).type-=2;
							filename =("./src/img/map_"+map.components.get(index-15-1).type+".png");
							map.components.get(index-15-1).img = loadImage(filename);
						}
					}
				
					if(index-15+1%15!=0){ //up right
						if(map.components.get(index-15+1).type%1000>=100){
							map.components.get(index-15).type+=2;
						}else if(map.components.get(index-15+1).type%100>=20){
							map.components.get(index-15+1).type-=20;
							filename =("./src/img/map_"+map.components.get(index-15+1).type+".png");
							map.components.get(index-15+1).img = loadImage(filename);
						}
					}
				
					filename =("./src/img/map_"+map.components.get(index-15).type+".png");
					map.components.get(index-15).img = loadImage(filename);
				}
			}
			
			if(index+15<225){
				if(map.components.get(index+15).type>89999){ //down
					map.components.get(index+15).type-=1000;
					filename =("./src/img/map_"+map.components.get(index+15).type+".png");
					map.components.get(index+15).img = loadImage(filename);
				}
			}
			
			if(index-1%15!=14 && index-1!=-1){
				if(map.components.get(index-1).type>89999){ //left
					if(this.type%1000>=100 && map.components.get(index-1).type%1000<100){ //has down boundary
						map.components.get(index-1).type+=1;
					}else{
						map.components.get(index-1).type-=1;
					}
					filename =("./src/img/map_"+map.components.get(index-1).type+".png");
					map.components.get(index-1).img = loadImage(filename);
				}
			}
			
			if(index+1%15!=0 && index!=224){
				if(map.components.get(index+1).type>89999){ //right
					if(this.type%1000>=100 && map.components.get(index+1).type%1000<100){
						map.components.get(index+1).type+=10;
					}else{
						map.components.get(index+1).type-=10;
					}
					filename =("./src/img/map_"+map.components.get(index+1).type+".png");
					map.components.get(index+1).img = loadImage(filename);
				}
			}
			return true;
		}else{
			return false;
		}
	}
	
	
	
	public boolean delWall(int index){
		if(this.type>=4)
		{
			String filename;
			
			if(index-15>=0){
				if(map.components.get(index-15).type>89999){ //up
					map.components.get(index-15).type+=100;
					if(map.components.get(index-15).type%100>=20) map.components.get(index-15).type-=20;
					if(map.components.get(index-15).type%10>=2 ) map.components.get(index-15).type-=2;
				
					if(index-15-1%15!=14 && index!=15){ //up left
						if(map.components.get(index-15-1).type>=4 && (map.components.get(index-15-1).type%1000<100 || index-15==0)){
							map.components.get(index-15-1).type+=2;
							filename =("./src/img/map_"+map.components.get(index-15-1).type+".png");
							map.components.get(index-15-1).img = loadImage(filename);
						}
					}
				
					if(index-15+1%15!=0){ //up right
						if(map.components.get(index-15+1).type>=4 && map.components.get(index-15+1).type%1000<100){ //up right doesn't have down boundary
							map.components.get(index-15+1).type+=20;
							filename =("./src/img/map_"+map.components.get(index-15+1).type+".png");
							map.components.get(index-15+1).img = loadImage(filename);
						}
					}
				
					filename =("./src/img/map_"+map.components.get(index-15).type+".png");
					map.components.get(index-15).img = loadImage(filename);
				}
			}
			
			if(index+15<225){
				if(map.components.get(index+15).type>89999){ //down
					map.components.get(index+15).type+=1000;
					filename =("./src/img/map_"+map.components.get(index+15).type+".png");
					map.components.get(index+15).img = loadImage(filename);
				}
			}
			
			if(index-1%15!=14 && index-1!=-1){
				if(map.components.get(index-1).type>89999){ //left
					if(this.type%1000>=100 && map.components.get(index-1).type%1000<100){ //this has down boundary but left doesn't
						map.components.get(index-1).type-=1;
					}else{
						map.components.get(index-1).type+=1;
					}
					filename =("./src/img/map_"+map.components.get(index-1).type+".png");
					map.components.get(index-1).img = loadImage(filename);
				}
			}
			
			if(index+1%15!=0 && index!=224){
				if(map.components.get(index+1).type>89999){ //right
					if(this.type%1000>=100 && map.components.get(index+1).type%1000<100){
						map.components.get(index+1).type-=10;
					}else{
						map.components.get(index+1).type+=10;
					}
					filename =("./src/img/map_"+map.components.get(index+1).type+".png");
					map.components.get(index+1).img = loadImage(filename);
				}
			}
			
			
			
			this.type=0;
			this.passable=true;
			this.occupiedStage=0;
			filename =("./src/img/map_0.png");
			img = loadImage(filename);
			return true;
		}else{
			return false;
		}
	}

}
