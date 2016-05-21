package client;

import java.awt.event.KeyEvent;

import processing.core.PApplet;
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private GameMap map;

	private final static int width = 1200, height = 650;
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
