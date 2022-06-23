package com.mycompany.app.servers;

import com.mycompany.app.Bench;
import com.mycompany.app.Client;
import com.mycompany.app.Smoker;
import com.mycompany.app.smokers.SmokerWithTobacco;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {
	protected int port;
	private Socket socket;
	private ArrayList<Socket> sockets;
	Bench bench = new Bench();
	
	public void startListening() throws IOException, ClassNotFoundException {
		ServerSocket server = new ServerSocket(this.port);
		System.out.println("Servidor iniciado.");
		
		while (true) {
			socket = server.accept();
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			
			this.manageClient(objectInputStream.readObject());
//			Client client = this.manageClient(objectInputStream.readObject());
			/*client.start();
			objectOutputStream.writeObject(client);*/
		}
	}
	
	private Client manageClient(String clientName) {
		
		return switch (clientName) {
			case "Fumador con Tabaco" -> new SmokerWithTobacco(this.socket, this.bench);
			default -> throw new IllegalStateException("Unexpected value: " + clientName);
		};
	}
	
	private void manageClient(Object client) throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		Smoker smoker = null;
		
		if (client instanceof SmokerWithTobacco) {
			smoker = (SmokerWithTobacco) client;
			smoker.setBench(bench);
//			smoker.start();
			new Thread(smoker).start();
			objectOutputStream.writeObject(smoker);
//			return new SmokerWithTobacco(this.socket, this.bench);
		}
//		return null;
	}
	
	@Override
	public void run() {
	
	}
}
