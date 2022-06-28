package com.mycompany.app.clientSide;

import java.io.IOException;
import java.net.Socket;

/**
 * Has the server's information for smokers and the vendor to connect with the bench, that includes every server's
 * IP and port.
 */
public class ClientRunner {
	
	/**
	 * Returns a socket created with the bench's IP address and port.
	 *
	 * @param benchNumber The option the user has selected previously.
	 * @return A socket with selected bench's connection.
	 * @throws IOException When there are problems creating the socket.
	 */
	public Socket getBenchSocket(int benchNumber) throws IOException {
		String externalIP = "192.168.1.5";
		String localIP = "localhost";
		
		String ip = "";
		int port = 0;
		
		switch (benchNumber) {
			case 1: // Server on Windows 1. - With Tobacco.
				ip = localIP;
				port = 5000;
				break;
			case 2: // Server on Windows 2. - With Matchstick.
				ip = localIP;
				port = 5001;
				break;
			case 3: // Server on Linux. - With Paper.
				ip = localIP; // TODO cambiar cuando se vayan a crear los jar.
				port = 5002;
				break;
		}
		
		return new Socket(ip, port);
	}
}
