package server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class GameServer {
	private ServerSocket serverSocket;
	private ArrayList<ConnectionThread> connections;
	
	GameServer(int portNum){
		try {
			//create server socket
			this.serverSocket = new ServerSocket(portNum);
			System.out.printf("Server starts listening on port %d.\n", portNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ServerRunning(){
		
	}
	
	public class ConnectionThread extends Thread{

		
		public ConnectionThread(Socket socket,int num) {

		}
		public void run(){

		}

	}
	
	public static void main(String[] args) {	
		GameServer server = new GameServer(8000);
	}
}
