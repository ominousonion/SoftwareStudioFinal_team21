package client;

import java.awt.event.KeyEvent;

import processing.core.PApplet;
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private GameMap map;

	private final static int width = 1200, height = 650;
	private CharacterState state;
	
	
	public void setup(){
		size(width,height);
		map=new GameMap(this);
		state=new CharacterState(this);
	
	}
	
	public void draw(){
		map.display();
		state.display();
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
