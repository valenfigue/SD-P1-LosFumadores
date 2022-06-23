package com.mycompany.app.clients;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class RunnerClient {
	
	public Socket getBenchSocket(int benchNumber) throws IOException {
		String externalIP = "192.168.1.5";
		String localIP = "localhost";
		
		String ip = "";
		int port = 0;
		
		switch (benchNumber) {
			case 1 -> { // Server on Windows 1. - With Tobacco.
				ip = externalIP;
				port = 5000;
			}
			case 2 -> { // Server on Windows 2. - With Matchstick.
				ip = externalIP;
				port = 5001;
			}
			case 3 -> { // Server on Linux. - With Paper.
				ip = externalIP;
				port = 5002;
			}
			default -> System.out.println("No existe esa banca.");
		}
		
		return new Socket(ip, port);
	}
}
