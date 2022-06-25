package com.mycompany.app.common;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.Socket;

public abstract class Client extends Thread {
	protected String actorName;
	protected Socket socket;
	protected Bench bench;
	
	protected String benchId;
	
	public void updateMotionTrace(String action, int amount) {
		try {
			new XMLWriter().updateMotionTrace(this, action, amount);
		} catch (IOException | SAXException | TransformerException | ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getActorName() {
		return actorName;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public String getBenchId() {
		return benchId;
	}
}
