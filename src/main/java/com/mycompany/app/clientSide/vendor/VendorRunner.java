package com.mycompany.app.clientSide.vendor;

import com.mycompany.app.clientSide.ClientRunner;
import com.mycompany.app.common.Vendor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

/**
 * Execute a vendor and shows all it moves.
 * <p></p>
 * Starts waiting for any smoker petition. When they take one, the vendor goes to two random benches and replenish their
 * ingredients. Then, they tell the smoker that has called them, that they have finished.
 *
 * @author valen
 */
public class VendorRunner {
	@SuppressWarnings("InfiniteLoopStatement")
	public static void main(String[] args) {
		ClientRunner clientRunner = new ClientRunner();
		Vendor vendor = new Vendor();
		Socket smokerSocket;
		
		try (ServerSocket server = new ServerSocket(4999)) {
			System.out.println("Vendedor a la espera de peticiones.");
			while (true) {
				smokerSocket = server.accept();
				
				DataInputStream smokerDataInputStream = new DataInputStream(smokerSocket.getInputStream());
				String smokerRequest = smokerDataInputStream.readUTF(); // The vendor receive the smoker's petition.
				String smokerName = smokerDataInputStream.readUTF(); // The vendor receive the smoker's name.
				
				if (smokerRequest.equals("Ingredients needed.")) {
					vendor.setSocket(smokerSocket);
					
					System.out.println("\nEl vendedor recibió petición de " + smokerName + ":\n");
					int benchNumber = 0;
					for (int i = 0; i < 2; i++) {
						benchNumber = vendor.getBenchNumberRandomly(benchNumber);
						
						try (Socket benchSocket = clientRunner.getBenchSocket(benchNumber)) {
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(benchSocket.getOutputStream());
							objectOutputStream.writeObject(new Vendor()); // Sends a vendor instance to let the server know who is it
							
							sleep(vendor.getSleepingTime());
							
							int totalIngredientsReplenished = 2; // Quantity to register in the motion-trace file.
							vendor.setBenchId(benchNumber);
							String action; // Action to register in the motion-trace file.
							if (totalIngredientsReplenished == 0) { // For now, this option is not available
								action = "Intentó reponer los ingredientes de la " + vendor.getBenchId() + ", pero esta ya estaba llena.";
							} else {
								action = "Repuso los ingredientes de la " + vendor.getBenchId();
							}
							
							// Messages to let the user knows that the vendor has done their job.
							System.out.println(vendor.getActorName() + " " + action.toLowerCase());
							if (totalIngredientsReplenished > 0) {
								System.out.println("Cantidad de ingredientes repuestos: " + totalIngredientsReplenished);
							}
							
							vendor.updateMotionTrace(action, totalIngredientsReplenished);
						} catch (ConnectException e) {
							System.out.println("Se ha elegido, aleatoriamente, una banca que no se encuentra disponible en este momento.\n");
						} catch (IOException e) { // A problem regarding sending and receiving messages with the smoker..
							System.out.println("Ha ocurrido un problema en la comunicación con " + smokerName + ".\n");
						} catch (InterruptedException e) {
							System.out.println("El vendedor ha sido interrumpido.");
						}
					}
					// Sends to the calling smoker that the vendor has finished.
					DataOutputStream smokerDataOutputStream = new DataOutputStream(smokerSocket.getOutputStream());
					smokerDataOutputStream.writeUTF("Replenishment complete.");
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
