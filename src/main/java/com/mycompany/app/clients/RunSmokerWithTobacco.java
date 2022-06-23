package com.mycompany.app.clients;

import com.mycompany.app.smokers.SmokerWithTobacco;

import java.io.*;
import java.net.Socket;

public class RunSmokerWithTobacco {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		RunnerClient runnerClient = new RunnerClient();
		int exit = 2; // The user doesn't want to end the program.
		
		SmokerWithTobacco smoker = new SmokerWithTobacco();
		
		do {
			if (!smoker.checkCigarIngredients()) {
				if (smoker.getTriesCount() < 3) {
					try (Socket socket = runnerClient.selectBench()) {
						ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
						objectOutputStream.writeObject(smoker);
						
						smoker.setSocket(socket);
						smoker.sendCigar();
						smoker.receiveCigar();
					}
				} /*else {
				}*/
			} else {
				smoker.rollCigar();
				exit = runnerClient.askUserToEndProgram();
			}
		} while (exit == 2);
	}
}
