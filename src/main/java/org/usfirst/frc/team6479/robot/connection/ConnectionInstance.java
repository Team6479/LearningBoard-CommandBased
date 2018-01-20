package org.usfirst.frc.team6479.robot.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//one connection to a client
public class ConnectionInstance extends Thread {

	public ConnectionInstance(Socket s) {
		super("ConnectionInstance");
		socket = s;
		dataOutput = "Open connection";
		dataRecieved = "No data yet recieved";
	}

	@Override
	public void run() {
		// init all resources that must be closed here
		try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

			// loop until thread is stopped
			while (!isInterrupted()) {
				dataRecieved = in.readLine();
				out.println(dataOutput);
			}
			socket.close();
		}
		catch (IOException e) {
			System.err.println("An error occured: " + e.getMessage());
			dataRecieved = "No New Data";
		}
	}

	public synchronized String readData() {
		return dataRecieved;
	}

	public synchronized void sendData(String data) {
		dataOutput = data;
	}

	private Socket socket;
	private String dataOutput;
	private String dataRecieved;
}
