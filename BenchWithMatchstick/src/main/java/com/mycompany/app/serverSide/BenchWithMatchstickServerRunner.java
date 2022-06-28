package com.mycompany.app.serverSide;

/**
 * Execute the server for the bench with the matchsticks.
 *
 * @author valen
 */
public class BenchWithMatchstickServerRunner {
	public static void main(String[] args) {
		new BenchWithMatchstickServer().startListening();
	}
}
