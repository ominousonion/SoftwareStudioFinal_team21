package client;

import controlP5.*;

public class PictureSelButton {
	
	private ControlP5 cp5;
	private String msg = "";
	private MainApplet parent;
	
	public PictureSelButton(MainApplet parent){
		this.parent = parent;
		//parent.size(3600, 370);
		cp5 = new ControlP5(parent);
		cp5.addButton("buttonSel1")
		.setLabel("SELECT")
		.setPosition(2000/4, 500)
		.setSize(200, 50);
		/*cp5.addButton("buttonSel2")
		.setLabel("Picture2")
		.setPosition(2800/4, 500)
		.setSize(200, 50);*/

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
