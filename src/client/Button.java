package client;

import controlP5.*;

public class Button {
	
	private ControlP5 cp5;
	private String msg = "";
	private MainApplet parent;
	
	public Button(MainApplet parent){
		this.parent = parent;
		//parent.size(3600, 370);
		cp5 = new ControlP5(parent);
		cp5.addButton("buttonA")
		.setLabel("Game Start")
		.setPosition(2000/4, 370/2)
		.setSize(200, 50);
		cp5.addButton("buttonB")
		.setLabel("Explanation")
		.setPosition(2000/4, 3*370/4)
		.setSize(200, 50);
		cp5.addButton("buttonC")
		.setLabel("Exit")
		.setPosition(2000/4,370)
		.setSize(200,50);
	}
	
	public void display(){
		//parent.fill(0);
		parent.textSize(26);
		parent.text(msg, 2000/4, 370/4);
		System.out.println(msg);
	}
	
	//hide the button
	public void hideButton()
	{
		cp5.hide();
	}
	
	//show the button
	public void showButton()
	{
		cp5.show();
	}
	
}
