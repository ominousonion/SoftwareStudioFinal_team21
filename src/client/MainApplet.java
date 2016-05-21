package client;

import java.awt.event.KeyEvent;

import processing.core.PApplet;
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private GameMap map;
	private final static int width = 1200, height = 650;
	
	public void setup(){
		map=new GameMap(this,0,0,500,500);
		this.size(width, height);
		this.smooth();
		
	}
	
	public void draw(){
		map.display();
	}
	
	public void keyPressed(){
		
	}
}
