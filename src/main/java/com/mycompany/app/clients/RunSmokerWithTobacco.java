package com.mycompany.app.clients;

import com.mycompany.app.Smoker;
import com.mycompany.app.smokers.SmokerWithTobacco;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class RunSmokerWithTobacco {
	public static void main(String[] args) {
		RunnerClient runnerClient = new RunnerClient();
		int exit = 2; // The user doesn't want to end the program.
		
		Smoker smokerWithTobacco = new SmokerWithTobacco();
		
		
		do {
			
			if (!smokerWithTobacco.checkCigarIngredients()) {
				
				if (smokerWithTobacco.getTriesCount() < 3) {
					try (Socket socket = runnerClient.selectBench()) {
						DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
						DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
						
						dataOutputStream.writeUTF(smokerWithTobacco.getActorName());
						/*ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
						smokerWithTobacco = (SmokerWithTobacco) objectInputStream.readObject();*/
						
//						smokerWithTobacco.start();
						// FIXME devolver el objeto creado en el servidor.
					} catch (UnknownHostException e) {
						throw new RuntimeException(e);
					} catch (IOException e) {
						throw new RuntimeException(e);
					} /*catch (ClassNotFoundException e) {
						throw new RuntimeException(e);
					}*/
				} /*else {
				}*/
			} else {
				smokerWithTobacco.start();
				exit = runnerClient.askUserToEndProgram();
			}
		} while (exit != 2);
	}
}
