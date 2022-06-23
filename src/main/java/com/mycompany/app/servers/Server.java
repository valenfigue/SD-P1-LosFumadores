package com.mycompany.app.servers;

import com.mycompany.app.Bench;
import com.mycompany.app.Client;
import com.mycompany.app.Ingredient;
import com.mycompany.app.Smoker;
import com.mycompany.app.smokers.SmokerWithTobacco;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	protected int port;
	private Socket socket;
	Bench bench = new Bench();
	Client client;
	
	public void startListening() throws IOException, ClassNotFoundException {
		ServerSocket server = new ServerSocket(this.port);
		System.out.println("Servidor iniciado.");
		
		while (true) {
			socket = server.accept();
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			
			this.acceptClient(objectInputStream.readObject());
			client.start();
		}
	}
	
	private void acceptClient(Object clientObject) {
		if (clientObject instanceof SmokerWithTobacco) {
			this.client = new SmokerWithTobacco(socket, bench);
		}
	}
	
	private Ingredient[] sendSmokerCigar(Ingredient[] cigar) throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		for (Ingredient ingredient : cigar) {
			dataOutputStream.writeUTF(ingredient.name());
		}
		
		return cigar;
	}
}
