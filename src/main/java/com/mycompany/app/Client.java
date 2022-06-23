package com.mycompany.app;

import com.mycompany.app.benches.Bench;

import java.net.Socket;

public abstract class Client extends Thread {
	protected String actorName;
	protected Socket socket;
	protected Bench bench;
	
	public void updateMotionTrace(
		Client actor,
		String action,
		int amount
	) {
	
	}
	
	public String getActorName() {
		return actorName;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
}
