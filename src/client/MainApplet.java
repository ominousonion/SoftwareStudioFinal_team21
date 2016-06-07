package client;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import de.looksgood.ani.Ani;

import processing.core.PApplet;
import processing.core.PImage;
import gifAnimation.*;

@SuppressWarnings("serial")

public class MainApplet extends PApplet{
	public GameMap map;
	public ShowResult viewResult;

	private final static int width = 1200, height = 650;
	private CharacterState state;
	private GameClient gc;
	private boolean isStart;//paul
	private boolean isExplain;
	private boolean isSelected;
	private boolean isView;
	public  boolean isBegin;
	public boolean isEnding;
	public boolean victory;
	private Button btn;//Paul added
	private BackButton backbtn;
	private PictureSelButton picSelButton;
	public PicData picdata1;
	private PImage [][]itemImg = new PImage[100][100];
	private PImage []backImg = new PImage[10];
	private PImage MainImage = new PImage();
	private PImage explainImg;
	private PImage winImg;
	private PImage loseImg;
	private int sel_number;
	private int sel_number_1;
	private Gif myAnimation;
	private Gif myAnimation2;
	private Gif myAnimation3;
	private Gif myAnimation4;
	private Gif myAnimation5;
	private Gif myAnimation6;
	private int frogX;
	private MinimUsage minim;

	private Random r = new Random();
	private int ran;
	
	private int moneyValue=0;
	
	public MainApplet(GameClient gc){
		this.gc = gc;
	}
	
	public void setup(){
		size(width,height);
		map=new GameMap(this,r.nextInt(1)+1, gc);
		state=new CharacterState(this);
		isStart = true;
		isExplain = false;
		isBegin = false;
		isView=false;
		isEnding=false;
		victory=false;
		btn = new Button(this);
		backbtn = new BackButton(this);
		picSelButton = new PictureSelButton(this);
		picdata1 = new PicData(this);
		sel_number = 0;
		for( String str : picdata1.getGroupData().get("Sneaker")){
			itemImg[0][sel_number] = picdata1.getImageData().get(str);
			sel_number++;
		}
		sel_number = 0;
		for( String str : picdata1.getGroupData().get("Drink")){
			itemImg[1][sel_number] = picdata1.getImageData().get(str);
			sel_number++;
		}
		sel_number = 0;
		for( String str : picdata1.getGroupData().get("Cellphone")){
			itemImg[2][sel_number] = picdata1.getImageData().get(str);
			sel_number++;
		}
		sel_number = 0;
		for( String str : picdata1.getGroupData().get("Caffee")){
			itemImg[3][sel_number] = picdata1.getImageData().get(str);
			sel_number++;
		}
		sel_number = 0;
		for(String str : picdata1.getGroupData().get("Fastfood")){
			itemImg[4][sel_number] = picdata1.getImageData().get(str);
			sel_number++;
		}
		sel_number = 0;
		sel_number_1 = 0;
		
		
		
		viewResult=new ShowResult(this);
		explainImg=loadImage("/src/img/explaintion_1.png");
		
		this.MainImage = loadImage("/src/background/main.jpg");
		this.MainImage.resize(width, height);
		this.backImg[0] = loadImage("/src/background/img1.jpg");
		this.backImg[0].resize(1200, 720);
		this.backImg[1] = loadImage("/src/background/img2.jpg");
		this.backImg[1].resize(1200, 720);
		this.backImg[2] = loadImage("/src/background/img3.jpg");
		this.backImg[2].resize(1200, 720);
		this.backImg[3] = loadImage("/src/background/img4.jpg");
		this.backImg[3].resize(width, height);
		this.backImg[4] = loadImage("/src/background/img5.jpg");
		this.backImg[4].resize(width, height);
		this.backImg[5] = loadImage("/src/background/img6.jpg");
		this.backImg[5].resize(width, height);
		this.winImg = loadImage("/src/img/Game_WIN.png");
		this.loseImg = loadImage("/src/img/Game_lose.png");
		myAnimation = new Gif(this, "/src/animation/bird.GIF"); 
		myAnimation.play();
		myAnimation2 = new Gif(this, "/src/animation/frog.gif"); 
		myAnimation2.play();
		frogX = 300;
		myAnimation3 = new Gif(this, "/src/animation/flower.gif"); 
		myAnimation3.play();
		myAnimation4 = new Gif(this, "/src/animation/signature_3.gif"); 
		myAnimation4.play();
		myAnimation5 = new Gif(this, "/src/animation/arrow1.gif"); 
		myAnimation5.play();
		myAnimation6 = new Gif(this, "/src/animation/arrow2.gif"); 
		myAnimation6.play();
		
		minim = new MinimUsage(this);
		minim.playSong();
		
	}
	
