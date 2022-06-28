package com.mycompany.app.serverSide;

import com.mycompany.app.common.Bench;
import com.mycompany.app.common.Client;
import com.mycompany.app.common.Vendor;
import com.mycompany.app.common.smokers.SmokerWithMatchstick;
import com.mycompany.app.common.smokers.SmokerWithPaper;
import com.mycompany.app.common.smokers.SmokerWithTobacco;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Has all servers' logic.
 * <p></p>
 * Accepts a client and let them do their job defined in their `run` method.
 *
 * @author valen
 */
public class BenchServer {
	protected int port;
	private Socket socket;
	protected Bench bench;
	protected Client client;
	
	/**
	 * Starts to listen to any client that wants to connect to the server and let them do what they wanted to do in
	 * the server.
	 */
	@SuppressWarnings("InfiniteLoopStatement")
	public void startListening() {
		try (ServerSocket server = new ServerSocket(this.port)) {
			System.out.println("Servidor iniciado.\n");
			
			while (true) {
				socket = server.accept();
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				this.acceptClient(objectInputStream.readObject());
				client.start();
			}
		} catch (IOException e) {
			System.out.println("Hay problemas en la comunicación con un cliente.");
		} catch (ClassNotFoundException e) {
			System.out.println("El cliente enviado no se reconoce.");
		}
	}
	
	/**
	 * Creates a client depending on the object instance received from a client.
	 *
	 * @param clientObject Instance received from a client.
	 */
	private void acceptClient(Object clientObject) {
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
	}
}
