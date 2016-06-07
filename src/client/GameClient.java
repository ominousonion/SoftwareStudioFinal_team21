package client;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
@SuppressWarnings("serial")
public class GameClient extends JFrame{
	private final static int windowWidth = 1200, windowHeight = 680;
	//applet
	private MainApplet applet;
	public int seq = 0;

	//connection
	private String destinationIPAddr;
	private int destinationPortNum;
	private Socket socket;
	private PrintWriter writer;
	//private ConnectionThread connection;

	//character data
	private String name = "de";

	public GameClient(){


		//create window
		this.setTitle("team21_final");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(windowWidth,windowHeight);
		this.setVisible(true);
		this.setResizable(false);

	}

	public GameClient(String IP,int portNum){
		this();
		this.destinationIPAddr = IP;
		this.destinationPortNum = portNum;
	}

	//connect to server
	public void connect(){
		try{
			this.socket = new Socket(this.destinationIPAddr,this.destinationPortNum);
			this.writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			Thread clientThread = new ConnectionThread(reader);
			clientThread.start();
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

	public class ConnectionThread extends Thread{
		private BufferedReader reader;
		//public int seq = 0;
		public ConnectionThread(BufferedReader reader){
			this.reader = reader;
		}
		public void run() {
			while(true) {
				try {
					String line = this.reader.readLine();
					//message accept
					String [] info = line.split(":");	// info[0] name, info[1] message
					//System.out.println(info[1]);
					if(info[1].equals("setting_1")) seq = 1;
					else seq = 2;
					if(applet!=null) System.out.println(info[1]);
						if(info[1].equals("up")||info[1].equals("down")|| info[1].equals("left") ||info[1].equals("right") )
							applet.map.opponent.move(info[1]);
						else if(info[1].equals("turn_up"))
							applet.map.opponent.face="up";
						else if(info[1].equals("turn_down"))
							applet.map.opponent.face="down";
						else if(info[1].equals("turn_left"))
							applet.map.opponent.face="left";
						else if(info[1].equals("turn_right"))
							applet.map.opponent.face="right";
						else if(info[1].equals("create")){
							if(applet.map.opponent.skillCreateBlock.toMakeBlock()){
								applet.map.opponent.money-=25;	
							}	
						}
						else if(info[1].equals("break")){
							if(applet.map.opponent.skillDeleteBlock.toDeleteBlock()){
								applet.map.opponent.money-=25;
							}
						}
						else if(info[1].equals("occupipe")){
							applet.map.opponent.skillOccupipeBlock.toOccupipeBlock();
						}	
						else if(info[1].equals("plus")){
							applet.map.character.plusMoney();
							applet.map.opponent.plusMoney();
						}
						else if(info[1].equals("event0")){ //event
							applet.map.Event(0);
						}
						else if(info[1].equals("event1")) applet.map.Event(1);
						else if(info[1].equals("event2")) applet.map.Event(2);
						else if(info[1].equals("event3")) applet.map.Event(3);
						else if(info[1].equals("event4")) applet.map.Event(4);
						/*else if(info[1].equals("event5")) applet.map.Event(5);
						else if(info[1].equals("event6")) applet.map.Event(6);
						else if(info[1].equals("event7")) applet.map.Event(7);
						else if(info[1].equals("event8")) applet.map.Event(8);*/
						else if(info[1].equals("pichide")){ //hide picture
							applet.map.ep.show=false;
						}
						else if(info[1].equals("startgame")){
							if(applet.map.opponent.type!=0) sendMessage("type"+applet.map.opponent.type);
							applet.isBegin = true;			
						}
						else if(info[1].equals("win")){
							applet.victory=false;
							applet.isEnding=true;
						}
						else if(info[1].substring(0, 4).equals("type")){ //
							if(applet.map.character.type==info[1].charAt(4)+'0' && applet.map.opponent.type!=0){} 
							else applet.map.opponent.type=info[1].charAt(4)+'0';
						}
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}
	}

	public void sendMessage(String message){
		message = name.concat(":").concat(message);//use string split
		this.writer.println(message);
		this.writer.flush();
	}

	public static void main(String [] args){
		GameClient client=new GameClient("127.0.0.1",8000);// IP and portnum
		client.connect();
		//create applet
		client.applet = new MainApplet(client);
		client.applet.init();
		client.applet.start();
		client.applet.setFocusable(true);
		client.setContentPane(client.applet);
	}
}