package com.mycompany.app;

import java.net.Socket;

public abstract class Client/* extends Thread*/ implements Runnable {
	protected String actorName;
	protected Socket socket; // FIXME eliminar.
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
	
	public Socket getSocket() {
		return socket;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public void setBench(Bench bench) {
		this.bench = bench;
	}
}
