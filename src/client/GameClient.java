package client;

import javax.swing.*;

import java.io.IOException;
import java.net.*;

public class GameClient extends JFrame{
	private final static int windowWidth = 1200, windowHeight = 670;
	//applet
	private MainApplet applet;
	
	//connection
	private String destinationIPAddr;
	private int destinationPortNum;
	private Socket socket;
	private ConnectionThread connection;
	
	
	GameClient(){
		//create applet
		this.applet = new MainApplet();
		this.applet.init();
		this.applet.start();
		this.applet.setFocusable(true);
		
		//create window
		this.setTitle("team21_final");
		this.setContentPane(applet);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(windowWidth,windowHeight);
		this.setVisible(true);
		
	}
	
	//connect to server
	public void connect(){
		try{
			this.socket=new Socket(this.destinationIPAddr,this.destinationPortNum);
		}
		catch (UnknownHostException e){
			e.printStackTrace();
		} 
		catch (ConnectException e){
			e.printStackTrace();
		} 
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	private class ConnectionThread extends Thread{
		
		ConnectionThread(){
			
		}
		
		public void run(){
			
		}
		
	}
	
	public static void main(String [] args){
		GameClient client=new GameClient();

	}
}
