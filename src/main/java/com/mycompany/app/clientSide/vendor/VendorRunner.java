package com.mycompany.app.clientSide.vendor;

import com.mycompany.app.clientSide.ClientRunner;
import com.mycompany.app.common.Vendor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class VendorRunner {
	@SuppressWarnings("InfiniteLoopStatement")
	public static void main(String[] args) {
		ClientRunner clientRunner = new ClientRunner();
		Vendor vendor = new Vendor();
		Socket smokerSocket;
		
		try (ServerSocket server = new ServerSocket(4999)) {
			System.out.println("Vendedor a la espera de peticiones.\n");
			while (true) {
				smokerSocket = server.accept();
				
				DataInputStream dataInputStream = new DataInputStream(smokerSocket.getInputStream());
				String smokerRequest = dataInputStream.readUTF();
				
				if (smokerRequest.equals("Ingredients needed.")) {
					vendor.setSocket(smokerSocket);
					
					System.out.println("El vendedor recibió petición de uno de los fumadores.");
					int benchNumber = 0;
					for (int i = 0; i < 2; i++) {
						benchNumber = vendor.getBenchNumberRandomly(benchNumber);
						
						try (Socket benchSocket = clientRunner.getBenchSocket(benchNumber)) {
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(benchSocket.getOutputStream());
							objectOutputStream.writeObject(new Vendor());
							
//							vendor.receiveBenchId("Se repondrán los ingredientes de la ");
//							sleep(vendor.getSleepingTime());
							
//							int totalIngredientsReplenished = Integer.getInteger(dataInputStream.readUTF());
							int totalIngredientsReplenished = 0;
							String action;
							if (totalIngredientsReplenished == 0) {
								action = "Intentó reponer los ingredientes de la " + vendor.getBenchId() + ", pero esta ya estaba llena.";
							} else {
								action = "Repuso los ingredientes de la " + vendor.getBenchId();
							}
							vendor.updateMotionTrace(action, totalIngredientsReplenished);
						} catch (ConnectException e) {
							System.out.println("Se ha elegido, aleatoriamente, una banca que no se encuentra disponible en este momento.\n");
						} catch (IOException e) {
							throw new RuntimeException(e);
						}/* catch (InterruptedException e) {
							System.out.println("El vendedor ha sido interrumpido.");
						}*/
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
