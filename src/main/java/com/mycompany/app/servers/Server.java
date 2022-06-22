package com.mycompany.app.servers;

import com.mycompany.app.Client;
import com.mycompany.app.Smoker;
import com.mycompany.app.Vendor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	protected int port;
	public Socket socket;
	DataInputStream inputStream;
	DataOutputStream outputStream;
	
	protected Vendor vendor;
	protected Smoker smoker;
	
	public void startListening() throws IOException {
		ServerSocket server = new ServerSocket(this.port);
		System.out.println("Servidor iniciado.");
		
		while (true) {
			socket = server.accept();
			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());
			
			this.acceptClient(inputStream.readUTF());
//			Thread client = new Thread()
		}
	}
	
	private void acceptClient(String clientName) {
		Client client;
		switch (clientName) {
			case "Fumador con Tabaco":
				Smoker.createSmokerWithTobacco(socket).start();
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + clientName);
		}
	}
}
