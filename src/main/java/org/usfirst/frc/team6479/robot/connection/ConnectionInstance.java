package org.usfirst.frc.team6479.robot.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import communication.JetsonPacket.*;
import communication.JetsonPacket.ModePacket.Mode;

//one connection to a client
public class ConnectionInstance extends Thread {

	public ConnectionInstance(Socket s) {
		super("ConnectionInstance");
		socket = s;
		
		//set default for inout
		dataRecieved = CameraPacket.newBuilder().setDistance(0).build();
		
		//default value for output
		setMode(Mode.CUBE);
		
		System.out.println(dataOutput);
	}

	@Override
	public void run() {
		// init all resources that must be closed here
		try (OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream()) {

			// loop until thread is stopped
			while (!isInterrupted()) {
				dataRecieved = CameraPacket.parseFrom(in);
				out.write(dataOutput.toByteArray());
			}
			socket.close();
		}
		catch (IOException e) {
			System.err.println("An error occured: " + e.getMessage());
		}
	}

	public synchronized Double getDistance() {
		return dataRecieved.getDistance();
	}

	public synchronized void setMode(ModePacket.Mode mode) {
		dataOutput = ModePacket.newBuilder().setMode(mode).build();
	}

	private Socket socket;
	private ModePacket dataOutput;
	private CameraPacket dataRecieved;
}
