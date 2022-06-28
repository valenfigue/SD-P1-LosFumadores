package com.mycompany.app.common;

import com.mycompany.app.common.XMLFile.XMLWriter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * A client to the program. It could be a smoker or the vendor.
 *
 * @author valen
 */
public abstract class Client extends Thread {
	protected String actorName;
	protected Socket socket;
	/**
	 * The bench the client currently is.
	 */
	protected Bench bench;
	/**
	 * Bench identifier. Save the specific ingredient the bench has.
	 */
	protected String benchId;
	/**
	 * The time the thread will be sleeping.
	 */
	protected final int sleepingTime = 6 * 1000;
	
	/**
	 * Updates the motion-trace file.
	 *
	 * @param action Action that the client has done.
	 * @param quantity Amount of ingredients taken from a bench (if the client is a smoker), or
	 *                 replenished in a bench (if the client is the vendor).
	 */
	public void updateMotionTrace(String action, int quantity) {
		try {
			new XMLWriter().updateMotionTrace(this, action, quantity);
		} catch (IOException | SAXException | TransformerException | ParserConfigurationException e) {
			System.out.println("Ha ocurrido un problema al actualizar el archivo de trazas de movimiento.");
		}
	}
	
	/**
	 * Sends the bench ID to the client that is waiting for it. Then, it shows a message to the user and wait for a
	 * moment to continue the program.
	 *
	 * @param message A message to be shown after sending the bench ID.
	 * @throws IOException When there is a problem sending the bench ID.
	 * @throws InterruptedException When the thread is interrupted while sleeping.
	 */
	public void sendBenchId(String message) throws IOException, InterruptedException {
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		dataOutputStream.writeUTF(bench.getId());
		
		System.out.println(message);
		sleep(sleepingTime);
	}
	
	/**
	 * Receives the bench ID where the client should have gone. Then, it shows a message to the user and wait for a
	 * moment to continue the program.
	 *
	 * @param message A message to be shown after receiving the bench ID.
	 * @throws IOException When there is a problem sending the bench ID.
	 * @throws InterruptedException When the thread is interrupted while sleeping.
	 */
	public void receiveBenchId(String message) throws IOException, InterruptedException {
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		this.benchId = dataInputStream.readUTF();
		
		System.out.println(message + this.benchId + "\n");
		sleep(sleepingTime);
	}
	
	public String getActorName() {
		return actorName;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public String getBenchId() {
		return this.benchId;
	}
	
	public int getSleepingTime() {
		return sleepingTime;
	}
}
