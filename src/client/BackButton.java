package client;

import controlP5.*;

public class BackButton {
	
	private ControlP5 cp5;
	private String msg = "";
	private MainApplet parent;
	
	public BackButton(MainApplet parent){
		this.parent = parent;
		//parent.size(3600, 370);
		cp5 = new ControlP5(parent);
		cp5.addButton("backBtn")
		.setLabel("Back")
		.setPosition(3600/4, 550)
		.setSize(200, 50);
	}
	
	public void display(){
		//parent.fill(0);
		parent.textSize(26);
		parent.text(msg, 3600/4, 550);
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
