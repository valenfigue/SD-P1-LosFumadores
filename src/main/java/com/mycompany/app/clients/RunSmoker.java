package com.mycompany.app.clients;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class RunSmoker {
	public static void main(String[] args) {
		String externalIP = "190.205.6.206"; // IP según geolocalizador. TODO verificar.
		String localIP = "localhost";
		
		try (Socket socket = new Socket(localIP, 5000)) { // TODO: arreglar firewall.
			System.out.println("Hola");
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
