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
		break;
		case KeyEvent.VK_DOWN :
			this.map.character.move[1]=true;
		break;
		case KeyEvent.VK_LEFT :
			this.map.character.move[2]=true;
		break;
		case KeyEvent.VK_RIGHT :
			this.map.character.move[3]=true;
		break;
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
					if(com.passable){
						gc.sendMessage("up");
						this.map.character.move("up");
					}	
				}	
		}else if(this.map.character.move[1]){
				index+=15;
				if(index<225){
					com=this.map.components.get(index);
					if(com.passable){
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
						if(com.passable){
							gc.sendMessage("left");
							this.map.character.move("left");
						}	
					}					
				}

		}else if(this.map.character.move[3]){
				int row_pre = index/15;
				int row_after = (index+1)/15;
				index+=1;				
				if(row_pre==row_after){
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
