package com.mycompany.app.clients.smokers;

import com.mycompany.app.smokers.Smoker;
import com.mycompany.app.clients.ClientRunner;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SmokerRunner extends ClientRunner {
	
	public void mainProgram(Smoker smoker) throws IOException {
		int exit = 2; // The user doesn't want to end the program.
		
		do {
			if (!smoker.checkCigarIngredients()) {
				if (smoker.getTriesCount() < 3) {
					try (Socket socket = this.selectBench()) {
						ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
						objectOutputStream.writeObject(smoker);
						
						smoker.setSocket(socket);
						smoker.sendCigar();
						smoker.receiveCigar();
						smoker.increaseTriesCount();
					}
				} else {
					try (Socket socket = new Socket("192.168.1.6", 4999)) {
						DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
						dataOutputStream.writeUTF("Ingredients needed.");
						
						System.out.println("El " + smoker.getActorName().toLowerCase() + " está esperando por el vendedor.");
						
						smoker.restartTriesCount();
					}
				}
			} else {
				smoker.rollCigar();
				exit = this.askUserToEndProgram();
			}
		} while (exit == 2);
	}
	
	private Socket selectBench() throws IOException {
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
	
	private int askUserToEndProgram() {
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
