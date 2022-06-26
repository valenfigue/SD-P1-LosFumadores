package com.mycompany.app.serverSide;

import com.mycompany.app.common.Bench;
import com.mycompany.app.common.Client;
import com.mycompany.app.common.Vendor;
import com.mycompany.app.common.smokers.SmokerWithMatchstick;
import com.mycompany.app.common.smokers.SmokerWithPaper;
import com.mycompany.app.common.smokers.SmokerWithTobacco;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class BenchServer {
	protected int port;
	private Socket socket;
	protected Bench bench;
	protected ArrayList<Client> clients = new ArrayList<>();
	protected Client client;
	
	@SuppressWarnings("InfiniteLoopStatement")
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
	
	private void acceptClient(Object clientObject) {
//		Client client = null;
		if (clientObject instanceof SmokerWithTobacco) {
			client = new SmokerWithTobacco(socket, bench);
		}
		if (clientObject instanceof SmokerWithMatchstick) {
			client = new SmokerWithMatchstick(socket, bench);
		}
		if (clientObject instanceof SmokerWithPaper) {
			client = new SmokerWithPaper(socket, bench);
		}
		if (clientObject instanceof Vendor) {
			client = new Vendor(socket, bench);
		}
		/*if (client != null) {
			client.wait();
			clients.add(client);
		}*/
	}
}
