package com.mycompany.app.clientSide;

import com.mycompany.app.common.Vendor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

public class VendorRunner {
	@SuppressWarnings("InfiniteLoopStatement")
	public static void main(String[] args) {
		Vendor vendor = new Vendor();
		ClientRunner clientRunner = new ClientRunner();
		Socket smokerSocket;
		
		try (ServerSocket server = new ServerSocket(4999)) {
			System.out.println("Vendedor a la espera de peticiones.\n");
			while (true) { // FIXME no está haciendo lo que debería hacer
				smokerSocket = server.accept();
				
				DataInputStream dataInputStream = new DataInputStream(smokerSocket.getInputStream());
				String smokerRequest = dataInputStream.readUTF();
				
				if (smokerRequest.equals("Ingredients needed.")) {
					vendor.setSocket(smokerSocket);
					System.out.println("El vendedor recibió petición de uno de los fumadores.");
					// TODO hacerlo un hilo para que pueda recibir varias peticiones al mismo tiempo.
					int benchNumber = 0;
					for (int i = 0; i < 2; i++) {
						benchNumber = vendor.getBenchNumberRandomly(benchNumber);
						
						String chosenBench = "Se escogió la banca con ";
						switch (benchNumber) {
							case 1 -> chosenBench += "tabacos.";
							case 2 -> chosenBench += "fósforos.";
							case 3 -> chosenBench += "papeles.";
							default -> chosenBench = "La banca escogida no existe.";
						}
						System.out.println(chosenBench);
						
						try (Socket benchSocket = clientRunner.getBenchSocket(benchNumber)) {
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(benchSocket.getOutputStream());
							objectOutputStream.writeObject(vendor);
							
							System.out.println("El vendedor está reponiendo los ingredientes.");
							smokerSocket.close();
						} catch (ConnectException e) {
							System.out.println("La banca que se ha elegido aleatoriamente, no se encuentra disponible en este momento.\n");
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
