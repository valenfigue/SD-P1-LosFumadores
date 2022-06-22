package com.mycompany.app.servers;

import com.mycompany.app.Bench;
import com.mycompany.app.Client;
import com.mycompany.app.smokers.SmokerWithTobacco;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	protected int port;
	private Socket socket;
	DataOutputStream dataOutputStream;
	
	Bench bench = new Bench();
	
	public void startListening() throws IOException {
		DataInputStream dataInputStream;
		ServerSocket server = new ServerSocket(this.port);
		System.out.println("Servidor iniciado.");
		
		while (true) {
			socket = server.accept();
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			
			Client client = this.acceptClient(dataInputStream.readUTF());
			client.start();
			
			/*ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(client);*/
		}
	}
	
	private Client acceptClient(String clientName) {
		
		return switch (clientName) {
			case "Fumador con Tabaco" -> new SmokerWithTobacco(this.socket, this.bench);
			default -> throw new IllegalStateException("Unexpected value: " + clientName);
		};
	}
}
