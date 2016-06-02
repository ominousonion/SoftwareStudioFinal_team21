package client;

import java.awt.event.KeyEvent;
import java.util.Random;
import de.looksgood.ani.Ani;

import processing.core.PApplet;
@SuppressWarnings("serial")

public class MainApplet extends PApplet{
	public GameMap map;
	private Ani ani;
	private final static int width = 1200, height = 650;
	private CharacterState state;
	private GameClient gc;
	private boolean isStart;//paul
	private boolean isExplain;
	private Button btn;//Paul added
	private BackButton backbtn;
	
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
	
	}
	
	public void draw(){
		if( isStart == true && isExplain == false){
			background(0);
			backbtn.hideButton();
			btn.showButton();
			btn.display();
		}
		else if( isExplain == true ){
			background(167);
			btn.hideButton();
			backbtn.showButton();
			backbtn.display();
		}
		else{
			btn.hideButton();
			backbtn.hideButton();
			map.display();
			state.display();
			checkMove();
			plusMoney();
			
		}
	}
	
	/*control the behavior of buttonA*/
	public void buttonA(){
		isStart = false;//go to the game scene
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
	
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP :
			this.map.character.move[0]=true;
			this.map.character.face="up";
		break;
		case KeyEvent.VK_DOWN :
			this.map.character.move[1]=true;
			this.map.character.face="down";
		break;
		case KeyEvent.VK_LEFT :
			this.map.character.move[2]=true;
			this.map.character.face="left";
		break;
		case KeyEvent.VK_RIGHT :
			this.map.character.move[3]=true;
			this.map.character.face="right";
		break;
		case KeyEvent.VK_Z :
			this.map.character.skillCreateBlock.toMakeBlock();
		break;
		case KeyEvent.VK_X :
			this.map.character.skillDeleteBlock.toDeleteBlock();
		break;
		case KeyEvent.VK_SPACE :
			this.map.character.skillOccupipeBlock.toOccupipeBlock();
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
		int pos_x=this.map.character.x, pos_y=this.map.character.y;
		int step=this.map.character.oneStep;
		MapComponent com;
		int index;
		if(this.map.character.move[0]){
				index=(pos_y-this.map.SquareY-this.map.SquareUnit)/this.map.SquareUnit*15+(pos_x-this.map.SquareX)/this.map.SquareUnit;
				if(pos_y-step >= this.map.SquareY){
					com=this.map.components.get(index);
					if(com.passable){
						gc.sendMessage("up");
						this.map.character.move("up");
					}	
				}	
		}else if(this.map.character.move[1]){
				index=(pos_y-this.map.SquareY+this.map.SquareUnit)/this.map.SquareUnit*15+(pos_x-this.map.SquareX)/this.map.SquareUnit;
				if(pos_y+step < this.map.SquareY+this.map.SquareHeight){
					com=this.map.components.get(index);
					if(com.passable){
						gc.sendMessage("down");
						this.map.character.move("down");
					}
				}	
		}else if(this.map.character.move[2]){
				index=(pos_y-this.map.SquareY)/this.map.SquareUnit*15+(pos_x-this.map.SquareX-this.map.SquareUnit)/this.map.SquareUnit;
				if(pos_x-step >= this.map.SquareX){
					com=this.map.components.get(index);
					if(com.passable){
						gc.sendMessage("left");
						this.map.character.move("left");
					}	
				}
		}else if(this.map.character.move[3]){
				index=(pos_y-this.map.SquareY)/this.map.SquareUnit*15+(pos_x-this.map.SquareX+this.map.SquareUnit)/this.map.SquareUnit;
				if(pos_x+step <this.map.SquareX+this.map.SquareWidth){
					com=this.map.components.get(index);
					if(com.passable){
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
