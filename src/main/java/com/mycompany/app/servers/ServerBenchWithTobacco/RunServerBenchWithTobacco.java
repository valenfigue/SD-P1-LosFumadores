package com.mycompany.app.servers.ServerBenchWithTobacco;


import java.io.IOException;

public class RunServerBenchWithTobacco {
	public static void main(String[] args) {
		try {
			new ServerBenchWithTobacco().startListening();
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
