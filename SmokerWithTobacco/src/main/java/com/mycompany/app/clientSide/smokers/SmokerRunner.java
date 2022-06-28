package com.mycompany.app.clientSide.smokers;

import com.mycompany.app.clientSide.ClientRunner;
import com.mycompany.app.common.smokers.Smoker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Execute all user interface logic regarding controlling any smoker.
 * <p></p>
 * This class has the starting program that any smoker would execute by their own main program.
 * The class also contains methods where the user is asked to choose a bench where the smoker will go and take
 * an ingredient; as well as weather the user wants to restart the program or finish it, once the smoker had smoked.
 *
 * @author valen
 */
public class SmokerRunner extends ClientRunner {
	private Smoker smoker;
	private int exit = 2;
	
	/**
	 * Any smoker's starting program.
	 * <p></p>
	 * First, the smoker count how many ingredients they have to smoke.If they don't have enough,
	 * the user is asked to choose a bench where the smoker will go and take an ingredient.
	 * The program then will show in the console, the bench where the smoker went to (in case the bench isn't available,
	 * the program will also show a message).
	 * <p></p>
	 * If the smoker has tried two times to take an ingredient they don't have yet, and fail, they will try to call
	 * the vendor to replenish the benches. In case the bench isn't available, the program will show a message.
	 * <p></p>
	 * Finally, when the smoker has all the ingredients, they will smoke. In the end, the program will ask the user
	 * if they would like to start again or finish.
	 *
	 * @param smoker A smoker that has a certain ingredient at the start of the program.
	 */
	public void startProgram(Smoker smoker) {
		this.smoker = smoker;
		
		do {
			this.exit = 2; // The user doesn't want to end the program.
			if (!smoker.checkCigarIngredients()) { // Check how many ingredients the smoker has.
				
				if (smoker.getTriesCount() < 3) { // When the smoker still can go and take an ingredient from a bench.
					this.takeIngredientFromBench();
				} else { /* When the smoker has tried two times to go and take an ingredient from a bench,
					* but still doesn't have all the ingredients to roll their cigar and smoke, they call the vendor.
					* */
					this.callVendor();
				}
			} else { // When the smoker has all the ingredients to smoke.
				smoker.rollCigar();
				this.exit = this.askUserToEndProgram();
			}
		} while (this.exit == 2);
	}
	
	/**
	 * The client tries to connect with the selected bench. If the connection was successful, the client send to the
	 * server the smoker instance to let it know who is it.
	 * <p></p>
	 * Then, the client sends to the server the ingredients the smoker has, receive the bench's ID that the client
	 * has connected to; and receive the new ingredients the smoker now has.
	 * <p></p>
	 * Later, the client updates the motion-trace file with the chance and increase the smoker's tries.
	 */
	private void takeIngredientFromBench() {
		// To know how many ingredients the smoker has before go and take an ingredient from a bench.
		int oldIngredientsQuantity = this.smoker.countCigarIngredients();
		
		try (Socket socket = this.selectBench()) { // If the bench is available, the smoker connects to it.
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(this.smoker); // Sends the smoker to let the server know who is it.
			
			this.smoker.setSocket(socket);
			this.smoker.sendCigar();
			this.smoker.receiveBenchId(this.smoker.getActorName() + " va a intentar tomar un ingrediente de la ");
			this.smoker.receiveCigar();
			this.smoker.increaseTriesCount();
		}
		
		catch (ConnectException e) {
			System.out.println("La banca que ha elegido no se encuentra disponible en este momento.\n");
		} catch (InterruptedException e) { // When the thread has been interrupted while sleeping.
			System.out.println("El fumador ha sido interrumpido.\n");
		} catch (BindException |InputMismatchException e) {
			// When the user introduce an unavailable option on the console.
			System.out.println("Esa banca no existe.\n");
		} catch (IOException e) { // A problem regarding sending and receiving the cigar and the bench ID.
			System.out.println("Ha ocurrido un problema en la comunicación entre el actual servidor y este fumador.\n");
		}
		
		finally {
			// How many ingredients has the smoker now.
			int newIngredientsQuantity = this.smoker.countCigarIngredients();
			// How many ingredients the smoker has taken from the bench. Quantity to register in the motion-trace file.
			int totalIngredientsTaken = newIngredientsQuantity - oldIngredientsQuantity;
			String action; // The action to write on the motion-trace file.
			if (totalIngredientsTaken == 0) { // if the smoker has taken any new ingredient from the bench.
				action = this.smoker.getActorName() + " intentó tomar un ingrediente de la " + this.smoker.getBenchId();
			} else {
				action = this.smoker.getActorName() + " tomó un ingrediente de la " + this.smoker.getBenchId();
			}
			this.smoker.updateMotionTrace(action, totalIngredientsTaken);
		}
	}
	
	/**
	 * When the smoker has tried two times to go and take an ingredient from a bench,
	 * but still doesn't have all the ingredients to roll their cigar and smoke, they call the vendor.
	 * <p></p>
	 * The client connects to the vendor and sends them a message, letting them know they are needed.
	 * Then, the client receive a message from the vendor, letting them know the vendor has finished.
	 * <p></p>
	 * At the end, restarts the smoker's tries count.
	 */
	private void callVendor() {
		System.out.println("El " + this.smoker.getActorName() + " está esperando por el vendedor.");
		
		try (Socket socket = new Socket("localhost", 4999)) {
			Thread.sleep(this.smoker.getSleepingTime());
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			dataOutputStream.writeUTF("Ingredients needed.");
			dataOutputStream.writeUTF(this.smoker.getActorName());
			
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
			String vendorResponse = dataInputStream.readUTF();
			
			if (vendorResponse.equals("Replenishment complete.")) {
				System.out.println("El vendedor repuso los ingredientes.\n");
			}
			Thread.sleep(this.smoker.getSleepingTime());
		}
		
		catch (ConnectException e) {
			System.out.println("El vendedor no se encuentra disponible en este momento.\n");
		} catch (InterruptedException e) { // When the thread has been interrupted while sleeping.
			System.out.println("El fumador ha sido interrumpido.\n");
		} catch (IOException e) { // A problem regarding sending and receiving the cigar and the bench ID.
			System.out.println("Ha ocurrido un problema en la comunicación entre el actual servidor y este fumador.\n");
		}
		
		finally {
			this.smoker.restartTriesCount();
		}
	}
	
	/**
	 * Shows to the user the bench options and what ingredient they have. Then, asks the user to choose one of them.
	 *
	 * @return Socket with the selected bench connection.
	 * @throws IOException When there are problems connecting with the bench.
	 */
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
	
	/**
	 * Asks the user if they would like to restart the program or finish it.
	 *
	 * @return User's answer.
	 */
	private int askUserToEndProgram() {
		Scanner sn = new Scanner(System.in);
		sn.useDelimiter("\n");
		String errorMessage = "No existe esa opción.";
		int userAnswer = 0;
		
		do {
			System.out.println("¿Desea terminar?");
			System.out.println("1.- Sí.");
			System.out.println("2.- No.");
			System.out.print("\nRespuesta: ");
			try {
				userAnswer = sn.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(errorMessage);
			}
			
			if (userAnswer != 1 && userAnswer != 2) {
				System.out.println(errorMessage);
			}
			
		} while (userAnswer != 1 && userAnswer != 2);
		
		return userAnswer;
	}
}
