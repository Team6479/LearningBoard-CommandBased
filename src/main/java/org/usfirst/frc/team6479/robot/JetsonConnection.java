package org.usfirst.frc.team6479.robot;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.hal.HAL;

//Class to control all connections to the jetson
public class JetsonConnection {
	
	//create a server network table
	private JetsonConnection() {
		//make a network table insatnce
		table = NetworkTableInstance.create();
		//listen on any address
		table.startServer("JetsonTable", "", 1182);
		
		
		tableChecker = new Thread(() -> {
			
			while(!tableChecker.isInterrupted()) {
			    System.out.println("Did we connect? " + table.isConnected());
			    System.out.println(table.getConnections());
			}
		});
		
		tableChecker.setDaemon(true);
		tableChecker.start();
		
		try {
			//ssh session setup
			JSch jsch = new JSch();
			
			Session s = jsch.getSession("ubuntu", "tegra-ubuntu.local");
			s.setConfig("StrictHostKeyChecking", "no");
			s.setPassword("ubuntu");
			s.connect();
			
			//start an exec session
			ChannelExec exec = (ChannelExec) s.openChannel("exec");
			exec.setCommand("sh runCameraVision");
			exec.connect();
			
			//get exit status
			int exitStatus = exec.getExitStatus();
			
			//end the session
			exec.disconnect();
			s.disconnect();
			
			if(exitStatus > 0) {
				//send error to driver station
				HAL.sendError(true, 0, false, "Command run with error", "", "", true);
			}

		}
		catch (JSchException e) {
			//send error to driver station
			HAL.sendError(true, 0, false, "Error remote connecting to Jetson", e.getMessage(), "", true);
			
		}
		
	}
	private Thread tableChecker;

	//this will keep the table alive
	private NetworkTableInstance table;
	//instance tbat is avaiable to users
	public static JetsonConnection connection = new JetsonConnection();
	
}
