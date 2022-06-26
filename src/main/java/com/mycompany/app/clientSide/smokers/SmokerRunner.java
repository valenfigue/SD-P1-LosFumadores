package com.mycompany.app.clientSide.smokers;

import com.mycompany.app.common.smokers.Smoker;
import com.mycompany.app.clientSide.ClientRunner;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SmokerRunner extends ClientRunner {
	
	public void mainProgram(Smoker smoker) throws IOException, InterruptedException {
		int exit = 2; // The user doesn't want to end the program.
		
		do {
			int oldIngredientsQuantity = smoker.countCigarIngredients();
			
			if (!smoker.checkCigarIngredients()) {
				if (smoker.getTriesCount() < 3) {
					try (Socket socket = this.selectBench()) {
						ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
						objectOutputStream.writeObject(smoker);
						
						smoker.setSocket(socket);
						smoker.sendCigar();
						smoker.receiveBenchId("\n" + smoker.getActorName() + " va a intentar tomar un ingrediente de la ");
						smoker.receiveCigar();
						smoker.increaseTriesCount();
					} catch (ConnectException e) {
						System.out.println("La banca que ha elegido no se encuentra disponible en este momento.\n");
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					} catch (BindException |InputMismatchException e) {
						System.out.println("Esa banca no existe.");
					} finally {
						int newIngredientsQuantity = smoker.countCigarIngredients();
						
						int totalIngredientsTaken = newIngredientsQuantity - oldIngredientsQuantity;
						String action;
						if (totalIngredientsTaken == 0) {
							action = smoker.getActorName() + " intentó tomar un ingrediente de la " + smoker.getBenchId();
						} else {
							action = smoker.getActorName() + " tomó un ingrediente de la " + smoker.getBenchId();
						}
						smoker.updateMotionTrace(action, totalIngredientsTaken);
					}
				} else {
					System.out.println("\nEl " + smoker.getActorName().toLowerCase() + " está esperando por el vendedor.\n");
					Thread.sleep(smoker.getSleepingTime());
					
					try (Socket socket = new Socket("localhost", 4999)) {
						DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
						dataOutputStream.writeUTF("Ingredients needed.");
						
						DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
						String vendorResponse = dataInputStream.readUTF();
						
						if (vendorResponse.equals("Replenishment complete.")) {
							System.out.println("El vendedor repuso los ingredientes.");
						}
						
					} catch (ConnectException e) {
						System.out.println("\nEl vendedor no se encuentra disponible en este momento.\n");
					} finally {
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
