package com.mycompany.app.clientSide;

import java.io.IOException;
import java.net.Socket;

public class ClientRunner {
	
	public Socket getBenchSocket(int benchNumber) throws IOException {
		String externalIP = "192.168.1.5";
		String localIP = "localhost";
		
		String ip = "";
		int port = 0;
		
		switch (benchNumber) {
			case 1 -> { // Server on Windows 1. - With Tobacco.
				ip = localIP;
				port = 5000;
			}
			case 2 -> { // Server on Windows 2. - With Matchstick.
				ip = localIP;
				port = 5001;
			}
			case 3 -> { // Server on Linux. - With Paper.
				ip = localIP;
				port = 5002;
			}
		}
		
		return new Socket(ip, port);
	}
}
