package com.mycompany.app.clients;

import com.mycompany.app.Vendor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RunVendor {
	public static void main(String[] args) {
		Vendor vendor = new Vendor();
		RunnerClient runnerClient = new RunnerClient();
		Socket smokerSocket;
		
		try (ServerSocket server = new ServerSocket(4999)) {
			System.out.println("Vendedor a la espera de peticiones.");
			while (true) {
				smokerSocket = server.accept();
				
				DataInputStream dataInputStream = new DataInputStream(smokerSocket.getInputStream());
				String smokerRequest = dataInputStream.readUTF();
				
				if (smokerRequest.equals("Ingredients needed.")) {
					vendor.setSocket(smokerSocket);
					int benchNumber = 0;
					for (int i = 0; i < 2; i++) {
						benchNumber = vendor.getBenchNumberRandomly(benchNumber);
						try (Socket benchSocket = runnerClient.getBenchSocket(benchNumber)) {
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(benchSocket.getOutputStream());
							objectOutputStream.writeObject(vendor);
							
							System.out.println("El vendedor está reponiendo los ingredientes.");
							smokerSocket.close();
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
