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

public class BenchServer {
	protected int port;
	private Socket socket;
	protected Bench bench;
	protected Client client;
	
	@SuppressWarnings("InfiniteLoopStatement")
	public void startListening() throws IOException, ClassNotFoundException, InterruptedException {
		try (ServerSocket server = new ServerSocket(this.port)) {
			System.out.println("Servidor iniciado.");
			
			while (true) {
				socket = server.accept();
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				
				this.acceptClient(objectInputStream.readObject());
				int oldIngredientsQuantity = 0;
				/*if (client instanceof Vendor) { // FIXME problemas al enviar y recibir mensajes del vendedor.
					client.sendBenchId("El vendedor repondrá los ingredientes de la " + this.bench.getId());
					oldIngredientsQuantity = this.bench.countIngredientsLeft();
					System.out.println("Total de ingredientes en la banca (ANTES): " + oldIngredientsQuantity);
				}*/
				client.start();
				
				/*if (client instanceof Vendor) {
					int newIngredientsQuantity = this.bench.countIngredientsLeft();
					System.out.println("Total de ingredientes en la banca (DESPUÉS): " + oldIngredientsQuantity);
					int totalIngredientsReplenished = newIngredientsQuantity - oldIngredientsQuantity;
					
					String stringTotal = String.valueOf(totalIngredientsReplenished);
					DataOutputStream dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
					dataOutputStream.writeUTF(stringTotal);
				}*/
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
