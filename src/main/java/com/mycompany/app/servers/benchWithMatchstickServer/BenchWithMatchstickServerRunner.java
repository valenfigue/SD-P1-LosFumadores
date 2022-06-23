package com.mycompany.app.servers.benchWithMatchstickServer;

import java.io.IOException;

public class BenchWithMatchstickServerRunner {
	public static void main(String[] args) {
		try {
			new BenchWithMatchstickServer().startListening();
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
