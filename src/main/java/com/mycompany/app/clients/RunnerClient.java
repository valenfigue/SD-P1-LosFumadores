package com.mycompany.app.clients;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class RunnerClient {
	
	public Socket selectBench() throws IOException {
		// TODO: arreglar firewall para aceptar peticiones externas.
		String externalIP = "190.205.6.206"; // IP según geolocalizador. TODO verificar.
		String localIP = "localhost";
		
		String ip = "";
		int port = 0;
		
		Scanner sn = new Scanner(System.in);
		sn.useDelimiter("\n");
		
		System.out.println("¿De cuál banca quisiera tomar los ingredientes faltantes para su cigarro?");
		int benchNumber = sn.nextInt();
		
		switch (benchNumber) {
			case 1 -> { // Server on Windows 1.
				ip = localIP;
				port = 5000;
			}
			case 2 -> { // Server on Windows 2.
				ip = localIP;
				port = 5001;
			}
			case 3 -> { // Server on Linux.
				ip = localIP;
				port = 5002;
			}
		}
		
		return new Socket(ip, port);
	}
}
