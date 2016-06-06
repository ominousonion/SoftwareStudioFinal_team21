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
	private final static int windowWidth = 1200, windowHeight = 650;
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
						else if(info[1].equals("create"))
							applet.map.opponent.skillCreateBlock.toMakeBlock();
						else if(info[1].equals("break"))
							applet.map.opponent.skillDeleteBlock.toDeleteBlock();
						else if(info[1].equals("occupipe"))
							applet.map.opponent.skillOccupipeBlock.toOccupipeBlock();
						if(info[1].equals("startgame"))
							applet.isBegin = true;
					
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
