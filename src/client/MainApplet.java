package client;

import java.awt.event.KeyEvent;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private GameMap map;
	private final static int width = 1200, height = 650;
	private CharacterState state;
	private boolean isStart;//paul
	private boolean isExplain;
	private Button btn;//Paul added
	private BackButton backbtn;
	
	public void setup(){
		size(width,height);
		map=new GameMap(this);
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
		}
	}
	
	/*control the behavior of buttonA*/
	public void buttonA()
	{
		isStart = false;//go to the game scene
		//btn.hideButton();//hide the button
	}
	
	/*control the behavior of buttonB*/
	public void buttonB(){ 
		isExplain = true;
		//btn.hideButton();
	}
	
	/*control the behavior of buttonC*/
	public void buttonC(){
		System.exit(0);//leave the game
	}
	
	/*control the behavior of backButton*/
	public void backBtn()
	{
		isExplain = false;
		//backbtn.hideButton();
		//btn.showButton();
	}
		
	public void keyPressed(KeyEvent e){
		int pos_x=this.map.character.x, pos_y=this.map.character.y;
		int step=this.map.character.oneStep;
		MapComponent com;
		int index;
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP :
				index=(pos_y-this.map.SquareY-this.map.SquareUnit)/this.map.SquareUnit*15+(pos_x-this.map.SquareX)/this.map.SquareUnit;
				if(pos_y-step >= this.map.SquareY){
					com=this.map.components.get(index);
					if(com.passable){
						this.map.character.move("up");
					}	
				}	
				break;
			case KeyEvent.VK_DOWN :
				index=(pos_y-this.map.SquareY+this.map.SquareUnit)/this.map.SquareUnit*15+(pos_x-this.map.SquareX)/this.map.SquareUnit;
				if(pos_y+step < this.map.SquareY+this.map.SquareHeight){
					com=this.map.components.get(index);
					if(com.passable){
						this.map.character.move("down");
					}
				}	
				break;
			case KeyEvent.VK_LEFT :
				index=(pos_y-this.map.SquareY)/this.map.SquareUnit*15+(pos_x-this.map.SquareX-this.map.SquareUnit)/this.map.SquareUnit;
				if(pos_x-step >= this.map.SquareX){
					com=this.map.components.get(index);
					if(com.passable){
						this.map.character.move("left");
					}	
				}
				break;
			case KeyEvent.VK_RIGHT :
				index=(pos_y-this.map.SquareY)/this.map.SquareUnit*15+(pos_x-this.map.SquareX+this.map.SquareUnit)/this.map.SquareUnit;
				if(pos_x+step <this.map.SquareX+this.map.SquareWidth){
					com=this.map.components.get(index);
					if(com.passable){
						this.map.character.move("right");
					}		
				}
				break;
		}
	}
	
}

