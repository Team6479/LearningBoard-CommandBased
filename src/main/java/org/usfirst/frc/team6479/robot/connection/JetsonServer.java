package org.usfirst.frc.team6479.robot.connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import communication.JetsonPacket.CameraPacket;
import communication.JetsonPacket.ModePacket;

//Class to control all connections to the jetson
public class JetsonServer {

	private ServerSocketChannel server;
	private Thread thread;

	public JetsonServer(int port) {

		dataRecieved = CameraPacket.getDefaultInstance();
		
		// start a thread to connect
		thread = new Thread(() -> {
			try {
				server = ServerSocketChannel.open();
				server.bind(new InetSocketAddress(port));
				// no blocking
				server.configureBlocking(true);

				// loop forever
				while (!thread.isInterrupted()) {
					// get a connection
					SocketChannel client = server.accept();
					// contstantly check for a new distance and send the current mode

					while (client.isConnected()) {
						// read in from the current buffer
						ByteBuffer input = ByteBuffer.allocate(1024);
						client.read(input);
						input.flip();
						dataRecieved = CameraPacket.parseFrom(input);

						// otput to the buffer
						ByteBuffer output = ByteBuffer.allocate(1024);
						output.put(dataOutput.toByteArray());
						output.flip();
						client.write(output);
					}
				}
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		thread.setDaemon(true);
		thread.start();
	}

	public synchronized Double getDistance() {
		return dataRecieved.getDistance();
	}

	public synchronized void setMode(ModePacket.Mode mode) {
		dataOutput = ModePacket.newBuilder().setMode(mode).build();
	}

	private ModePacket dataOutput;
	private CameraPacket dataRecieved;
}
