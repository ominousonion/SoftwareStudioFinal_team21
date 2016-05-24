package client;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;

public class GameClient extends JFrame{
	private final static int windowWidth = 1200, windowHeight = 670;
	//applet
	private MainApplet applet;

	//connection
	private String destinationIPAddr;
	private int destinationPortNum;
	private Socket socket;
	private PrintWriter writer;
	//private ConnectionThread connection;

	//character data
	private String name = "default";

	public GameClient(){
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
		public ConnectionThread(BufferedReader reader){
			this.reader = reader;
		}
		public void run() {
			while(true) {
				try {
					String line = this.reader.readLine();
					//message accept
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
	}
}
