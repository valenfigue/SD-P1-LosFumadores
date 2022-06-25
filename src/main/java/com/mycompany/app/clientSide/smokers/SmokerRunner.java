package com.mycompany.app.clientSide.smokers;

import com.mycompany.app.common.smokers.Smoker;
import com.mycompany.app.clientSide.ClientRunner;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
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
						smoker.receiveBenchId();
						int oldIngredientsQuantity = smoker.countCigarIngredients();
						smoker.receiveCigar();
						smoker.increaseTriesCount();
						
						int newIngredientsQuantity = smoker.countCigarIngredients();
						
						int ingredientsQuantity = newIngredientsQuantity - oldIngredientsQuantity;
						String action;
						if (ingredientsQuantity == 0) {
							action = smoker.getActorName() + " intentó tomar un ingrediente de la " + smoker.getBenchId();
						} else {
							action = smoker.getActorName() + " tomó un ingrediente de la " + smoker.getBenchId();
						}
						smoker.updateMotionTrace(action, ingredientsQuantity);
					} catch (ConnectException e) {
						System.out.println("La banca que ha elegido no se encuentra disponible en este momento.\n");
					}
				} else {
					System.out.println("\nEl " + smoker.getActorName().toLowerCase() + " está esperando por el vendedor.\n");
					
					try (Socket socket = new Socket("localhost", 4999)) {
						DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
						dataOutputStream.writeUTF("Ingredients needed.");
						
						smoker.restartTriesCount();
					} catch (ConnectException e) {
						System.out.println("\nEl vendedor no se encuentra disponible en este momento.\n");
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
		
		System.out.println("\n¿De cuál banca quisiera tomar los ingredientes faltantes para su cigarro?\n");
		System.out.println("Banca 1: tiene tabacos.");
		System.out.println("Banca 2: tiene fósforos.");
		System.out.println("Banca 3: tiene papeles.");
		System.out.print("\nRespuesta: ");
		int benchNumber = sn.nextInt();
		System.out.println("\n");
		
		return this.getBenchSocket(benchNumber);
	}
	
	private int askUserToEndProgram() {
		Scanner sn = new Scanner(System.in);
		sn.useDelimiter("\n");
		String errorMessage = "\nNo existe esa opción.";
		int userAnswer = 0;
		
		do {
			System.out.println("\n¿Desea terminar?");
			System.out.println("1.- Sí.");
			System.out.println("2.- No.");
			System.out.print("\nRespuesta: ");
			try {
				userAnswer = sn.nextInt();
			} catch (Exception e) {
				System.out.println(errorMessage);
			}
			
			if (userAnswer != 1 && userAnswer != 2) {
				System.out.println(errorMessage);
			}
			
		} while (userAnswer != 1 && userAnswer != 2);
		
		return userAnswer;
	}
}
