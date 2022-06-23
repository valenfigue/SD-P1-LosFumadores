package com.mycompany.app.servers.benchWithTobaccoServer;


import java.io.IOException;

public class BenchWithTobaccoServerRunner {
	public static void main(String[] args) {
		try {
			new BenchWithTobaccoServer().startListening();
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
