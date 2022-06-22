package com.mycompany.app.clients;

import com.mycompany.app.Smoker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class RunSmokerWithTobacco {
	public static void main(String[] args) {
		String externalIP = "190.205.6.206"; // IP según geolocalizador. TODO verificar.
		String localIP = "localhost";
		
		try (Socket socket = new Socket(localIP, 5000)) { // TODO: arreglar firewall.
			DataInputStream inputStream = new DataInputStream(socket.getInputStream());
			DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
			
			Smoker smokerWithTobacco = Smoker.createSmokerWithTobacco(socket);
			outputStream.writeUTF(smokerWithTobacco.getActorName());
			
//			Thread smokerThread = new Thread(smokerWithTobacco);
//			smokerWithTobacco.setSocket(socket);
//			smokerThread.start();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
