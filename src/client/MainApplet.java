package client;

import java.awt.event.KeyEvent;
import java.util.Random;
import de.looksgood.ani.Ani;

import processing.core.PApplet;
import processing.core.PImage;

@SuppressWarnings("serial")

public class MainApplet extends PApplet{
	public GameMap map;
	private Ani ani;
	private final static int width = 1200, height = 650;
	private CharacterState state;
	private GameClient gc;
	private boolean isStart;//paul
	private boolean isExplain;
	private boolean isSelected;
	private Button btn;//Paul added
	private BackButton backbtn;
	private PictureSelButton picSelButton;
	private PicData picdata1;
	private PImage []itemImg = new PImage[100];
	private int sel_number;
	
	private Random r = new Random();
	private int ran;
	
	private int moneyValue=0;
	
	public MainApplet(GameClient gc){
		this.gc = gc;
	}
	
	public void setup(){
		Ani.init(this);
		size(width,height);
		map=new GameMap(this,r.nextInt(1)+1, gc);
		state=new CharacterState(this);
		isStart = true;
		isExplain = false;
		btn = new Button(this);
		backbtn = new BackButton(this);
		picSelButton = new PictureSelButton(this);
		picdata1 = new PicData(this);
		sel_number = 0;
		for( String str : picdata1.getGroupData().get("Sneaker")){
			itemImg[sel_number] = picdata1.getImageData().get(str);
			sel_number++;
		}
		sel_number = 0;
	}
	
	public void draw(){
		if( isStart == true && isExplain == false && isSelected == false){
			background(0);
			picSelButton.hideButton();
			backbtn.hideButton();
			btn.showButton();
			btn.display();
		}
		else if( isExplain == true && isSelected == false ){
			background(167);
			btn.hideButton();
			backbtn.showButton();
			backbtn.display();
		}
		else if( isSelected == true ){
			background(color(0,125,0));
			btn.hideButton();
			picSelButton.showButton();
			picSelButton.display();
			//itemImg = picdata1.getImageData().get("Nike");
			/*for( String str : picdata1.getGroupData().get("Sneaker")){
				itemImg[sel_number] = picdata1.getImageData().get(str);
			}*/
			image(itemImg[sel_number],450,150);
		}
		else{
			btn.hideButton();
			backbtn.hideButton();
			map.display();
			state.display();
			checkMove();
		}
		
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
		System.exit(0);//leave the game
	}
	
	/*control the behavior of backButton*/
	public void backBtn(){
		isExplain = false;
	}
	
	public void buttonSel1(){
		picSelButton.hideButton();
		isSelected = false;
	}
	/*public void buttonSel2(){
		picSelButton.hideButton();
		isSelected = false;
	}*/
	
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP :
			if(isStart == false && isExplain == false &&isSelected == false){
				this.map.character.move[0]=true;
				this.map.character.face="up";				
			}
		break;
		case KeyEvent.VK_DOWN :
			if(isStart == false && isExplain == false &&isSelected == false){
				this.map.character.move[1]=true;
				this.map.character.face="down";				
			}
		break;	
		case KeyEvent.VK_LEFT :
			if( isStart == false && isExplain == false &&isSelected == false){
				this.map.character.move[2]=true;
				this.map.character.face="left";
			}
			else{
				if(sel_number-1>=0)
					sel_number--;
			}
			break;
		case KeyEvent.VK_RIGHT :
			if( isStart == false && isExplain == false &&isSelected == false ){
				this.map.character.move[3]=true;
				this.map.character.face="right";
			}
			else{
				if(sel_number+1<=3)
					sel_number++;
			}
			break;
		case KeyEvent.VK_Z :
			if( isStart == false && isExplain == false &&isSelected == false ){
				gc.sendMessage("create");
				this.map.character.skillCreateBlock.toMakeBlock();				
			}
			break;
		case KeyEvent.VK_X :
			if( isStart == false && isExplain == false &&isSelected == false ){
				gc.sendMessage("break");
				this.map.character.skillDeleteBlock.toDeleteBlock();				
			}
			break;
		case KeyEvent.VK_SPACE:
			MapComponent com;
			if( isStart == false && isExplain == false &&isSelected == false ){
				com=this.map.components.get(this.map.character.index);
				if(com.type>=1 && com.type<=3){
					gc.sendMessage("occupipe");
					com.occupipe(this.map.character.number);
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