package com.mycompany.app;

import java.io.Serializable;
import java.net.Socket;

public abstract class Client implements Runnable, Serializable {
	protected String name;
	protected Socket socket;
	
	public void updateMotionTrace(
		Client actor,
		String action,
		int amount
	) {
	
	}
	
	public String getName() {
		return name;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