	public void draw(){
		if( isStart == true && isExplain == false && isSelected == false && isView == false){
			//background(0);
			image(this.MainImage,0,0);
			image(myAnimation,900,100);
			image(myAnimation2,frogX,500);
			image(myAnimation3,200,550);
			image(myAnimation4,500,50);
			picSelButton.hideButton();
			backbtn.hideButton();
			btn.showButton();
			btn.display();
			if(frogX>=1)
				frogX--;
			else
				frogX=1200;
		}
		else if( isExplain == true && isSelected == false && isView == false){
			image(explainImg,0,0);
			btn.hideButton();
			backbtn.showButton();
			backbtn.display();
		}
		else if( isSelected == true ){
			image(this.backImg[sel_number_1],0,0);
			btn.hideButton();
			picSelButton.showButton();
			picSelButton.display();
			image(itemImg[sel_number_1][sel_number],450,150);
			image(myAnimation5,290,250);
			image(myAnimation6,780,250);
			//image(this.backImg[0],0,0);
		}
		else if( isView == true ){
			
			btn.hideButton();
			viewResult.display();
			backbtn.showButton();
			backbtn.display();
			//image(this.backImg[0],0,0);
		}
		else if(isBegin == false){
			btn.hideButton();
			backbtn.hideButton();
			map.display();
			state.display();
		}
		else{
			if(isEnding){
				btn.hideButton();
				backbtn.hideButton();
				//map.display();
				state.display();	
				if(victory){
					image(this.winImg, 360, 100, 500, 500);
				}
				else{
					image(this.loseImg, 360, 100, 500, 500);
				}
			}			
			else{
				btn.hideButton();
				backbtn.hideButton();
				map.display();
				state.display();
				//image(this.backImg[5],100,0);
				checkMove();	
			}
		}
		
	}
	
	public void reset(){
		isStart = true;
		isExplain = false;
		isBegin = false;
		isView = false;
		isEnding = false;
		victory = false;
		sel_number = 0;
		sel_number_1 = 0;
		this.map.reset();
		this.map.character.reset();
		this.map.opponent.reset();

	}
	
	/*control the behavior of buttonA*/
	public void buttonA(){
		isStart = false;//go to the game scene
		isSelected = true;
	}
	
	/*control the behavior of buttonB*/
	public void buttonB(){ 
		isExplain = true;
	}
	
	/*control the behavior of buttonC*/
	public void buttonC(){ 
		isView = true;
		viewResult.Setini();
		picdata1.readData();
	}
	
	
	public void buttonD(){
		System.exit(0);//leave the game
	}
	
	/*control the behavior of backButton*/
	public void backBtn(){
		isExplain = false;
		isView=false;
	}
	
