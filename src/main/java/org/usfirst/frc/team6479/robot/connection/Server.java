package org.usfirst.frc.team6479.robot.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//Class to control all connections to the jetson
public class Server {
	
	private Thread serverThread;
	//athough this class could potential control multiple clients, this will only hold the last client to connect
	private ConnectionInstance current;

	public Server(int port) {
		
		//run in a thread
		serverThread = new Thread(() -> {
		
			// init all resources that must be closed here
			try (ServerSocket server = new ServerSocket(port)) {
				
				// loop until thread is stopped
				while (!serverThread.isInterrupted()) {
					//try and accept a new connection
					Socket client = server.accept();
					current = new ConnectionInstance(client);
					current.setDaemon(true);
					current.start();

				}
			}
			catch (IOException e) {
				System.err.println("An error occured: " + e.getMessage());
			}
		});
		serverThread.setDaemon(true);
	}
	
	public void startServer() {
		serverThread.start();
	}
	public void stopServer() {
		serverThread.interrupt();
	}
	public boolean isAlive() {
		return !serverThread.isInterrupted();
	}
	public synchronized ConnectionInstance getCurrentClient() {
		return current;
	}
	
}
