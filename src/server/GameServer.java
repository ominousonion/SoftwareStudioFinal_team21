package server;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

public class GameServer extends JFrame{

	private ServerSocket serverSocket;
	private ArrayList<ConnectionThread> connections;
	private JTextArea textArea;
	private HashMap<String, ArrayList<String>> groupData = new HashMap<String, ArrayList<String>>();
	private HashMap<String, Integer> itemData = new HashMap<String, Integer>();
	private boolean start;
	private EventCall event = new EventCall();
	private int sel_count = 0;
	private Random ran;

	GameServer(int portNum){
		super("server");
		this.readData();
		this.setSize(400,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		this.textArea.setPreferredSize(new Dimension(400,300));
		this.add(this.textArea);
		this.setVisible(true);
		this.setResizable(false);
		this.start=false;
		this.ran=new Random();
		
		connections = new ArrayList<ConnectionThread>();
		try {
			//create server socket
			this.serverSocket = new ServerSocket(portNum);
			this.textArea.append("Server starts listening on port "+portNum+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ServerRunning(){
		this.textArea.append("Server starts waiting for client.\n");
		// Create a loop to make server wait for client forever (unless you stop it)
		// Make sure you do create a connectionThread and add it into 'connections'
		while(true){
			try {
				Socket connectionToClient = this.serverSocket.accept();
				this.textArea.append("Get connection from client "
						+ connectionToClient.getInetAddress() + ":"
						+ connectionToClient.getPort()+"\n");
				ConnectionThread client = new ConnectionThread(connectionToClient);
				client.start();
				if(connections.isEmpty()) client.sendMessage("server:setting_1");
				else{
					client.sendMessage("server:setting_2");
					//itemData.put("coke", itemData.get("coke")+1);
					event.start();
					//writeToFile();
				}
				connections.add(client);
			} catch (BindException e){
				//e.printStackTrace();
			} catch (IOException e){
				//e.printStackTrace();
			}
			
		}
		
	}
	
	public class EventCall extends Thread{
		public void run(){
			int eventcnt=0;
			while(true){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(start){
					broadcast("plus");
					
					if(eventcnt==20) eventcnt=0; /////// change the period of event
					else if(eventcnt!=0) eventcnt++;
					else eventcnt=0;
					
					if(ran.nextInt(30)==0 && eventcnt==0){
						broadcast("event"+ran.nextInt(5));
						eventcnt=1;
					}else if(eventcnt==4){  ///// time of picture 
						broadcast("pichide");
					}
				}
				
			}
		}
	}

	public class ConnectionThread extends Thread{
		private Socket socket;
		private BufferedReader reader;
		private PrintWriter writer;
		public ConnectionThread(Socket socket) {
			this.socket = socket;
			try{
				this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			}catch(IOException e){
				//e.printStackTrace();
			}
		}
		public void run(){
			while(true) {
				try {
					String line = this.reader.readLine();
					if(line!=null){
						textArea.append(line+"\n");
						String [] info = line.split(":");
						if(info[1].contains("selected")){
							String[] itemsel = info[1].split(" ");
							itemData.put(itemsel[1], itemData.get(itemsel[1])+1);
							sel_count++;
							if(sel_count == 2) {
								broadcast("startgame");
								start=true;
							}
							textArea.append(sel_count+"\n");
						}
						else{
							for(ConnectionThread ct: connections){
								if(ct.equals(this)==false){
									ct.sendMessage(line);
								}	
							}
						}						
					}

				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}

		public void sendMessage(String message) {
			this.writer.println(message);
			this.writer.flush();
		}
	}
	
	public void broadcast(String message){
		//send message to every clients in the list
		String msg = "server:";
		//textArea.append("event");
		for (ConnectionThread connection: connections) {
			connection.sendMessage(msg.concat(message));
		}
	}
		

	public void writeToFile(){          //write user's input to text file
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("./output.txt"));
			for(String group : groupData.keySet()){
				for(String name : groupData.get(group)){
					String type = group.concat(" ").concat(name).concat(" ").concat(itemData.get(name).toString());
					writer.write(type);
					writer.newLine();
					writer.flush();
				}
		    	
			}
		}catch(IOException ex) {
			    ex.printStackTrace();
		}
		try{
			writer.close();
		}catch(IOException ex) {
		}
	}

	public void readData(){
		String group, name;
		int times;
		try{
			Scanner filescn = new Scanner(new FileInputStream("./output.txt"));
			while(filescn.hasNext()){
				group = filescn.next();
				name = filescn.next();
				times = Integer.parseInt(filescn.next());
				itemData.put( name, times);
				if(groupData.containsKey(group)==false){
					ArrayList<String> list = new ArrayList<String>();
					list.add(name);
					groupData.put( group, list);
					itemData.put(name, times);
				}
				else{
					groupData.get(group).add(name);
				}	
			}
		}catch(IOException e){
			System.out.println("Can't not find file");
		}
	}

	public static void main(String[] args) {
		GameServer server = new GameServer(8000);
		server.ServerRunning();
	}
}