	public void buttonSel1(){
		picSelButton.hideButton();
		String g_name ;
		if(sel_number_1==0) g_name ="Sneaker";
		else if(sel_number_1==1) g_name ="Drink";
		else if(sel_number_1==2) g_name ="Cellphone";
		else if(sel_number_1==3) g_name ="Caffee";
		else g_name = "Fastfood";
		String selected = "selected";
		ArrayList<String> g = (ArrayList<String>) picdata1.getGroupData().get(g_name);
		String selname = (String) g.toArray()[sel_number];
		gc.sendMessage(selected.concat(" ").concat(selname));
		isSelected = false;
	}
	/*public void buttonSel2(){
		picSelButton.hideButton();
		isSelected = false;
	}*/
	
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP :
			if(isStart == false && isExplain == false && isSelected == false ){
				if(!isEnding){
					this.map.character.move[0]=true;
					this.map.character.face="up";
					gc.sendMessage("turn_up");					
				}
			}
			else{
				if(sel_number_1-1>=0){
					sel_number_1--;
					sel_number = 0;
				}
			}			
		break;
		case KeyEvent.VK_DOWN :
			if(isStart == false && isExplain == false && isSelected == false){
				if(!isEnding){
					this.map.character.move[1]=true;
					this.map.character.face="down";
					gc.sendMessage("turn_down");					
				}
			}
			else{
				if(sel_number_1+1<=4){
					sel_number_1++;
					sel_number = 0;
				}
			}
		break;	
		case KeyEvent.VK_LEFT :
			if(isView==true){
				viewResult.changeGroup(false);
			}
			else{
				if( isStart == false && isExplain == false && isSelected == false){
					if(!isEnding){
						this.map.character.move[2]=true;
						this.map.character.face="left";
						gc.sendMessage("turn_left");					
					}				
				}
				else{
					if(sel_number-1>=0){
						sel_number--;
					}
				}
			}
			break;
		case KeyEvent.VK_RIGHT :
			if(isView==true){
				viewResult.changeGroup(true);
			}
			else{
				if( isStart == false && isExplain == false && isSelected == false ){
					if(!isEnding){
						this.map.character.move[3]=true;
						this.map.character.face="right";
						gc.sendMessage("turn_right");					
					}
				}
				else{
					if(sel_number+1<=4)
						sel_number++;
					
				}
			}
			break;
		case KeyEvent.VK_Z :
			if( isStart == false && isExplain == false && isSelected == false && isView==false){
				if(!isEnding){
					if(this.map.character.money >= 25){
						gc.sendMessage("create");
						if(this.map.character.skillCreateBlock.toMakeBlock()){
							this.map.character.money-=25;							
						}
					}					
				}			
			}
			break;
		case KeyEvent.VK_X :
			if( isStart == false && isExplain == false && isSelected == false && isView==false){
				if(!isEnding){
					if(this.map.character.money >= 25){
						gc.sendMessage("break");
						if(this.map.character.skillDeleteBlock.toDeleteBlock()){
							this.map.character.money-=25;
						}	
					}					
				}			
			}
			break;
		case KeyEvent.VK_SPACE:
			MapComponent com;
			int index=-1;
			if( isStart == false && isExplain == false && isSelected == false && isView==false){
				if(!isEnding){
					if(this.map.character.face=="up"){
						index=this.map.character.index-15;
					}else if(this.map.character.face=="down"){
						index=this.map.character.index+15;
					}else if(this.map.character.face=="left"){
						index=this.map.character.index-1;
					}else if(this.map.character.face=="right"){
						index=this.map.character.index+1;
					}
					if(index>=0 && index<225){
						com=this.map.components.get(index);
						if(com.type>=1 && com.type<=3){
							if(map.character.money >= 100){
								gc.sendMessage("occupipe");
								this.map.character.skillOccupipeBlock.toOccupipeBlock();
							}
						}else if(com.type==4){
							if(map.character.money >= 1000){
								gc.sendMessage("win");
								victory=true;
								isEnding=true;
							}
						}					
					}					
				}
				else{

					reset();
				}
			}
			break;
		}
	}
	
	public void plusMoney()
	{
		moneyValue++;
		if(moneyValue>=1000)
		{
			moneyValue=0;
			this.map.character.plusMoney();
		}
	}
	
	public void checkMove(){
		MapComponent com;
		int index=this.map.character.index;
		MapComponent com_ori=this.map.components.get(index);
		if(this.map.character.move[0]){
			index-=15;
			if(index >= 0){
				com=this.map.components.get(index);
				if(this.map.character.y==com_ori.y){
					if(com.passable){
						gc.sendMessage("up");
						this.map.character.move("up");
					}						
				}
				else{
					gc.sendMessage("up");
					this.map.character.move("up");
				}
			}	
			else{
				if(this.map.character.y > com_ori.y){
					gc.sendMessage("up");
					this.map.character.move("up");
				}
			}
		}else if(this.map.character.move[1]){
			index+=15;
			if(index<225){
				com=this.map.components.get(index);
				if(this.map.character.y==com_ori.y){
					if(com.passable){
						gc.sendMessage("down");
						this.map.character.move("down");
					}						
				}
				else{
					gc.sendMessage("down");
					this.map.character.move("down");
				}
			}	
			else{
				if(this.map.character.y < com_ori.y){
					gc.sendMessage("down");
					this.map.character.move("down");
				}
			}
		}else if(this.map.character.move[2]){
			int row_pre = index/15;
			int row_after = (index-1)/15;
			index-=1;
			if(index >= 0){
				if(row_pre==row_after){
					com=this.map.components.get(index);
					if(this.map.character.x==com_ori.x){
						if(com.passable){
							gc.sendMessage("left");
							this.map.character.move("left");
						}							
					}
					else{
						gc.sendMessage("left");
						this.map.character.move("left");
					}
				}
				else{
					if(this.map.character.x > com_ori.x){
						gc.sendMessage("left");
						this.map.character.move("left");
					}
				}
			}
			else{
				if(this.map.character.x > com_ori.x){
					gc.sendMessage("left");
					this.map.character.move("left");
				}
			}

		}else if(this.map.character.move[3]){
			int row_pre = index/15;
			int row_after = (index+1)/15;
			index+=1;				
			if(row_pre==row_after){
				com=this.map.components.get(index);
				if(this.map.character.x==com_ori.x){
					if(com.passable){
						gc.sendMessage("right");
						this.map.character.move("right");
					}						
				}
				else{
					gc.sendMessage("right");
					this.map.character.move("right");						
				}
			}
			else{
				if(this.map.character.x < com_ori.x){
					gc.sendMessage("right");
					this.map.character.move("right");
				}
			}		
		}
	}
	
	public void keyReleased(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP :
				this.map.character.move[0]=false;	
			break;
			case KeyEvent.VK_DOWN :
				this.map.character.move[1]=false;					
			break;
			case KeyEvent.VK_LEFT :
				this.map.character.move[2]=false;					

			break;
			case KeyEvent.VK_RIGHT :
				this.map.character.move[3]=false;					
			break;
		}
	}
	

	
}