package com.mycompany.app.clients;

import com.mycompany.app.Smoker;
import com.mycompany.app.smokers.SmokerWithTobacco;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class RunSmokerWithTobacco {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		RunnerClient runnerClient = new RunnerClient();
		int exit = 2; // The user doesn't want to end the program.
		
		Smoker smokerWithTobacco = new SmokerWithTobacco();
		
		do {
			if (!smokerWithTobacco.checkCigarIngredients()) {
				if (smokerWithTobacco.getTriesCount() < 3) {
					try (Socket socket = runnerClient.selectBench()) {
						ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
						
						objectOutputStream.writeObject(smokerWithTobacco);
						
						ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
						smokerWithTobacco = (SmokerWithTobacco) objectInputStream.readObject(); // FIXME está enviando null T-T
						
//						smokerWithTobacco.start();
					}
				} /*else {
				}*/
			} else {
				smokerWithTobacco.rollCigar();
				exit = runnerClient.askUserToEndProgram();
			}
		} while (exit == 2);
	}
}
