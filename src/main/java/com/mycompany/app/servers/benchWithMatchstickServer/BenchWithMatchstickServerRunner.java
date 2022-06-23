package com.mycompany.app.servers.benchWithMatchstickServer;

import com.mycompany.app.servers.benchWithTobaccoServer.BenchWithTobaccoServer;

import java.io.IOException;

public class BenchWithMatchstickServerRunner {
	public static void main(String[] args) {
		try {
			new BenchWithMatchstickServer().startListening();
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
