package com.mycompany.app.common;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class Client extends Thread {
	protected String actorName;
	protected Socket socket;
	protected Bench bench;
	protected String benchId;
	protected final int sleepingTime = 6 * 1000;
	
	public void updateMotionTrace(String action, int amount) {
		try {
			new XMLWriter().updateMotionTrace(this, action, amount);
		} catch (IOException | SAXException | TransformerException | ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void sendBenchId(String message) throws IOException, InterruptedException {
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		dataOutputStream.writeUTF(bench.getId());
		
		System.out.println(message);
		sleep(sleepingTime);
	}
	
	public void receiveBenchId(String message) throws IOException, InterruptedException {
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		this.benchId = dataInputStream.readUTF();
		
		System.out.println(message + this.benchId);
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
