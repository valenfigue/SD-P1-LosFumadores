package com.mycompany.app.servers;

import com.mycompany.app.Smoker;
import com.mycompany.app.Vendor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	protected int port;
	public Socket socket;
	
	public void startListening() throws IOException {
		ServerSocket server = new ServerSocket(this.port);
		System.out.println("Servidor iniciado.");
		
		while (true) {
			socket = server.accept();
		}
	}
	
	public Thread acceptClient(Smoker smoker) {
		return null;
	}
	
	public Thread acceptClient(Vendor smoker) {
		return null;
	}
}
