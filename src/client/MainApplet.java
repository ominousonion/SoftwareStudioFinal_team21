package client;

import processing.core.PApplet;

public class MainApplet extends PApplet{
	private GameMap map;
	private CharacterState state;
	
	
	public void setup(){
		map=new GameMap(this);
		state=new CharacterState(this);
		
	}
	
	public void draw(){
		map.display();
		state.display();
	}

}
