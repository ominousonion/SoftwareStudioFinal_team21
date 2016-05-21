package client;

import java.awt.event.KeyEvent;

import processing.core.PApplet;
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private GameMap map;
<<<<<<< HEAD
	private final static int width = 1200, height = 650;
	
	public void setup(){
		map=new GameMap(this,0,0,500,500);
		this.size(width, height);
		this.smooth();
=======
	private CharacterState state;
	
	
	public void setup(){
		map=new GameMap(this);
		state=new CharacterState(this);
>>>>>>> 8845caa578f0589e6923a3bd5416be0bb59f8720
		
	}
	
	public void draw(){
		map.display();
<<<<<<< HEAD
	}
	
	public void keyPressed(){
		
=======
		state.display();
>>>>>>> 8845caa578f0589e6923a3bd5416be0bb59f8720
	}
}
