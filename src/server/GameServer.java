package server;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

public class GameServer extends JFrame{

	private ServerSocket serverSocket;
	private ArrayList<ConnectionThread> connections;
	private JTextArea textArea;
	private HashMap<String, Integer> groupData = new HashMap<String, Integer>();

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
				connections.add(client);
			} catch (BindException e){
				//e.printStackTrace();
			} catch (IOException e){
				//e.printStackTrace();
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
					String [] info = line.split(":");	// info[0] name, info[1] message
					//broadcast();
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
			for (ConnectionThread connection: connections) {
				connection.sendMessage(message);
			}
		}

	public void writeToFile(){          //write user's input to text file
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("output.txt"));
			for(String group : groupData.keySet()){
		    	writer.write(group.concat(" ").concat(Integer.toString(groupData.get(group))));
			    writer.newLine();
			    writer.flush();
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
		String group;
		int times;
		try{
			Scanner filescn = new Scanner(new FileInputStream(".output.txt"));
			while(filescn.hasNext()){
				group = filescn.next();
				times = Integer.parseInt(filescn.next());
				groupData.put(group, times);
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
