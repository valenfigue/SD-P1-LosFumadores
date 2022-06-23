package com.mycompany.app.servers.benchWithPaperServer;

import com.mycompany.app.servers.benchWithMatchstickServer.BenchWithMatchstickServer;

import java.io.IOException;

public class BenchWithPaperServerRunner {
	public static void main(String[] args) {
		try {
			new BenchWithPaperServer().startListening();
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
