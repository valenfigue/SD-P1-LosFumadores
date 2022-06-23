package com.mycompany.app.servers;

import com.mycompany.app.Vendor;
import com.mycompany.app.benches.Bench;
import com.mycompany.app.Client;
import com.mycompany.app.Ingredient;
import com.mycompany.app.smokers.SmokerWithTobacco;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	protected int port;
	private Socket socket;
	protected Bench bench;
	protected ArrayList<Client> clients = new ArrayList<>();
	protected Client client;
	
	public void startListening() throws IOException, ClassNotFoundException, InterruptedException {
		try (ServerSocket server = new ServerSocket(this.port)) {
			System.out.println("Servidor iniciado.");
			
			while (true) {
				socket = server.accept();
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				
				this.acceptClient(objectInputStream.readObject());
				client.start();
				/*Client currentClient = clients.get(0);
				currentClient.notify();
				currentClient.start();
				clients.remove(0);
				currentClient.getSocket().close();*/
			}
		}
	}
	
	private void acceptClient(Object clientObject) throws InterruptedException {
//		Client client = null;
		if (clientObject instanceof SmokerWithTobacco) {
			client = new SmokerWithTobacco(socket, bench);
		}
		if (clientObject instanceof Vendor) {
			client = new Vendor(socket, bench);
		}
		/*if (client != null) {
			client.wait();
			clients.add(client);
		}*/
	}
	
	private Ingredient[] sendSmokerCigar(Ingredient[] cigar) throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		for (Ingredient ingredient : cigar) {
			dataOutputStream.writeUTF(ingredient.name());
		}
		
		return cigar;
	}
}
