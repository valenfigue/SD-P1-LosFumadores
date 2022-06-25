package com.mycompany.app.common;

import java.net.Socket;

public abstract class Client extends Thread {
	protected String actorName;
	protected Socket socket;
	protected Bench bench;
	
	public void updateMotionTrace(String action, int amount) {
	
	}
	
	public String getActorName() {
		return actorName;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
}
