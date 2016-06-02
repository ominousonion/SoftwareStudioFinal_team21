package client;

import processing.core.PApplet;

public class CharacterState {
	
	int x,y,width,height, op_num;
	private MainApplet applet;
	private int Max_occupies = 5;
	
	CharacterState(MainApplet applet){
		this.x=0;
		this.y=0;
		this.width=300;
		this.height=670;
		this.applet=applet;
		if(applet.map.seq==1) op_num = 2;
		else op_num = 1;
		//Max_occupies = applet.map.Max_Occupipe;
	}
	
	public void display(){
		this.applet.noStroke();
		
		this.applet.fill(105, 105, 105);
		this.applet.rect(x, y, width, height);
		this.applet.fill( 0, 0, 0);
		this.applet.textSize(20);
		this.applet.text("Player"+applet.map.seq, 0, 20);
		this.applet.fill( 235, 192, 0);
		this.applet.textSize(18);
		this.applet.text("Money     :        "+applet.map.character.money, 10, 250);
		this.applet.fill( 0, 0, 0);
		this.applet.textSize(18);
		this.applet.text("Occpuied :", 10, 250+25);
		this.applet.fill(255, 255, 255);
		this.applet.rect(10, this.height/2-50, width-20, 20);
		this.applet.fill(131 ,139 ,131);
		this.applet.rect(10, this.height/2-50, (width-20)*applet.map.character.ocpy/Max_occupies, 20);
		this.applet.fill( 0, 0, 0);
		this.applet.textSize(15);
		this.applet.text(applet.map.character.ocpy+" / "+Max_occupies, 130, this.height/2-35);
		
		this.applet.fill(65, 129, 129);
		this.applet.rect(x, height/2, width, height/2);
		this.applet.fill( 0, 0, 0);
		this.applet.textSize(20);
		this.applet.text("Player"+op_num, 0, height/2+20);
		this.applet.fill( 235, 192, 0);
		this.applet.textSize(18);
		this.applet.text("Money     :        "+applet.map.opponent.money, 10, 250+670/2);
		this.applet.fill( 0, 0, 0);
		this.applet.textSize(18);
		this.applet.text("Occpuied :", 10, 250+670/2+25);
		this.applet.fill(255, 255, 255);
		this.applet.rect(10, this.height-50, width-20, 20);
		this.applet.fill(255, 0, 0);
		this.applet.rect(10, this.height-50, (width-20)*applet.map.opponent.ocpy/Max_occupies, 20);
		this.applet.fill( 0, 0, 0);
		this.applet.textSize(15);
		this.applet.text(applet.map.opponent.ocpy+" / "+Max_occupies, 130, this.height-35);
	}
}