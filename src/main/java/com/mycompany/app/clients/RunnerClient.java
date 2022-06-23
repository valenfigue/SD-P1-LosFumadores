package com.mycompany.app.clients;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class RunnerClient {
	
	public Socket selectBench() throws IOException {
		Scanner sn = new Scanner(System.in);
		sn.useDelimiter("\n");
		
		System.out.println("¿De cuál banca quisiera tomar los ingredientes faltantes para su cigarro?");
		System.out.println("Banca 1: tiene tabacos.");
		System.out.println("Banca 2: tiene fósforos.");
		System.out.println("Banca 3: tiene papeles.");
		System.out.print("Respuesta: ");
		int benchNumber = sn.nextInt();
		
		return this.getBenchSocket(benchNumber);
	}
	
	public Socket getBenchSocket(int benchNumber) throws IOException {
		// TODO: arreglar firewall para aceptar peticiones externas.
		String externalIP = "192.168.1.5"; // IP según geolocalizador. TODO verificar.
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
	
	public int askUserToEndProgram() {
		Scanner sn = new Scanner(System.in);
		sn.useDelimiter("\n");
		int userAnswer = 0;
		
		do {
			System.out.println("¿Desea terminar?");
			System.out.println("1.- Sí.");
			System.out.println("2.- No.");
			System.out.print("Respuesta: ");
			try {
				userAnswer = sn.nextInt();
			} catch (Exception e) {
				System.out.println("No existe esa opción.");
			}
			
			if (userAnswer != 1 && userAnswer != 2) {
				System.out.println("No existe esa opción.");
			}
			
		} while (userAnswer != 1 && userAnswer != 2);
		
		return userAnswer;
		
	}
}
